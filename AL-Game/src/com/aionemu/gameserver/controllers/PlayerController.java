/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen, ArchSoft,   *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package com.aionemu.gameserver.controllers;

import com.aionemu.gameserver.configs.main.*;
import com.aionemu.gameserver.controllers.attack.AttackUtil;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.actions.PlayerMode;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.AbyssRank;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.gameobjects.state.CreatureVisualState;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.skill.PlayerSkillEntry;
import com.aionemu.gameserver.model.stats.container.PlayerGameStats;
import com.aionemu.gameserver.model.summons.SummonMode;
import com.aionemu.gameserver.model.summons.UnsummonType;
import com.aionemu.gameserver.model.team2.group.PlayerFilters.ExcludePlayerFilter;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.model.templates.flypath.FlyPathEntry;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.model.templates.panels.SkillPanel;
import com.aionemu.gameserver.model.templates.quest.QuestItems;
import com.aionemu.gameserver.model.templates.robot.RobotInfo;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.stats.PlayerStatsTemplate;
import com.aionemu.gameserver.model.templates.zone.ZoneClassName;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.LOG;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.TYPE;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.restrictions.RestrictionsManager;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.services.abyss.AbyssService;
import com.aionemu.gameserver.services.craft.CraftSkillUpdateService;
import com.aionemu.gameserver.services.events.BanditService;
import com.aionemu.gameserver.services.events.CrazyDaevaService;
import com.aionemu.gameserver.services.events.FFAService;
import com.aionemu.gameserver.services.events.bg.DeathmatchBg;
import com.aionemu.gameserver.services.events.bg.SoloSurvivorBg;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativityEssenceService;
import com.aionemu.gameserver.services.summons.SummonsService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.services.toypet.MinionService;
import com.aionemu.gameserver.services.toypet.PetSpawnService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.skillengine.model.*;
import com.aionemu.gameserver.skillengine.model.Skill.SkillMethod;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.taskmanager.tasks.PlayerMoveTaskManager;
import com.aionemu.gameserver.taskmanager.tasks.TeamEffectUpdater;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.utils.audit.AuditLogger;
import com.aionemu.gameserver.world.MapRegion;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldType;
import com.aionemu.gameserver.world.geo.GeoService;
import com.aionemu.gameserver.world.knownlist.Visitor;
import com.aionemu.gameserver.world.zone.ZoneInstance;
import com.aionemu.gameserver.world.zone.ZoneName;
import com.sun.istack.internal.NotNull;

import javolution.util.FastMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.annotation.Nonnull;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Future;
/**
 * This class is for controlling players.
 * 
 * @author -Nemesiss-, ATracer, xavier, Sarynth, RotO, xTz, KID modified by Sippolo
 */
public class PlayerController extends CreatureController<Player> {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	private boolean isInShutdownProgress;
	private long lastAttackMilis = 0;
	private long lastAttackedMilis = 0;
	private int stance = 0;
	private FastMap<Integer, VisibleObject> autoPortals = new FastMap<Integer, VisibleObject>();

	@Override
	public void see(VisibleObject object) {
		super.see(object);
		if (object instanceof Player) {
			Player player = (Player) object;
			PacketSendUtility.sendPacket(getOwner(), new SM_PLAYER_INFO(player, getOwner().isAggroIconTo(player)));
			PacketSendUtility.sendPacket(getOwner(), new SM_MOTION(player.getObjectId(), player.getMotions().getActiveMotions()));
			if (player.isUseRobot() || player.getRobotId() != 0) {
				PacketSendUtility.sendPacket(getOwner(), new SM_USE_ROBOT(player, getRobotInfo(player).getRobotId()));
			} if (player.isTransformed()) {
				TeleportService2.playerTransformation(getOwner());
				TeleportService2.instanceTransformation(getOwner());
				TeleportService2.archdaevaTransformation(getOwner());
				PacketSendUtility.broadcastPacketAndReceive(player, new SM_TRANSFORM(player, player.getTransformedModelId(), true, player.getTransformedItemId()));
            	PacketSendUtility.broadcastPacketAndReceive(player, new SM_TRANSFORM(player, true));
            } if (player.isInPlayerMode(PlayerMode.RIDE)) {
				PacketSendUtility.sendPacket(getOwner(), new SM_EMOTION(player, EmotionType.RIDE, 0, player.ride.getNpcId()));
			} else if (player.getPet() != null) {
				LoggerFactory.getLogger(PlayerController.class).debug("Player " + getOwner().getName() + " sees " + object.getName() + " that has Toypet");
				PacketSendUtility.sendPacket(getOwner(), new SM_PET(3, player.getPet()));
			} else if (player.getMinion() != null) {
				LoggerFactory.getLogger(PlayerController.class).debug("Player " + getOwner().getName() + " sees " + object.getName() + " that has Minion");
				//PacketSendUtility.broadcastPacketAndReceive(player, new SM_MINIONS(5, player.getMinion().getCommonData(), 0));
			}
			player.getEffectController().sendEffectIconsTo(getOwner());
		} else if (object instanceof Kisk) {
			Kisk kisk = ((Kisk) object);
			PacketSendUtility.sendPacket(getOwner(), new SM_NPC_INFO(kisk, getOwner()));
			if (getOwner().getRace() == kisk.getOwnerRace()) {
				PacketSendUtility.sendPacket(getOwner(), new SM_KISK_UPDATE(kisk));
			}
		} else if (object instanceof Npc) {
			Npc npc = ((Npc) object);
			PacketSendUtility.sendPacket(getOwner(), new SM_NPC_INFO(npc, getOwner()));
			PacketSendUtility.sendPacket(getOwner(), new SM_EMOTION_NPC(npc, npc.getState(), EmotionType.SELECT_TARGET));
			PacketSendUtility.sendPacket(getOwner(), new SM_HEADING_UPDATE(object.getObjectId(), (byte) object.getHeading()));
			if (!npc.getEffectController().isEmpty()) {
				npc.getEffectController().sendEffectIconsTo(getOwner());
			}
		} else if (object instanceof Summon) {
			Summon npc = ((Summon) object);
			PacketSendUtility.sendPacket(getOwner(), new SM_NPC_INFO(npc));
			if (!npc.getEffectController().isEmpty()) {
				npc.getEffectController().sendEffectIconsTo(getOwner());
			}
		} else if (object instanceof Gatherable || object instanceof StaticObject) {
			PacketSendUtility.sendPacket(getOwner(), new SM_GATHERABLE_INFO(object));
		} else if (object instanceof Pet) {
			PacketSendUtility.sendPacket(getOwner(), new SM_PET(3, (Pet) object));
		} else if (object instanceof Minion) {
		PacketSendUtility.sendPacket(getOwner(), new SM_MINIONS(5, (Minion) object));
	    }
	}
	
	private RobotInfo getRobotInfo(Player player) {
		ItemTemplate template = player.getEquipment().getMainHandWeapon().getItemSkinTemplate();
		return DataManager.ROBOT_DATA.getRobotInfo(template.getRobotId());
	}

	@Override
	public void notSee(VisibleObject object, boolean isOutOfRange) {
		super.notSee(object, isOutOfRange);
		if (object instanceof Pet) {
			PacketSendUtility.sendPacket(getOwner(), new SM_PET(4, (Pet) object));
		} else if (object instanceof Minion) {
            PacketSendUtility.sendPacket(getOwner(), new SM_MINIONS(6, (Minion) object));
        } else {
			PacketSendUtility.sendPacket(getOwner(), new SM_DELETE(object, isOutOfRange ? 0 : 15));
		}
	}

	public void updateNearbyQuests() {
		HashMap<Integer, Integer> nearbyQuestList = new HashMap<>();
		for (int questId : getOwner().getPosition().getMapRegion().getParent().getQuestIds()) {
			int diff = 0;
			if (questId <= 0xFFFF) {
				diff = QuestService.getLevelRequirement(questId, getOwner().getCommonData().getLevel());
			}
			if (diff <= 2 && QuestService.checkStartConditions(new QuestEnv(null, getOwner(), questId, 0), false)) {
				nearbyQuestList.put(questId, diff);
			}
		}
		PacketSendUtility.sendPacket(getOwner(), new SM_NEARBY_QUESTS(nearbyQuestList));
	}

	@Override
	public void onEnterZone(ZoneInstance zone) {
		Player player = getOwner();
		if ((!zone.canRide()) && (player.isInPlayerMode(PlayerMode.RIDE))) {
			player.unsetPlayerMode(PlayerMode.RIDE);
		} if (zone.getZoneTemplate().getZoneType().equals(ZoneClassName.FORT) && (player.isInState(CreatureState.FLYING))) {
		   /**
		    * If a player enter in zone "Panesterra Fortress"
			* of while player flying, then the system will landing the player.
			*/
			switch (player.getWorldId()) {
			    case 400020000: //Belus.
			    case 400040000: //Aspida.
			    case 400050000: //Atanatos.
			    case 400060000: //Disillon.
					player.setFlyState(0);
					player.getFlyController().endFly(true);
					player.unsetState(CreatureState.FLYING);
					//You cannot fly in this area.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FLYING_FORBIDDEN_ZONE);
				    PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.LAND, 0, 0), true);
				break;
			}
		}
		InstanceService.onEnterZone(player, zone);
		if (zone.getAreaTemplate().getZoneName() == null) {
			log.error("No name found for a Zone in the map " + zone.getAreaTemplate().getWorldId());
		} else {
			QuestEngine.getInstance().onEnterZone(new QuestEnv(null, player, 0, 0), zone.getAreaTemplate().getZoneName());
		}
	   /**
		* These instances portal are "spawn & reversed" to the opposite race.
		* If a player enter in fews area, a portal will appear automatically.
		* These portals are only 2 minute ingame before despawn.
		* PS: Please, check "portal/AI2" for these portal.
		*/
		SpawnTemplate template;
		if (zone.getAreaTemplate().getZoneName() == ZoneName.get("REIAN_REFUGEE_CAMP_210070000")) {
			switch (player.getRace()) {
		        //Rentus Base
				case ELYOS:
			        template = SpawnEngine.addNewSingleTimeSpawn(210070000, 730399, 1147.6155f, 800.88049f, 563.40173f, (byte) 0);
                    template.setEntityId(885);
                    autoPortals.put(730399, SpawnEngine.spawnObject(template, 1));
			    break;
				//Occupied Rentus Base
			    case ASMODIANS:
			        template = SpawnEngine.addNewSingleTimeSpawn(210070000, 832992, 1147.6155f, 800.88049f, 563.40173f, (byte) 0);
                    template.setEntityId(885);
                    autoPortals.put(832992, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("RENTUS_RECOVERY_BASE_220080000")) {
			switch (player.getRace()) {
		        //Occupied Rentus Base
				case ELYOS:
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 832991, 1973.3156f, 2017.3612f, 329.13571f, (byte) 0);
                    template.setEntityId(900);
					autoPortals.put(832991, SpawnEngine.spawnObject(template, 1));
			    break;
				//Rentus Base
			    case ASMODIANS:
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 730399, 1973.3156f, 2017.3612f, 329.13571f, (byte) 0);
                    template.setEntityId(900);
                    autoPortals.put(730399, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("RUINHOLD_SCATTERINGS_210070000")) {
			switch (player.getRace()) {
			    case ELYOS:
			        //Tiamat Stronghold
					template = SpawnEngine.addNewSingleTimeSpawn(210070000, 832995, 93.335602f, 1474.6055f, 491.90103f, (byte) 0);
                    template.setEntityId(306);
                    autoPortals.put(832995, SpawnEngine.spawnObject(template, 1));
					//Dragon Lord Refuge
			        template = SpawnEngine.addNewSingleTimeSpawn(210070000, 832998, 103.8532f, 1461.7725f, 494.52884f, (byte) 0);
                    template.setEntityId(865);
                    autoPortals.put(832998, SpawnEngine.spawnObject(template, 1));
			    break;
			    case ASMODIANS:
					//[Anguished] Dragon Lord Refuge
			        template = SpawnEngine.addNewSingleTimeSpawn(210070000, 832997, 103.8532f, 1461.7725f, 494.52884f, (byte) 0);
                    template.setEntityId(865);
                    autoPortals.put(832997, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("DRAGONFALLS_GLARE_220080000")) {
			switch (player.getRace()) {
			    case ELYOS:
			        //[Anguished] Dragon Lord Refuge
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 832997, 2862.9939f, 1679.4772f, 308.87949f, (byte) 0);
                    template.setEntityId(422);
                    autoPortals.put(832997, SpawnEngine.spawnObject(template, 1));
			    break;
			    case ASMODIANS:
				    //Tiamat Stronghold
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 832996, 2845.8596f, 1659.2727f, 302.67017f, (byte) 0);
                    template.setEntityId(364);
                    autoPortals.put(832996, SpawnEngine.spawnObject(template, 1));
					//Dragon Lord Refuge
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 832998, 2862.9939f, 1679.4772f, 308.87949f, (byte) 0);
                    template.setEntityId(422);
                    autoPortals.put(832998, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("DANUAR_SANCTUARY_INSPECTOR_210070000")) {
			switch (player.getRace()) {
		        //Danuar Sanctuary
				case ELYOS:
			        template = SpawnEngine.addNewSingleTimeSpawn(210070000, 731570, 2097.4739f, 2276.1729f, 294.90442f, (byte) 0);
                    template.setEntityId(888);
                    autoPortals.put(731570, SpawnEngine.spawnObject(template, 1));
			    break;
				//[Seized] Danuar Sanctuary
			    case ASMODIANS:
					template = SpawnEngine.addNewSingleTimeSpawn(210070000, 731549, 2097.4739f, 2276.1729f, 294.90442f, (byte) 0);
                    template.setEntityId(888);
                    autoPortals.put(731549, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("DANUAR_SANCTUARY_INVESTIGATION_AREA_220080000")) {
			switch (player.getRace()) {
		        //[Seized] Danuar Sanctuary
				case ELYOS:
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 731549, 1667.7465f, 562.70654f, 258.88382f, (byte) 0);
                    template.setEntityId(407);
                    autoPortals.put(731549, SpawnEngine.spawnObject(template, 1));
			    break;
				//Danuar Sanctuary
			    case ASMODIANS:
			        template = SpawnEngine.addNewSingleTimeSpawn(220080000, 731570, 1667.7465f, 562.70654f, 258.88382f, (byte) 0);
                    template.setEntityId(407);
                    autoPortals.put(731570, SpawnEngine.spawnObject(template, 1));
			    break;
			}
		}
	   /**
		* For Protect City.
		* If a opposite race player enter on these zone ==> return to "Bind Location"
		*/
		if (player.getAccessLevel() == 0) {
		    if (zone.getAreaTemplate().getZoneName() == ZoneName.get("AZPHEL_SANCTUARY_220110000")) {
				switch (player.getRace()) {
					case ELYOS:
					    TeleportService2.moveToBindLocation(player, true);
					break;
				}
			} else if (zone.getAreaTemplate().getZoneName() == ZoneName.get("ARIEL_SANCTUARY_210100000")) {
				switch (player.getRace()) {
					case ASMODIANS:
					    TeleportService2.moveToBindLocation(player, true);
					break;
				}
			}
		}
	}

	@Override
	public void onLeaveZone(ZoneInstance zone) {
		Player player = getOwner();
		InstanceService.onLeaveZone(player, zone);
		ZoneName zoneName = zone.getAreaTemplate().getZoneName();
		if (zoneName == null) {
			log.warn("No name for zone template in " + zone.getAreaTemplate().getWorldId());
			return;
		}
		QuestEngine.getInstance().onLeaveZone(new QuestEnv(null, player, 0, 0), zoneName);
	}

	/**
	 * {@inheritDoc} Should only be triggered from one place (life stats)
	 */
	// TODO [AT] move
	public void onEnterWorld() {
		InstanceService.onEnterInstance(getOwner());
		TeleportService2.playerTransformation(getOwner());
		TeleportService2.instanceTransformation(getOwner());
		TeleportService2.archdaevaTransformation(getOwner());
		if (getOwner().getPosition().getWorldMapInstance().getParent().isExceptBuff()) {
			getOwner().getEffectController().removeAllEffects();
		}
		for (Effect ef : getOwner().getEffectController().getAbnormalEffects()) {
			if (ef.isDeityAvatar()) {
				//Remove abyss transformation if worldtype != "Abyss" && worldtype != "Balaurea" && worldtype != "Panesterra"
				if (getOwner().getWorldType() != WorldType.ABYSS &&
				    getOwner().getWorldType() != WorldType.BALAUREA &&
					getOwner().getWorldType() != WorldType.PANESTERRA || getOwner().isInInstance()) {
					ef.endEffect();
					getOwner().getEffectController().clearEffect(ef);
				}
			} else if (ef.getSkillTemplate().getDispelCategory() == DispelCategoryType.NPC_BUFF) {
				ef.endEffect();
				getOwner().getEffectController().clearEffect(ef);
			}
		}
	}

	// TODO [AT] move
	public void onLeaveWorld() {
		ProtectorConquerorService.getInstance().onLeaveMap(getOwner());
		InstanceService.onLeaveInstance(getOwner());
	}

	public void onDie(Creature lastAttacker, boolean showPacket) {
		Player player = this.getOwner();
		player.getController().cancelCurrentSkill();
		player.setRebirthRevive(getOwner().haveSelfRezEffect());
		showPacket = player.hasResurrectBase() ? false : showPacket;
		Creature master = lastAttacker.getMaster();
		if ((PvPConfig.ENABLE_KILLING_SPREE_SYSTEM) && (getOwner().getRawKillCount() > 0)) {
			if ((master instanceof Npc)) {
				PvPSpreeService.cancelSpree(player, (Npc) master, false);
			} if (((master instanceof Player)) && (master.getRace() != player.getRace())) {
				PvPSpreeService.cancelSpree(player, (Player) master, true);
			}
		} if (EventsConfig.ENABLE_CRAZY) {
			if (((master instanceof Player)) && (master.getRace() != player.getRace())) {
				CrazyDaevaService.getInstance().crazyOnDie(player, (Player) master, true);
			}
		}
		AbyssRank ar = player.getAbyssRank();
		if (AbyssService.isOnPvpMap(player) && ar != null) {
			if (ar.getRank().getId() >= 1) {
				AbyssService.rankedKillAnnounce(player);
			}
		} if (DuelService.getInstance().isDueling(player.getObjectId())) {
			if (master != null && DuelService.getInstance().isDueling(player.getObjectId(), master.getObjectId())) {
				DuelService.getInstance().loseDuel(player);
				player.getEffectController().removeAbnormalEffectsByTargetSlot(SkillTargetSlot.DEBUFF);
				player.getLifeStats().setCurrentHp(player.getLifeStats().getMaxHp() / 3);
				return;
			}
			DuelService.getInstance().loseDuel(player);
		} if (FFAService.getInstance().isInArena(player) && player.isFFA()) {
			player.getAggroList().clear();
			FFAService.getInstance().onDie(player, master);
			return;
		} if (player.isBandit()) {
			player.getAggroList().clear();
			BanditService.getInstance().onDie(player, master);
			return;
		} if (player.getBattleground() != null &&
		    player.getBattleground() instanceof DeathmatchBg ||
			player.getBattleground() != null &&
			player.getBattleground() instanceof SoloSurvivorBg) {
			player.getAggroList().clear();
			player.getBattleground().onDie(player, master);
			return;
		}
		Summon summon = player.getSummon();
		if (summon != null) {
			SummonsService.doMode(SummonMode.RELEASE, summon, UnsummonType.UNSPECIFIED);
		}
		Pet pet = player.getPet();
		if (pet != null) {
			PetSpawnService.dismissPet(player, true);
		}
		
		Minion minion = player.getMinion();
		if (minion != null) {
			MinionService.getInstance().despawnMinion(player, true);
		}
		
		if (player.isInState(CreatureState.FLYING)) {
			player.setIsFlyingBeforeDeath(true);
		}
		player.setPlayerMode(PlayerMode.RIDE, null);
		player.unsetState(CreatureState.RESTING);
		player.unsetState(CreatureState.FLOATING_CORPSE);
		player.unsetState(CreatureState.FLYING);
		player.unsetState(CreatureState.GLIDING);
		player.setFlyState(0);
		if (player.isInInstance() &&
		    !FFAService.getInstance().isInArena(player) ||
			player.getBattleground() == null ||
			!player.getBattleground().is1v1()) {
			if (player.getPosition().getWorldMapInstance().getInstanceHandler().onDie(player, lastAttacker)) {
				super.onDie(lastAttacker);
				return;
			}
		}
		MapRegion mapRegion = player.getPosition().getMapRegion();
		if (mapRegion != null && mapRegion.onDie(lastAttacker, getOwner())) {
			return;
		}
		this.doReward();
		if (master instanceof Npc || master == player) {
			if (player.getLevel() > 4 &&
			   !isNoDeathPenaltyInEffect() &&
			   !isNoDeathPenaltyReduceInEffect() &&
			   !isDeathPenaltyReduceInEffect()) {
				player.getCommonData().calculateExpLoss();
			}
		}
		super.onDie(lastAttacker);
		sendDieFromCreature(lastAttacker, showPacket);
		QuestEngine.getInstance().onDie(new QuestEnv(null, player, 0, 0));
		if (player.isInGroup2()) {
			player.getPlayerGroup2().sendPacket(SM_SYSTEM_MESSAGE.STR_MSG_COMBAT_FRIENDLY_DEATH(player.getName()), new ExcludePlayerFilter(player));
		}
	}
	
	@Override
	public void onDie(Creature lastAttacker) {
		this.onDie(lastAttacker, true);
	}

	public void sendDie() {
		sendDieFromCreature(getOwner(), true);
	}

	private void sendDieFromCreature(@NotNull Creature lastAttacker, boolean showPacket) {
		Player player = this.getOwner();
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.DIE, 0, player.equals(lastAttacker) ? 0 : lastAttacker.getObjectId()), true);
		if (showPacket) {
			if (player.isInInstance()) {
				PacketSendUtility.sendPacket(player, new SM_DIE(player.haveSelfRezEffect(), player.haveSelfRezItem(), 0, 8, false));
			} else {
				int kiskTimeRemaining = (player.getKisk() != null ? player.getKisk().getRemainingLifetime() : 0);
				PacketSendUtility.sendPacket(player, new SM_DIE(player.canUseRebirthRevive(), player.haveSelfRezItem(), kiskTimeRemaining, 0, isInvader(player)));
			}
		}
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_COMBAT_MY_DEATH);
	}

	private boolean isInvader(Player player) {
        if (player.getRace().equals(Race.ASMODIANS)) {
            return player.getWorldId() == 210060000; //Theobomos.
        } else {
            return player.getWorldId() == 220050000; //Brusthonin.
        }
    }

	@Override
	public void doReward() {
		PvpService.getInstance().doReward(getOwner());
	}

	@Override
	public void onBeforeSpawn() {
		this.onBeforeSpawn(true);
	}

	public void onBeforeSpawn(boolean blink) {
        super.onBeforeSpawn();
        if (blink) {
            startProtectionActiveTask();
        } if (getOwner().getIsFlyingBeforeDeath()) {
            getOwner().unsetState(CreatureState.FLOATING_CORPSE);
        } else {
            getOwner().unsetState(CreatureState.DEAD);
        }
        getOwner().setState(CreatureState.ACTIVE);
    }

	@Override
	public void attackTarget(Creature target, int time) {
		PlayerGameStats gameStats = getOwner().getGameStats();
		if (!RestrictionsManager.canAttack(getOwner(), target)) {
			return;
		} if (!MathUtil.isInAttackRange(getOwner(), target, (float) (getOwner().getGameStats().getAttackRange().getCurrent() / 1000f) + 1)) {
			return;
		} if (!GeoService.getInstance().canSee(getOwner(), target)) {
			PacketSendUtility.sendPacket(getOwner(), SM_SYSTEM_MESSAGE.STR_ATTACK_OBSTACLE_EXIST);
			return;
		}
		int attackSpeed = gameStats.getAttackSpeed().getCurrent();
		long milis = System.currentTimeMillis();
		if (milis - lastAttackMilis < attackSpeed) {
			return;
		}
		lastAttackMilis = milis;
		super.attackTarget(target, time);
	}

	@Override
	public void onAttack(Creature creature, int skillId, TYPE type, int damage, boolean notifyAttack, LOG log) {
		if (getOwner().getLifeStats().isAlreadyDead()) {
			return;
		} else if (getOwner().isInvul() || getOwner().isProtectionActive()) {
			damage = 0;
		}
		cancelUseItem();
		cancelGathering();
		super.onAttack(creature, skillId, type, damage, notifyAttack, log);
		PacketSendUtility.broadcastPacket(getOwner(), new SM_ATTACK_STATUS(getOwner(), type, skillId, damage, log), true);
		lastAttackedMilis = System.currentTimeMillis();
	}

	/**
	 * @param skillId
	 * @param targetType
	 * @param x
	 * @param y
	 * @param z
	 */
	public void useSkill(int skillId, int targetType, float x, float y, float z, int time) {
		Player player = getOwner();

		Skill skill = SkillEngine.getInstance().getSkillFor(player, skillId, player.getTarget());

		if (skill != null) {
			if (!RestrictionsManager.canUseSkill(player, skill))
				return;

			skill.setTargetType(targetType, x, y, z);
			skill.setHitTime(time);
			skill.useSkill();
		}
	}

	/**
	 * @param template
	 * @param targetType
	 * @param x
	 * @param y
	 * @param z
	 * @param clientHitTime
	 */
	public void useSkill(SkillTemplate template, int targetType, float x, float y, float z, int clientHitTime, int skillLevel) {
		Player player = getOwner();

		Skill skill = SkillEngine.getInstance().getSkillFor(player, template, player.getTarget());
		if ((skill == null) && (player.isTransformed())) {
			SkillPanel panel = DataManager.PANEL_SKILL_DATA.getSkillPanel(player.getTransformModel().getPanelId());
			if ((panel != null) && (panel.canUseSkill(template.getSkillId(), skillLevel))) {
				skill = SkillEngine.getInstance().getSkillFor(player, template, player.getTarget(), skillLevel);
			}
		}

		if (skill != null) {
			if (!RestrictionsManager.canUseSkill(player, skill))
				return;

			skill.setTargetType(targetType, x, y, z);
			skill.setHitTime(clientHitTime);
			skill.useSkill();
			QuestEnv env = new QuestEnv(player.getTarget(), player, 0, 0);
			QuestEngine.getInstance().onUseSkill(env, template.getSkillId());
		}
	}

	@Override
	public void onMove() {
		getOwner().getObserveController().notifyMoveObservers();
		super.onMove();
	}

	@Override
	public void onStopMove() {
		PlayerMoveTaskManager.getInstance().removePlayer(getOwner());
		getOwner().getObserveController().notifyMoveObservers();
		getOwner().getMoveController().setInMove(false);
		cancelCurrentSkill();
		updateZone();
		super.onStopMove();
	}

	@Override
	public void onStartMove() {
		getOwner().getMoveController().setInMove(true);
		PlayerMoveTaskManager.getInstance().addPlayer(getOwner());
		cancelUseItem();
		cancelCurrentSkill();
		super.onStartMove();
	}

	@Override
	public void cancelCurrentSkill() {
		if (getOwner().getCastingSkill() == null) {
			return;
		}

		Player player = getOwner();
		Skill castingSkill = player.getCastingSkill();
		castingSkill.cancelCast();
		player.removeSkillCoolDown(castingSkill.getSkillTemplate().getDelayId());
		player.setCasting(null);
		player.setNextSkillUse(0);
		if (castingSkill.getSkillMethod() == SkillMethod.CAST) {
			PacketSendUtility.broadcastPacket(player, new SM_SKILL_CANCEL(player, castingSkill.getSkillTemplate().getSkillId()), true);
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_SKILL_CANCELED);
		}
		else if (castingSkill.getSkillMethod() == SkillMethod.ITEM) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_ITEM_CANCELED(new DescriptionId(castingSkill.getItemTemplate().getNameId())));
			player.removeItemCoolDown(castingSkill.getItemTemplate().getUseLimits().getDelayId());
			PacketSendUtility.broadcastPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), castingSkill.getFirstTarget().getObjectId(), castingSkill.getItemObjectId(),
				castingSkill.getItemTemplate().getTemplateId(), 0, 3, 0), true);
		}
	}

	@Override
	public void cancelUseItem() {
		Player player = getOwner();
		Item usingItem = player.getUsingItem();
		player.setUsingItem(null);
		if (hasTask(TaskId.ITEM_USE)) {
			cancelTask(TaskId.ITEM_USE);
			PacketSendUtility.broadcastPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), usingItem == null ? 0 : usingItem.getObjectId(), usingItem == null ? 0
				: usingItem.getItemTemplate().getTemplateId(), 0, 3, 0), true);
		}
	}

	public void cancelGathering() {
		Player player = getOwner();
		if (player.getTarget() instanceof Gatherable) {
			Gatherable g = (Gatherable) player.getTarget();
			g.getController().finishGathering(player);
		}
	}

	public void updatePassiveStats() {
		Player player = getOwner();
		for (PlayerSkillEntry skillEntry : player.getSkillList().getAllSkills()) {
			Skill skill = SkillEngine.getInstance().getSkillFor(player, skillEntry.getSkillId(), player.getTarget());
			if (skill != null && skill.isPassive()) {
				skill.useSkill();
			}
		}
	}

	@Override
	public Player getOwner() {
		return (Player) super.getOwner();
	}

	@Override
	public void onRestore(HealType healType, int value) {
		super.onRestore(healType, value);
		switch (healType) {
			case DP:
				getOwner().getCommonData().addDp(value);
			break;
		}
	}

	/**
	 * @param player
	 * @return
	 */
	// TODO [AT] move to Player
	public boolean isDueling(Player player) {
		return DuelService.getInstance().isDueling(player.getObjectId(), getOwner().getObjectId());
	}

	// TODO [AT] rename or remove
	public boolean isInShutdownProgress() {
		return isInShutdownProgress;
	}

	// TODO [AT] rename or remove
	public void setInShutdownProgress(boolean isInShutdownProgress) {
		this.isInShutdownProgress = isInShutdownProgress;
	}

	@Override
	public void onDialogSelect(int dialogId, Player player, int questId, int extendedRewardIndex, int unk) {//TODO unk need to be figure out
		switch (dialogId) {
			case 2:
				PacketSendUtility.sendPacket(player, new SM_PRIVATE_STORE(getOwner().getStore(), player));
			break;
		}
	}
	
	public void upgradePlayer() {
		Player player = getOwner();
		byte level = player.getLevel();
		PlayerStatsTemplate statsTemplate = DataManager.PLAYER_STATS_DATA.getTemplate(player);
		player.setPlayerStatsTemplate(statsTemplate);
		player.getLifeStats().synchronizeWithMaxStats();
		player.getLifeStats().updateCurrentStats();
		PacketSendUtility.broadcastPacket(player, new SM_LEVEL_UPDATE(player.getObjectId(), 0, level), true);
		if (HTMLConfig.ENABLE_GUIDES) {
			HTMLService.sendGuideHtml(player);
		}
		ClassChangeService.showClassChangeDialog(player);
		QuestEngine.getInstance().onLvlUp(new QuestEnv(null, player, 0, 0));
		player.getController().updateZone();
		player.getController().updateNearbyQuests();
		player.getController().updatePassiveStats();
		PacketSendUtility.sendPacket(player, new SM_STATS_INFO(player));
		if (level == 10) {
			CraftSkillUpdateService.getInstance().setMorphRecipe(player);
			//Youâ€™ve reached the level where you can join a legion.
			//Use the legion search to find the legion you want.
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GUILD_CAN_JOIN_LEVEL);
		}
		//Stigma 5.1
		//Characters will receive "Chargeable Stigma" bundles based on their class and level.
		//http://static.ncsoft.com/aion/store/PatchNotes/AION_Patch_Notes_110916.pdf
		if (level == 20) {
			ItemService.addItem(player, 188053787, 1); //Stigma Support Bundle.
			//Open "Stigma Slot" + "Completed" quest auto.
			if (!player.isCompleteQuest(1929) && player.getRace() == Race.ELYOS) {
				ClassChangeService.completeQuest(player, 1929);
			} else if (!player.isCompleteQuest(2900) && player.getRace() == Race.ASMODIANS) {
				ClassChangeService.completeQuest(player, 2900);
			}
        } if (level == 30) {
			ItemService.addItem(player, 188053787, 2); //Stigma Support Bundle.
        } if (level == 40) {
			ItemService.addItem(player, 188053787, 3); //Stigma Support Bundle.
        } if (level == 45) {
			ItemService.addItem(player, 188053787, 3); //Stigma Support Bundle.
			ItemService.addItem(player, 188053785, 1); //Greater Stigma Bundle.
        } if (level == 50) {
			ItemService.addItem(player, 188053787, 3); //Stigma Support Bundle.
			ItemService.addItem(player, 188053785, 2); //Greater Stigma Bundle.
        } if (level == 55) {
			ItemService.addItem(player, 188053787, 3); //Stigma Support Bundle.
			ItemService.addItem(player, 188053785, 2); //Greater Stigma Bundle.
			ItemService.addItem(player, 188053786, 1); //Major Stigma Bundle.
        }
		//Essence Cores 5.3
		if (level == 66) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CPSTONE_OPEN_SLOT);
        } if (level == 68) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CPSTONE_OPEN_SLOT);
        } if (level == 70) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CPSTONE_OPEN_SLOT);
        }
		SkillLearnService.addNewSkills(player);
		PacketSendUtility.sendPacket(player, new SM_SKILL_LIST(player, player.getSkillList().getBasicSkills()));
		if (player.isInTeam()) {
			TeamEffectUpdater.getInstance().startTask(player);
		} if (player.isLegionMember()) {
			LegionService.getInstance().updateMemberInfo(player);
		}
		
	   /**
		* http://static.ncsoft.com/aion/store/PatchNotes/AION_Patch_Notes_061715.pdf
		* Mentor status now cancels automatically as soon as the lowest level group member reaches level 51
		*/
		if (level == 51) {
			PlayerGroupService.stopMentoring(player);
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CANT_BE_MENTEE_BY_LEVEL_LIMIT);
		} if (level == 66) { //TODO This is temporary solution, player need to complete quests to become highdeava, i guess
			player.getCommonData().setArchDaeva(true);
		} if (level >= 66 && level <= 75) {
			reachedPlayerLvl(player);
		}
		player.getNpcFactions().onLevelUp();
		CreativityEssenceService.getInstance().pointPerLevel(player);
	}

	public static final void reachedPlayerLvl(final Player player) {
	    World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player players) {
				//"Player Name" has reached level %1.
				byte playerLevel = player.getLevel();
				PacketSendUtility.sendPacket(players, new SM_SYSTEM_MESSAGE(1300086, player.getName(), playerLevel));
			}
		});
	}

	/**
	 * After entering game player char is "blinking" which means that it's in under some protection, after making an
	 * action char stops blinking. - Starts protection active - Schedules task to end protection
	 */
	public void startProtectionActiveTask() {
		if (!getOwner().isProtectionActive()) {
			TeleportService2.playerTransformation(getOwner());
			TeleportService2.instanceTransformation(getOwner());
			TeleportService2.archdaevaTransformation(getOwner());
			getOwner().setVisualState(CreatureVisualState.BLINKING);
			AttackUtil.cancelCastOn((Creature) getOwner());
            AttackUtil.removeTargetFrom((Creature) getOwner());
			PacketSendUtility.broadcastPacket(getOwner(), new SM_PLAYER_STATE(getOwner()), true);
			Future<?> task = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					stopProtectionActiveTask();
				}
			}, 60000);
			addTask(TaskId.PROTECTION_ACTIVE, task);
		}
	}

	/**
	 * Stops protection active task after first move or use skill
	 */
	public void stopProtectionActiveTask() {
		cancelTask(TaskId.PROTECTION_ACTIVE);
		Player player = getOwner();
		if (player != null && player.isSpawned()) {
			player.unsetVisualState(CreatureVisualState.BLINKING);
			PacketSendUtility.broadcastPacket(player, new SM_PLAYER_STATE(player), true);
			notifyAIOnMove();
		}
	}

	/**
	 * When player arrives at destination point of flying teleport
	 */
	public void onFlyTeleportEnd() {
		Player player = getOwner();
		if (player.isInPlayerMode(PlayerMode.WINDSTREAM)) {
			player.unsetPlayerMode(PlayerMode.WINDSTREAM);
			player.getLifeStats().triggerFpReduce();
			player.unsetState(CreatureState.FLYING);
			player.setState(CreatureState.ACTIVE);
			player.setState(CreatureState.GLIDING);
			player.getGameStats().updateStatsAndSpeedVisually();
		}
		else {
			player.unsetState(CreatureState.FLIGHT_TELEPORT);
			player.setFlightTeleportId(0);

			if (SecurityConfig.ENABLE_FLYPATH_VALIDATOR) {
				long diff = (System.currentTimeMillis() - player.getFlyStartTime());
				FlyPathEntry path = player.getCurrentFlyPath();

				if (player.getWorldId() != path.getEndWorldId()) {
					AuditLogger.info(player, "Player tried to use flyPath #" + path.getId() + " from not native start world " + player.getWorldId() + ". expected " + path.getEndWorldId());
				}

				if (diff < path.getTimeInMs()) {
					AuditLogger.info(player, "Player " + player.getName() + " used flypath bug " + diff + " instead of " + path.getTimeInMs());
				}
				player.setCurrentFlypath(null);
			}

			player.setFlightDistance(0);
			player.setState(CreatureState.ACTIVE);
			updateZone();
		}
	}

	public boolean addItems(int itemId, int count) {
		return ItemService.addQuestItems(getOwner(), Collections.singletonList(new QuestItems(itemId, count)));
	}

	public void startStance(final int skillId) {
		stance = skillId;
	}

	public void stopStance() {
		getOwner().getEffectController().removeEffect(stance);
		PacketSendUtility.sendPacket(getOwner(), new SM_PLAYER_STANCE(getOwner(), 0));
		stance = 0;
	}

	public int getStanceSkillId() {
		return stance;
	}

	public boolean isUnderStance() {
		return stance != 0;
	}

	public void updateSoulSickness(int skillId) {
		Player player = getOwner();
		House house = player.getActiveHouse();
		if (house != null) {
			switch (house.getHouseType()) {
				case MANSION:
				case ESTATE:
				case PALACE:
					return;
			}
		} if (!player.havePermission(MembershipConfig.DISABLE_SOULSICKNESS)) {
			int deathCount = player.getCommonData().getDeathCount();
			if (deathCount < 10) {
				deathCount++;
				player.getCommonData().setDeathCount(deathCount);
			} if (skillId == 0) {
				skillId = 8291;
			}
			SkillEngine.getInstance().getSkill(player, skillId, deathCount, player).useSkill();
		}
	}

	/**
	 * Player is considered in combat if he's been attacked or has attacked less or equal 10s before
	 * 
	 * @return true if the player is actively in combat
	 */
	public boolean isInCombat() {
		return (((System.currentTimeMillis() - lastAttackedMilis) <= 10000) || ((System.currentTimeMillis() - lastAttackMilis) <= 10000));
	}

	public boolean isNoDeathPenaltyInEffect() {
		Iterator<Effect> iterator = getOwner().getEffectController().iterator();
		while (iterator.hasNext()) {
			Effect effect = (Effect) iterator.next();
			if (effect.isNoDeathPenalty()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNoDeathPenaltyReduceInEffect() {
		Iterator<Effect> iterator = getOwner().getEffectController().iterator();
		while (iterator.hasNext()) {
			Effect effect = (Effect) iterator.next();
			if (effect.isNoDeathPenaltyReduce()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDeathPenaltyReduceInEffect() {
		Iterator<Effect> iterator = getOwner().getEffectController().iterator();
		while (iterator.hasNext()) {
			Effect effect = (Effect) iterator.next();
			if (effect.isDeathPenaltyReduce()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNoResurrectPenaltyInEffect() {
		Iterator<Effect> iterator = getOwner().getEffectController().iterator();
		while (iterator.hasNext()) {
			Effect effect = (Effect) iterator.next();
			if (effect.isNoResurrectPenalty()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isHiPassInEffect() {
		Iterator<Effect> iterator = getOwner().getEffectController().iterator();
		while (iterator.hasNext()) {
			Effect effect = (Effect) iterator.next();
			if (effect.isHiPass()) {
				return true;
			}
		}
		return false;
	}
}
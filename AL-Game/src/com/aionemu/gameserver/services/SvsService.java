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
package com.aionemu.gameserver.services;

import com.aionemu.commons.services.CronService;
import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.configs.schedule.SvsSchedule;
import com.aionemu.gameserver.configs.schedule.SvsSchedule.Svs;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.svs.SvsLocation;
import com.aionemu.gameserver.model.svs.SvsStateType;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.spawns.svsspawns.SvsSpawnTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.svsservice.Gate;
import com.aionemu.gameserver.services.svsservice.Panesterra;
import com.aionemu.gameserver.services.svsservice.SvsStartRunnable;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class SvsService
{
	private SvsSchedule svsSchedule;
	private Map<Integer, SvsLocation> svs;
	private static Logger log = LoggerFactory.getLogger(SvsService.class);
	private static final int duration = CustomConfig.SVS_DURATION;
	//Transidium Annex 4.7
	private FastMap<Integer, VisibleObject> advanceCorridor = new FastMap<Integer, VisibleObject>();
	private final Map<Integer, Panesterra<?>> activeSvs = new FastMap<Integer, Panesterra<?>>().shared();
	
	public void initSvsLocations() {
		if (CustomConfig.SVS_ENABLED) {
			svs = DataManager.SVS_DATA.getSvsLocations();
			for (SvsLocation loc: getSvsLocations().values()) {
				spawn(loc, SvsStateType.PEACE);
			}
			log.info("[PanesterraService] Loaded " + svs.size() + " panesterra locations.");
		} else {
			log.info("[PanesterraService] Panesterra is disabled in config...");
			svs = Collections.emptyMap();
		}
	}
	
	public void initSvs() {
		if (CustomConfig.SVS_ENABLED) {
			log.info("[PanesterraService] is initialized...");
		    svsSchedule = SvsSchedule.load();
		    for (Svs svs: svsSchedule.getSvssList()) {
			    for (String svsTime: svs.getSvsTimes()) {
				    CronService.getInstance().schedule(new SvsStartRunnable(svs.getId()), svsTime);
			    }
			}
		}
	}
	
	public void startSvs(final int id) {
		final Panesterra<?> gate;
		synchronized (this) {
			if (activeSvs.containsKey(id)) {
				return;
			}
			gate = new Gate(svs.get(id));
			activeSvs.put(id, gate);
		}
		gate.start();
		advanceCorridorCountdownMsg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				stopSvs(id);
			}
		}, duration * 3600 * 1000);
	}
	
	public void stopSvs(int id) {
		if (!isSvsInProgress(id)) {
			return;
		}
		Panesterra<?> gate;
		synchronized (this) {
			gate = activeSvs.remove(id);
		} if (gate == null || gate.isFinished()) {
			return;
		}
		distinguishedServiceMsg(id);
		gate.stop();
	}
	
	public void spawn(SvsLocation loc, SvsStateType pstate) {
		if (pstate.equals(SvsStateType.SVS)) {
		}
		List<SpawnGroup2> locSpawns = DataManager.SPAWNS_DATA2.getSvsSpawnsByLocId(loc.getId());
		for (SpawnGroup2 group : locSpawns) {
			for (SpawnTemplate st : group.getSpawnTemplates()) {
				SvsSpawnTemplate svstemplate = (SvsSpawnTemplate) st;
				if (svstemplate.getPStateType().equals(pstate)) {
					loc.getSpawned().add(SpawnEngine.spawnObject(svstemplate, 1));
				}
			}
		}
	}
	
   /**
	* The Advance Corridor Countdown.
	*/
	public boolean advanceCorridorCountdownMsg(int id) {
        switch (id) {
            case 1:
                World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						//An Advance Corridor to a Rift Portal battle has appeared.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_SVS_INVADE_DIRECT_PORTAL_OPEN, 0);
						//The Advance Corridor leading to the Panesterra Fortress Battle will be closed in 10 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End01, 3000000);
						//The Advance Corridor leading to the Panesterra Fortress Battle will be closed in 5 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End02, 3300000);
						//The Advance Corridor leading to the Panesterra Fortress Battle will be closed in 1 minute.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End03, 3540000);
						//The Advance Corridor leading to the Panesterra Fortress Battle has closed.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End05, 3600000);
					}
				});
			    return true;
            default:
                return false;
        }
    }
	
   /**
	* The Advance Corridor For Distinguished Service.
	*/
	public boolean distinguishedServiceMsg(int id) {
        switch (id) {
            case 1:
                World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						//The Distinguished Service Siege Portal leading to Panesterra opened.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End11, 0);
						//The Distinguished Service Siege Portal to the Panesterra Siege will close in 5 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End06, 10000);
						//The Distinguished Service Siege Portal to the Panesterra Siege will close in 3 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End07, 120000);
						//The Distinguished Service Siege Portal to the Panesterra Siege will close in 1 minute.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End08, 240000);
						//The Distinguished Service Siege Portal to the Panesterra Siege has closed.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Gab1_End10, 300000);
					}
				});
			    return true;
            default:
                return false;
        }
    }
	
   /**
	* Advance Corridor [Transidium Annex]
	*/
	public boolean transidiumAnnexMsg(int id) {
        switch (id) {
            case 5:
                World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						//Loading the Advance Corridor Shield... Please wait.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_01, 0);
						//The entrance to the Transidium Annex will open in 8 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_02, 10000);
						//The entrance to the Transidium Annex will open in 6 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_03, 120000);
						//The entrance to the Transidium Annex will open in 4 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_04, 240000);
						//The entrance to the Transidium Annex will open in 2 minutes.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_05, 360000);
						//The entrance to the Transidium Annex will open in 1 minute.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_06, 420000);
						//The entrance to the Transidium Annex has opened.
						PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_08, 480000);
					}
				});
			    return true;
            default:
                return false;
        }
    }
	
   /**
	* Advance Corridor [Transidium Annex]
	*/
	public boolean advanceCorridorSP(int id) {
        switch (id) {
            case 5:
                advanceCorridor.put(802219, SpawnEngine.spawnObject(SpawnEngine.addNewSingleTimeSpawn(400020000, 802219, 1024.12f, 1078.747f, 1530.2688f, (byte) 90), 1));
				advanceCorridor.put(802221, SpawnEngine.spawnObject(SpawnEngine.addNewSingleTimeSpawn(400040000, 802221, 1024.12f, 1078.747f, 1530.2688f, (byte) 90), 1));
				advanceCorridor.put(802223, SpawnEngine.spawnObject(SpawnEngine.addNewSingleTimeSpawn(400050000, 802223, 1024.12f, 1078.747f, 1530.2688f, (byte) 90), 1));
				advanceCorridor.put(802225, SpawnEngine.spawnObject(SpawnEngine.addNewSingleTimeSpawn(400060000, 802225, 1024.12f, 1078.747f, 1530.2688f, (byte) 90), 1));
			    return true;
            default:
                return false;
        }
    }
	
	public void despawn(SvsLocation loc) {
		if (loc.getSpawned() == null) {
        	return;
		} for (VisibleObject obj: loc.getSpawned()) {
            Npc spawned = (Npc) obj;
            spawned.setDespawnDelayed(true);
            if (spawned.getAggroList().getList().isEmpty()) {
                spawned.getController().cancelTask(TaskId.RESPAWN);
                obj.getController().onDelete();
            }
        }
        loc.getSpawned().clear();
	}
	
	public boolean isSvsInProgress(int id) {
		return activeSvs.containsKey(id);
	}
	
	public Map<Integer, Panesterra<?>> getActiveSvs() {
		return activeSvs;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public SvsLocation getSvsLocation(int id) {
		return svs.get(id);
	}
	
	public Map<Integer, SvsLocation> getSvsLocations() {
		return svs;
	}
	
	public static SvsService getInstance() {
		return SvsServiceHolder.INSTANCE;
	}
	
	private static class SvsServiceHolder {
		private static final SvsService INSTANCE = new SvsService();
	}
}
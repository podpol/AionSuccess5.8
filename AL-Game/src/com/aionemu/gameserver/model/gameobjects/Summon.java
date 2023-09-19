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
package com.aionemu.gameserver.model.gameobjects;

import com.aionemu.gameserver.ai2.AI2Engine;
import com.aionemu.gameserver.controllers.CreatureController;
import com.aionemu.gameserver.controllers.SummonController;
import com.aionemu.gameserver.controllers.attack.AggroList;
import com.aionemu.gameserver.controllers.attack.PlayerAggroList;
import com.aionemu.gameserver.controllers.movement.SiegeWeaponMoveController;
import com.aionemu.gameserver.controllers.movement.SummonMoveController;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.TribeClass;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.container.SummonGameStats;
import com.aionemu.gameserver.model.stats.container.SummonLifeStats;
import com.aionemu.gameserver.model.summons.SummonMode;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.stats.SummonStatsTemplate;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.concurrent.Future;

/**
 * @author ATracer
 */
public class Summon extends Creature {

	private Player master;
	private SummonMode mode = SummonMode.GUARD;
	private byte level;
	private int liveTime = 0;
	private Future<?> releaseTask;

	/**
	 * @param objId
	 * @param controller
	 * @param spawnTemplate
	 * @param objectTemplate
	 * @param position
	 * @param level
	 */
	public Summon(int objId, CreatureController<? extends Creature> controller, SpawnTemplate spawnTemplate, NpcTemplate objectTemplate, byte level, int time) {
		super(objId, controller, spawnTemplate, objectTemplate, new WorldPosition(spawnTemplate.getWorldId()));
		controller.setOwner(this);
		String ai = objectTemplate.getAi();
		AI2Engine.getInstance().setupAI(ai, this);
		moveController = (ai.equals("siege_weapon") ? new SiegeWeaponMoveController(this) : new SummonMoveController(this));
		this.level = level;
		liveTime = time;
		SummonStatsTemplate statsTemplate = DataManager.SUMMON_STATS_DATA.getSummonTemplate(objectTemplate.getTemplateId(), level);
		setGameStats(new SummonGameStats(this, statsTemplate));
		setLifeStats(new SummonLifeStats(this));
	}

	@Override
	protected AggroList createAggroList() {
		return new PlayerAggroList(this);
	}

	@Override
	public SummonGameStats getGameStats() {
		return (SummonGameStats) super.getGameStats();
	}

	@Override
	public Player getMaster() {
		return master;
	}

	/**
	 * @param master
	 *          the master to set
	 */
	public void setMaster(Player master) {
		this.master = master;
	}

	@Override
	public String getName() {
		return objectTemplate.getName();
	}

	/**
	 * @return the level
	 */
	@Override
	public byte getLevel() {
		return level;
	}

	@Override
	public NpcTemplate getObjectTemplate() {
		return (NpcTemplate) super.getObjectTemplate();
	}

	public int getNpcId() {
		return getObjectTemplate().getTemplateId();
	}

	public int getNameId() {
		return getObjectTemplate().getNameId();
	}

	/**
	 * @return NpcObjectType.SUMMON
	 */
	@Override
	public NpcObjectType getNpcObjectType() {
		return NpcObjectType.SUMMON;
	}

	@Override
	public SummonController getController() {
		return (SummonController) super.getController();
	}

	/**
	 * @return the mode
	 */
	public SummonMode getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *          the mode to set
	 */
	public void setMode(SummonMode mode) {
		this.mode = mode;
	}

	@Override
	public boolean isEnemy(Creature creature) {
		return master != null ? master.isEnemy(creature) : false;
	}

	@Override
	public boolean isEnemyFrom(Npc npc) {
		return master != null ? master.isEnemyFrom(npc) : false;
	}

	@Override
	public boolean isEnemyFrom(Player player) {
		return master != null ? master.isEnemyFrom(player) : false;
	}

	@Override
	public TribeClass getTribe() {
		if (master == null)
			return ((NpcTemplate) objectTemplate).getTribe();
		return master.getTribe();
	}

	@Override
	public final boolean isAggroFrom(Npc npc) {
		if (getMaster() == null)
			return false;

		return getMaster().isAggroFrom(npc);
	}
	
	@Override
	public SummonMoveController getMoveController() {
		return (SummonMoveController) super.getMoveController();
	}
	
	@Override
	public Creature getActingCreature() {
		return getMaster() == null ? this : getMaster();
	}
	
	@Override
	public Race getRace() {
		return getMaster() != null ? getMaster().getRace() : Race.NONE;
	}

	/**
	 * @return liveTime in sec.
	 */
	public int getLiveTime() {
		return liveTime;
	}
	
	/**
	 * @param liveTime in sec.
	 */
	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	public void setReleaseTask(Future<?> task) {
		releaseTask = task;
	}

	public void cancelReleaseTask() {
		if (releaseTask != null && !releaseTask.isDone())
			releaseTask.cancel(true);
	}
}

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
package com.aionemu.gameserver.model.stats.container;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.LOG;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.TYPE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SUMMON_UPDATE;
import com.aionemu.gameserver.services.LifeStatsRestoreService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author ATracer
 */
public class SummonLifeStats extends CreatureLifeStats<Summon> {

	public SummonLifeStats(Summon owner) {
		super(owner, owner.getGameStats().getMaxHp().getCurrent(), owner.getGameStats().getMaxMp().getCurrent());
	}

	@Override
	protected void onIncreaseHp(TYPE type, int value, int skillId, LOG log) {
		Creature master = getOwner().getMaster();
		sendAttackStatusPacketUpdate(type, value, skillId, log);

		if (master instanceof Player) {
			PacketSendUtility.sendPacket((Player) master, new SM_SUMMON_UPDATE(getOwner()));
		}
	}

	@Override
	protected void onIncreaseMp(TYPE type, int value, int skillId, LOG log) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onReduceHp() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onReduceMp() {
		// TODO Auto-generated method stub
	}

	@Override
	public Summon getOwner() {
		return (Summon) super.getOwner();
	}

	@Override
	public void triggerRestoreTask() {
		restoreLock.lock();
		try {
			if (lifeRestoreTask == null && !alreadyDead) {
				this.lifeRestoreTask = LifeStatsRestoreService.getInstance().scheduleHpRestoreTask(this);
			}
		}
		finally {
			restoreLock.unlock();
		}
	}
}

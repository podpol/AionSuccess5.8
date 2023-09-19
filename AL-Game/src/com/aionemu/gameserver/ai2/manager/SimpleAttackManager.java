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
package com.aionemu.gameserver.ai2.manager;

import com.aionemu.gameserver.ai2.AI2Logger;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.geo.GeoService;

/**
 * @author ATracer
 */
public class SimpleAttackManager {

	/**
	 * @param npcAI
	 * @param delay
	 */
	public static void performAttack(NpcAI2 npcAI, int delay) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "performAttack");
		}
		if (npcAI.getOwner().getGameStats().isNextAttackScheduled()) {
			if (npcAI.isLogging()) {
				AI2Logger.info(npcAI, "Attack already sheduled");
			}
			scheduleCheckedAttackAction(npcAI, delay);
			return;
		}

		if (!isTargetInAttackRange(npcAI.getOwner())) {
			if (npcAI.isLogging()) {
				AI2Logger.info(npcAI, "Attack will not be scheduled because of range");
			}
			npcAI.onGeneralEvent(AIEventType.TARGET_TOOFAR);
			return;
		}
		npcAI.getOwner().getGameStats().setNextAttackTime(System.currentTimeMillis() + delay);
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new SimpleAttackAction(npcAI), delay);
		}
		else {
			attackAction(npcAI);
		}
	}

	/**
	 * @param npcAI
	 * @param delay
	 */
	private static void scheduleCheckedAttackAction(NpcAI2 npcAI, int delay) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "Scheduling checked attack " + delay);
		}
		ThreadPoolManager.getInstance().schedule(new SimpleCheckedAttackAction(npcAI), delay);
	}

	public static boolean isTargetInAttackRange(Npc npc) {
		if (npc.getAi2().isLogging()) {
			float distance = npc.getDistanceToTarget();
			AI2Logger.info((AbstractAI) npc.getAi2(), "isTargetInAttackRange: " + distance);
		}
		if (npc.getTarget() == null || !(npc.getTarget() instanceof Creature))
			return false;
		return MathUtil.isInAttackRange(npc, (Creature) npc.getTarget(), npc.getGameStats().getAttackRange().getCurrent() / 1000f);
		//return distance <= npc.getController().getAttackDistanceToTarget() + NpcMoveController.MOVE_CHECK_OFFSET;
	}

	/**
	 * @param npcAI
	 */
	protected static void attackAction(final NpcAI2 npcAI) {
		if (!npcAI.isInState(AIState.FIGHT)) {
			return;
		}
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "attackAction");
		}
		Npc npc = npcAI.getOwner();
		Creature target = (Creature) npc.getTarget();
		if (target != null && !target.getLifeStats().isAlreadyDead()) {
			if (!npc.canSee(target) || !GeoService.getInstance().canSee(npc, target)) { //delete check geo when the Path Finding
				npc.getController().cancelCurrentSkill();
				npcAI.onGeneralEvent(AIEventType.TARGET_GIVEUP);
				return;
			}
			if (isTargetInAttackRange(npc)) {
				npc.getController().attackTarget(target, 0);
				npcAI.onGeneralEvent(AIEventType.ATTACK_COMPLETE);
				return;
			}
			npcAI.onGeneralEvent(AIEventType.TARGET_TOOFAR);
		}
		else {
			npcAI.onGeneralEvent(AIEventType.TARGET_GIVEUP);
		}
	}

	private final static class SimpleAttackAction implements Runnable {

		private NpcAI2 npcAI;

		SimpleAttackAction(NpcAI2 npcAI) {
			this.npcAI = npcAI;
		}

		@Override
		public void run() {
			attackAction(npcAI);
			npcAI = null;
		}

	}

	private final static class SimpleCheckedAttackAction implements Runnable {

		private NpcAI2 npcAI;

		SimpleCheckedAttackAction(NpcAI2 npcAI) {
			this.npcAI = npcAI;
		}

		@Override
		public void run() {
			if (!npcAI.getOwner().getGameStats().isNextAttackScheduled()) {
				attackAction(npcAI);
			}
			else {
				if (npcAI.isLogging()) {
					AI2Logger.info(npcAI, "Scheduled checked attacked confirmed");
				}
			}
			npcAI = null;
		}

	}

}
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
package com.aionemu.gameserver.ai2.handler;

import com.aionemu.gameserver.ai2.AI2Logger;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.ai2.manager.AttackManager;
import com.aionemu.gameserver.ai2.manager.FollowManager;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;

/**
 * @author ATracer
 */
public class TargetEventHandler {

	/**
	 * @param npcAI
	 */
	public static void onTargetReached(NpcAI2 npcAI) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onTargetReached");
		}

		AIState currentState = npcAI.getState();
		switch (currentState) {
			case FIGHT:
				npcAI.getOwner().getMoveController().abortMove();
				AttackManager.scheduleNextAttack(npcAI);
				if (npcAI.getOwner().getMoveController().isFollowingTarget())
					npcAI.getOwner().getMoveController().storeStep();
				break;
			case RETURNING:
				npcAI.getOwner().getMoveController().abortMove();
				npcAI.getOwner().getMoveController().recallPreviousStep();
				if (npcAI.getOwner().isAtSpawnLocation())
					npcAI.onGeneralEvent(AIEventType.BACK_HOME);
				else
					npcAI.onGeneralEvent(AIEventType.NOT_AT_HOME);
				break;
			case WALKING:
				WalkManager.targetReached(npcAI);
				checkAggro(npcAI);
				break;	
			case FOLLOWING:
				npcAI.getOwner().getMoveController().abortMove();
				npcAI.getOwner().getMoveController().storeStep();
				break;
			case FEAR: //TO DO remove this state
				npcAI.getOwner().getMoveController().abortMove();
				npcAI.getOwner().getMoveController().storeStep();
				break;
		}
	}

	/**
	 * @param npcAI
	 */
	public static void onTargetTooFar(NpcAI2 npcAI) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onTargetTooFar");
		}
		switch (npcAI.getState()) {
			case FIGHT:
				AttackManager.targetTooFar(npcAI);
				break;
			case FOLLOWING:
				FollowManager.targetTooFar(npcAI);
				break;
			case FEAR:
				break;
			default:
				if (npcAI.isLogging()) {
					AI2Logger.info(npcAI, "default onTargetTooFar");
				}
		}
	}

	/**
	 * @param npcAI
	 */
	public static void onTargetGiveup(NpcAI2 npcAI) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onTargetGiveup");
		}
		VisibleObject target = npcAI.getOwner().getTarget();
		if (target != null) {
			npcAI.getOwner().getAggroList().stopHating(target);
		}
		if (npcAI.isMoveSupported()) {
			npcAI.getOwner().getMoveController().abortMove();
		}
		if (!npcAI.isAlreadyDead())
			npcAI.think();
	}

	/**
	 * @param npcAI
	 */
	public static void onTargetChange(NpcAI2 npcAI, Creature creature) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onTargetChange");
		}
		if (npcAI.isInState(AIState.FIGHT)) {
			npcAI.getOwner().setTarget(creature);
			AttackManager.scheduleNextAttack(npcAI);
		}
	}
	
	private static void checkAggro(NpcAI2 npcAI) {
		for (VisibleObject obj : npcAI.getOwner().getKnownList().getKnownObjects().values()) {
			if (obj instanceof Creature) {
				CreatureEventHandler.checkAggro(npcAI, (Creature) obj);
			}
		}
	}
}
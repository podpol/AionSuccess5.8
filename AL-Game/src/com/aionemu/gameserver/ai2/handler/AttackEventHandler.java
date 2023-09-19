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
import com.aionemu.gameserver.ai2.AISubState;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.ai2.manager.AttackManager;
import com.aionemu.gameserver.ai2.manager.EmoteManager;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.ai2.poll.AIQuestion;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;

/**
 * @author ATracer
 */
public class AttackEventHandler {

	/**
	 * @param npcAI
	 * @param creature
	 */
	public static void onAttack(NpcAI2 npcAI, Creature creature) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onAttack");
		}
		if (creature == null || creature.getLifeStats().isAlreadyDead()) {
			return;
		}
		// TODO lock or better switch
		if (npcAI.isInState(AIState.RETURNING)) {
			npcAI.getOwner().getMoveController().abortMove();
			npcAI.setStateIfNot(AIState.IDLE);
			npcAI.onGeneralEvent(AIEventType.NOT_AT_HOME);
			return;
		}
		if (!npcAI.canThink()) {
			return;
		}
		if (npcAI.isInState(AIState.WALKING)) {
			WalkManager.stopWalking(npcAI);
		}
		npcAI.getOwner().getGameStats().renewLastAttackedTime();
		if (!npcAI.isInState(AIState.FIGHT)) {
			npcAI.setStateIfNot(AIState.FIGHT);
			if (npcAI.isLogging()) {
				AI2Logger.info(npcAI, "onAttack() -> startAttacking");
			}
			npcAI.setSubStateIfNot(AISubState.NONE);
			npcAI.getOwner().setTarget(creature);
			AttackManager.startAttacking(npcAI);
			if (npcAI.poll(AIQuestion.CAN_SHOUT))
				ShoutEventHandler.onAttackBegin(npcAI, (Creature) npcAI.getOwner().getTarget());
		}
	}

	/**
	 * @param npcAI
	 */
	public static void onForcedAttack(NpcAI2 npcAI) {
		onAttack(npcAI, (Creature) npcAI.getOwner().getTarget());
	}

	/**
	 * @param npcAI
	 */
	public static void onAttackComplete(NpcAI2 npcAI) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onAttackComplete: " + npcAI.getOwner().getGameStats().getLastAttackTimeDelta());
		}
		npcAI.getOwner().getGameStats().renewLastAttackTime();
		AttackManager.scheduleNextAttack(npcAI);
	}

	/**
	 * @param npcAI
	 */
	public static void onFinishAttack(NpcAI2 npcAI) {
		if (!npcAI.canThink()) {
			return;
		}
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "onFinishAttack");
		}
		Npc npc = npcAI.getOwner();
		EmoteManager.emoteStopAttacking(npc);
		npc.getLifeStats().startResting();
		npc.getAggroList().clear();
		if (npcAI.poll(AIQuestion.CAN_SHOUT))
			ShoutEventHandler.onAttackEnd(npcAI);
		npc.setTarget(null);
		npc.setSkillNumber(0);
	}
}

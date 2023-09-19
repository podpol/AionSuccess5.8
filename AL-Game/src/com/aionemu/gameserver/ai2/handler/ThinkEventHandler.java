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
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;

/**
 * @author ATracer
 */
public class ThinkEventHandler {

	/**
	 * @param npcAI
	 */
	public static void onThink(NpcAI2 npcAI) {
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "think");
		}
		if(npcAI.isAlreadyDead()){
			AI2Logger.info(npcAI, "can't think in dead state");
			return;
		}
		if(!npcAI.tryLockThink()){
			AI2Logger.info(npcAI, "can't acquire lock");
			return;
		}
		try {
			if (!npcAI.getOwner().getPosition().isMapRegionActive() || npcAI.getSubState() == AISubState.FREEZE) {
				thinkInInactiveRegion(npcAI);
				return;
			}
			if (npcAI.isLogging()) {
				AI2Logger.info(npcAI, "think state " + npcAI.getState());
			}
			switch (npcAI.getState()) {
				case FIGHT:
					thinkAttack(npcAI);
					break;
				case WALKING:
					thinkWalking(npcAI);
					break;
				case IDLE:
					thinkIdle(npcAI);
					break;
			}
		}
		finally {
			npcAI.unlockThink();
		}
	}

	/**
	 * @param npcAI
	 */
	private static void thinkInInactiveRegion(NpcAI2 npcAI) {
		if (!npcAI.canThink()) {
			return;
		}
		if (npcAI.isLogging()) {
			AI2Logger.info(npcAI, "think in inactive region: " + npcAI.getState());
		}
		switch (npcAI.getState()) {
			case FIGHT:
				thinkAttack(npcAI);
				break;
			default:
				if (!npcAI.getOwner().isAtSpawnLocation()) {
					npcAI.onGeneralEvent(AIEventType.NOT_AT_HOME);
				}
		}

	}

	/**
	 * @param npcAI
	 */
	public static void thinkAttack(NpcAI2 npcAI) {
		Npc npc = npcAI.getOwner();
		Creature mostHated = npc.getAggroList().getMostHated();
		if (mostHated != null && !mostHated.getLifeStats().isAlreadyDead()) {
			npcAI.onCreatureEvent(AIEventType.TARGET_CHANGED, mostHated);
		}
		else {
			npc.getMoveController().recallPreviousStep();
			npcAI.onGeneralEvent(AIEventType.ATTACK_FINISH);
			npcAI.onGeneralEvent(npc.isAtSpawnLocation() ? AIEventType.BACK_HOME : AIEventType.NOT_AT_HOME);
		}
	}

	/**
	 * @param npcAI
	 */
	public static void thinkWalking(NpcAI2 npcAI) {
		WalkManager.startWalking(npcAI);
	}

	/**
	 * @param npcAI
	 */
	public static void thinkIdle(NpcAI2 npcAI) {
		if (WalkManager.isWalking(npcAI)) {
			boolean startedWalking = WalkManager.startWalking(npcAI);
			if (!startedWalking) {
				npcAI.setStateIfNot(AIState.IDLE);
			}
		}
	}
}

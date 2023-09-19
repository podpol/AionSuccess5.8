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

import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.NpcAI2;

/**
 * @author ATracer
 */
public class SpawnEventHandler {

	/**
	 * @param npcAI
	 */
	public static void onSpawn(NpcAI2 npcAI) {
		if (npcAI.setStateIfNot(AIState.IDLE)) {
			if (npcAI.getOwner().getPosition().isMapRegionActive()) {
				npcAI.think();
			}
		}
	}

	/**
	 * @param npcAI
	 */
	public static void onDespawn(NpcAI2 npcAI) {
		npcAI.setStateIfNot(AIState.DESPAWNED);
	}

	/**
	 * @param npcAI
	 */
	public static void onRespawn(NpcAI2 npcAI) {
		npcAI.getOwner().getMoveController().resetMove();
	}

}

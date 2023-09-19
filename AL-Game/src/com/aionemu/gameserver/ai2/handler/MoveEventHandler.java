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

import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.geo.GeoService;

/**
 * @author ATracer
 */
public class MoveEventHandler {

	/**
	 * @param npcAI
	 */
	public static final void onMoveValidate(NpcAI2 npcAI) {
		npcAI.getOwner().getController().onMove();
		TargetEventHandler.onTargetTooFar(npcAI);
		if(npcAI.getOwner().getTarget() != null && npcAI.getOwner().getObjectTemplate().getStatsTemplate().getRunSpeed() != 0) {
		  if(!GeoService.getInstance().canSee(npcAI.getOwner(), npcAI.getOwner().getTarget()) && !MathUtil.isInRange(npcAI.getOwner(), npcAI.getOwner().getTarget(), 15))
			  npcAI.onGeneralEvent(AIEventType.TARGET_GIVEUP);
		}
	}

	/**
	 * @param npcAI
	 */
	public static final void onMoveArrived(NpcAI2 npcAI) {
		npcAI.getOwner().getController().onMove();
		TargetEventHandler.onTargetReached(npcAI);
		if(npcAI.getOwner().getTarget() != null && npcAI.getOwner().getObjectTemplate().getStatsTemplate().getRunSpeed() != 0) {
		  if(!GeoService.getInstance().canSee(npcAI.getOwner(), npcAI.getOwner().getTarget()) && !MathUtil.isInRange(npcAI.getOwner(), npcAI.getOwner().getTarget(), 15))
			  npcAI.onGeneralEvent(AIEventType.TARGET_GIVEUP);
		}
	}
}

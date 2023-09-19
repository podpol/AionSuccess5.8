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
package com.aionemu.gameserver.ai2;

import com.aionemu.gameserver.ai2.event.AIEventType;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * @author ATracer
 */
public enum StateEvents {
	CREATED_EVENTS(AIEventType.SPAWNED),
	DESPAWN_EVENTS(AIEventType.RESPAWNED, AIEventType.SPAWNED),
	DEAD_EVENTS(AIEventType.DESPAWNED, AIEventType.DROP_REGISTERED);

	private EnumSet<AIEventType> events;

	private StateEvents(AIEventType... aiEventTypes) {
		this.events = EnumSet.copyOf(Arrays.asList(aiEventTypes));
	}

	public boolean hasEvent(AIEventType event) {
		return events.contains(event);
	}

}

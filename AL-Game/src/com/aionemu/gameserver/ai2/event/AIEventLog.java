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
package com.aionemu.gameserver.ai2.event;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ATracer
 */
public class AIEventLog extends LinkedBlockingDeque<AIEventType> {

	private static final long serialVersionUID = -7234174243343636729L;

	public AIEventLog() {
		super();
	}

	/**
	 * @param capacity
	 */
	public AIEventLog(int capacity) {
		super(capacity);
	}

	@Override
	public synchronized boolean offerFirst(AIEventType e) {
		if (remainingCapacity() == 0) {
			removeLast();
		}
		super.offerFirst(e);
		return true;
	}
}

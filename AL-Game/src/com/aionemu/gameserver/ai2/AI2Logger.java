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

import com.aionemu.gameserver.configs.main.AIConfig;
import com.aionemu.gameserver.model.gameobjects.Creature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ATracer
 */
public class AI2Logger {

	private static final Logger log = LoggerFactory.getLogger(AI2Logger.class);

	public static final void info(AbstractAI ai, String message) {
		if (ai.isLogging()) {
			log.info("[AI2] " + ai.getOwner().getObjectId() + " - " + message);
		}
	}

	public static final void info(AI2 ai, String message) {
		info((AbstractAI) ai, message);
	}

	/**
	 * @param owner
	 * @param message
	 */
	public static void moveinfo(Creature owner, String message) {
		if (AIConfig.MOVE_DEBUG && owner.getAi2().isLogging()) {
			log.info("[AI2] " + owner.getObjectId() + " - " + message);
		}
	}
}

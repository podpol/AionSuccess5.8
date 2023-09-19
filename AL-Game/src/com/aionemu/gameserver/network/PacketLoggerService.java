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
package com.aionemu.gameserver.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.configs.administration.DeveloperConfig;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class PacketLoggerService {

	private static final Logger log = LoggerFactory.getLogger(PacketLoggerService.class);

	public void logPacketCM(String name) {
		if (DeveloperConfig.SHOW_PACKETS) {
			log.info("[PACKET CLIENT] " + name);
		}
	}

	public void logPacketSM(String name) {
		if (DeveloperConfig.SHOW_PACKETS) {
			log.info("[PACKET SERVER] " + name);
		}
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {

		protected static final PacketLoggerService instance = new PacketLoggerService();
	}

	public static final PacketLoggerService getInstance() {
		return SingletonHolder.instance;
	}
}

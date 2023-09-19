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
package com.aionemu.gameserver.network.factories;

import com.aionemu.gameserver.network.loginserver.LoginServerConnection.State;
import com.aionemu.gameserver.network.loginserver.LsClientPacket;
import com.aionemu.gameserver.network.loginserver.LsPacketHandler;
import com.aionemu.gameserver.network.loginserver.clientpackets.*;

/**
 * @author Luno
 */
public class LsPacketHandlerFactory {

	private LsPacketHandler handler = new LsPacketHandler();

	public static final LsPacketHandlerFactory getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * @param loginServer
	 */
	private LsPacketHandlerFactory() {
		addPacket(new CM_ACCOUNT_RECONNECT_KEY(0x03), State.AUTHED);
		addPacket(new CM_ACOUNT_AUTH_RESPONSE(0x01), State.AUTHED);
		addPacket(new CM_GS_AUTH_RESPONSE(0x00), State.CONNECTED);
		addPacket(new CM_REQUEST_KICK_ACCOUNT(0x02), State.AUTHED);
		addPacket(new CM_LS_CONTROL_RESPONSE(0x04), State.AUTHED);
		addPacket(new CM_BAN_RESPONSE(0x05), State.AUTHED);
		addPacket(new CM_GS_CHARACTER_RESPONSE(0x08), State.AUTHED);
		addPacket(new CM_MACBAN_LIST(9), State.AUTHED);
		addPacket(new CM_PREMIUM_RESPONSE(10), State.AUTHED);
		addPacket(new CM_LS_PING(11), State.AUTHED);
		addPacket(new CM_PTRANSFER_RESPONSE(12), State.AUTHED);
	}

	private void addPacket(LsClientPacket prototype, State... states) {
		handler.addPacketPrototype(prototype, states);
	}

	public LsPacketHandler getPacketHandler() {
		return handler;
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {

		protected static final LsPacketHandlerFactory instance = new LsPacketHandlerFactory();
	}
}

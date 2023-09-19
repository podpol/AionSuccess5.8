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

import com.aionemu.gameserver.network.chatserver.ChatServerConnection.State;
import com.aionemu.gameserver.network.chatserver.CsClientPacket;
import com.aionemu.gameserver.network.chatserver.CsPacketHandler;
import com.aionemu.gameserver.network.chatserver.clientpackets.CM_CS_AUTH_RESPONSE;
import com.aionemu.gameserver.network.chatserver.clientpackets.CM_CS_PLAYER_AUTH_RESPONSE;

/**
 * @author ATracer
 */
public class CsPacketHandlerFactory {

	private CsPacketHandler handler = new CsPacketHandler();

	/**
	 * @param injector
	 */
	public CsPacketHandlerFactory() {
		addPacket(new CM_CS_AUTH_RESPONSE(0x00), State.CONNECTED);
		addPacket(new CM_CS_PLAYER_AUTH_RESPONSE(0x01), State.AUTHED);
	}

	/**
	 * @param prototype
	 * @param states
	 */
	private void addPacket(CsClientPacket prototype, State... states) {
		handler.addPacketPrototype(prototype, states);
	}

	/**
	 * @return handler
	 */
	public CsPacketHandler getPacketHandler() {
		return handler;
	}
}

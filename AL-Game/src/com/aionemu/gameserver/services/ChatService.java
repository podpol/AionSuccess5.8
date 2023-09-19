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
package com.aionemu.gameserver.services;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_CHAT_INIT;
import com.aionemu.gameserver.network.chatserver.ChatServer;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;

/** 
 * @author ATracer
 */
public class ChatService {
	
	private static byte[] ip = { 127, 0, 0, 1 };
	private static int port = 10241;

	/**
	 * Disonnect from chat server
	 * 
	 * @param player
	 */
	public static void onPlayerLogout(Player player) {
		ChatServer.getInstance().sendPlayerLogout(player);
	}

	/**
	 * @param playerId
	 * @param token
	 * @param account 
	 * @param nick 
	 */
	public static void playerAuthed(int playerId, byte[] token) {
		Player player = World.getInstance().findPlayer(playerId);
		if (player != null) {
			PacketSendUtility.sendPacket(player, new SM_CHAT_INIT(token));
		}
	}

	/**
	 * @return the ip
	 */
	public static byte[] getIp() {
		return ip;
	}

	/**
	 * @return the port
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * @param ip
	 *          the ip to set
	 */
	public static void setIp(byte[] _ip) {
		ip = _ip;
	}

	/**
	 * @param port
	 *          the port to set
	 */
	public static void setPort(int _port) {
		port = _port;
	}
}

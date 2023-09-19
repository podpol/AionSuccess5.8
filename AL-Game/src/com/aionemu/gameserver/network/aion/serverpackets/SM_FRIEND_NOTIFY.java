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
package com.aionemu.gameserver.network.aion.serverpackets;


import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Notifies players when their friends log in, out, or delete them
 * 
 * @author Ben
 */
public class SM_FRIEND_NOTIFY extends AionServerPacket {

	/**
	 * Buddy has logged in (Or become visible)
	 */
	public static final int LOGIN = 0;
	/**
	 * Buddy has logged out (Or become invisible)
	 */
	public static final int LOGOUT = 1;
	/**
	 * Buddy has deleted you
	 */
	public static final int DELETED = 2;

	private final int code;
	private final String name;

	/**
	 * Constructs a new notify packet
	 * 
	 * @param code
	 *          Message code
	 * @param name
	 *          Name of friend
	 */
	public SM_FRIEND_NOTIFY(int code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeS(name);
		writeC(code);
	}
}

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
 * Responses to block list related requests
 * 
 * @author Ben
 */
public class SM_BLOCK_RESPONSE extends AionServerPacket {

	/**
	 * You have blocked %0
	 */
	public static final int BLOCK_SUCCESSFUL = 0;
	/**
	 * You have unblocked %0
	 */
	public static final int UNBLOCK_SUCCESSFUL = 1;
	/**
	 * That character does not exist.
	 */
	public static final int TARGET_NOT_FOUND = 2;
	/**
	 * Your Block List is full.
	 */
	public static final int LIST_FULL = 3;
	/**
	 * You cannot block yourself.
	 */
	public static final int CANT_BLOCK_SELF = 4;

	private int code;
	private String playerName;

	/**
	 * Constructs a new block request response packet
	 * 
	 * @param code
	 *          Message code to use - see class constants
	 * @param playerName
	 *          Parameters inserted into message. Usually the target player's name
	 */
	public SM_BLOCK_RESPONSE(int code, String playerName) {
		this.code = code;
		this.playerName = playerName;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeS(playerName);
		writeD(code);

	}
}

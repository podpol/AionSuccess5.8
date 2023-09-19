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


import com.aionemu.gameserver.model.account.PlayerAccountData;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.PlayerInfo;

/**
 * This packet is response for CM_CREATE_CHARACTER
 * 
 * @author Nemesiss, AEJTester
 */
public class SM_CREATE_CHARACTER extends PlayerInfo {

	/** If response is ok */
	public static final int RESPONSE_OK = 0x00;

	public static final int FAILED_TO_CREATE_THE_CHARACTER = 1;
	/** Failed to create the character due to world db error */
	public static final int RESPONSE_DB_ERROR = 2;
	/** The number of characters exceeds the maximum allowed for the server */
	public static final int RESPONSE_SERVER_LIMIT_EXCEEDED = 4;
	/** Invalid character name */
	public static final int RESPONSE_INVALID_NAME = 5;
	/** The name includes forbidden words */
	public static final int RESPONSE_FORBIDDEN_CHAR_NAME = 9;
	/** A character with that name already exists */
	public static final int RESPONSE_NAME_ALREADY_USED = 10;
	/** The name is already reserved */
	public static final int RESPONSE_NAME_RESERVED = 11;
	/** You cannot create characters of other races in the same server */
	public static final int RESPONSE_OTHER_RACE = 12;
	public static final int RESPONSE_CREATE_NEW = 22;

	/**
	 * response code
	 */
	private final int responseCode;

	/**
	 * Newly created player.
	 */
	private final PlayerAccountData player;

	/**
	 * Constructs new <tt>SM_CREATE_CHARACTER </tt> packet
	 * 
	 * @param accPlData
	 *          playerAccountData of player that was created
	 * @param responseCode
	 *          response code (invalid nickname, nickname is already taken, ok)
	 */

	public SM_CREATE_CHARACTER(PlayerAccountData accPlData, int responseCode) {
		this.player = accPlData;
		this.responseCode = responseCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(responseCode);

		if (responseCode == RESPONSE_OK) {
			writePlayerInfo(player); // if everything is fine, all the character's data should be sent
			writeB(new byte[32]);
			writeB(new byte[88]); // unk 4.5.0.19
		}
		else {
			writeB(new byte[448+/*4.5.0.19 unk*/88]); // if something is wrong, only return code should be sent in the packet
		}
	}
}
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
package com.aionemu.gameserver.network.aion.clientpackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MACRO_RESULT;
import com.aionemu.gameserver.services.player.PlayerService;

/**
 * Request to create
 *
 * @author SoulKeeper
 */
public class CM_MACRO_CREATE extends AionClientPacket {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(CM_MACRO_CREATE.class);
	/**
	 * Macro number. Fist is 1, second is 2. Starting from 1, not from 0
	 */
	private int macroPosition;
	/**
	 * XML that represents the macro
	 */
	private String macroXML;

	/**
	 * Constructs new client packet instance.
	 *
	 * @param opcode
	 */
	public CM_MACRO_CREATE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * Read macro data
	 */
	@Override
	protected void readImpl() {
		macroPosition = readC();
		macroXML = readS();
	}

	/**
	 * Logging
	 */
	@Override
	protected void runImpl() {
		log.debug(String.format("Created Macro #%d: %s", macroPosition, macroXML));

		PlayerService.addMacro(getConnection().getActivePlayer(), macroPosition, macroXML);

		sendPacket(SM_MACRO_RESULT.SM_MACRO_CREATED);
	}
}

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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ranastic (Encom)
 */

public class CM_ARCHDAEVA_CREATIVITY_RESET extends AionClientPacket
{
	private static final Logger log = LoggerFactory.getLogger(CM_ARCHDAEVA_CREATIVITY_RESET.class);
	
	public CM_ARCHDAEVA_CREATIVITY_RESET(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		//empty byte
	}
	
	@Override
	protected void runImpl() {
		Player player = this.getConnection().getActivePlayer();
		log.info("0x0124 CM_ARCHDAEVA_CREATIVITY called");
	}
}
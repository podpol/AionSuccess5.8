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
import com.aionemu.gameserver.network.aion.serverpackets.SM_BLOCK_RESPONSE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.SocialService;
import com.aionemu.gameserver.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ben
 */
public class CM_BLOCK_ADD extends AionClientPacket {

	private static Logger log = LoggerFactory.getLogger(CM_BLOCK_ADD.class);

	private String targetName;
	private String reason;

	public CM_BLOCK_ADD(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl() {
		targetName = readS();
		reason = readS();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl() {

		Player activePlayer = getConnection().getActivePlayer();

		Player targetPlayer = World.getInstance().findPlayer(targetName);

		// Trying to block self
		if (activePlayer.getName().equalsIgnoreCase(targetName)) {
			sendPacket(new SM_BLOCK_RESPONSE(SM_BLOCK_RESPONSE.CANT_BLOCK_SELF, targetName));
		}

		// List full
		else if (activePlayer.getBlockList().isFull()) {
			sendPacket(new SM_BLOCK_RESPONSE(SM_BLOCK_RESPONSE.LIST_FULL, targetName));
		}

		// Player offline
		else if (targetPlayer == null) {
			sendPacket(new SM_BLOCK_RESPONSE(SM_BLOCK_RESPONSE.TARGET_NOT_FOUND, targetName));
		}

		// Player is your friend
		else if (activePlayer.getFriendList().getFriend(targetPlayer.getObjectId()) != null) {
			sendPacket(SM_SYSTEM_MESSAGE.STR_BLOCKLIST_NO_BUDDY);
		}

		// Player already blocked
		else if (activePlayer.getBlockList().contains(targetPlayer.getObjectId())) {
			sendPacket(SM_SYSTEM_MESSAGE.STR_BLOCKLIST_ALREADY_BLOCKED);
		}

		// Try and block player
		else if (!SocialService.addBlockedUser(activePlayer, targetPlayer, reason)) {
			log.error("Failed to add " + targetPlayer.getName() + " to the block list for " + activePlayer.getName()
				+ " - check database setup.");
		}

	}

}

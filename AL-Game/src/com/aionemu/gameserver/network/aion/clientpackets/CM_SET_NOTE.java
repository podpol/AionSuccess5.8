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

import com.aionemu.gameserver.model.gameobjects.player.Friend;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FRIEND_LIST;
import com.aionemu.gameserver.network.aion.serverpackets.SM_UPDATE_NOTE;

/**
 * Received when a player sets his note
 * 
 * @author Ben
 */
public class CM_SET_NOTE extends AionClientPacket {

	private String note;

	public CM_SET_NOTE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl() {
		note = readS();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl() {
		Player activePlayer = getConnection().getActivePlayer();

		if (!note.equals(activePlayer.getCommonData().getNote())) {

			activePlayer.getCommonData().setNote(note);
			activePlayer.getClientConnection().sendPacket(new SM_UPDATE_NOTE(activePlayer.getObjectId(), note));

			for (Friend friend : activePlayer.getFriendList()) // For all my friends
			{
				Player frienPlayer = friend.getPlayer();
				if (friend.isOnline() && frienPlayer != null) // If the player is online
				{
					friend.getPlayer().getClientConnection().sendPacket(new SM_FRIEND_LIST()); // Send him a new friend list
																																											// packet
				}
			}

		}
	}
}

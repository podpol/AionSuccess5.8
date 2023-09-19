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


import com.aionemu.gameserver.model.gameobjects.player.Friend;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sent to update a player's status in a friendlist
 * 
 * @author Ben
 */
public class SM_FRIEND_UPDATE extends AionServerPacket {

	private int friendObjId;

	private static Logger log = LoggerFactory.getLogger(SM_FRIEND_UPDATE.class);

	public SM_FRIEND_UPDATE(int friendObjId) {
		this.friendObjId = friendObjId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		Friend f = con.getActivePlayer().getFriendList().getFriend(friendObjId);
		if (f == null)
			log.debug("Attempted to update friend list status of " + friendObjId + " for " + con.getActivePlayer().getName()
				+ " - object ID not found on friend list");
		else {
			writeS(f.getName());
			writeD(f.getLevel());
			writeD(f.getPlayerClass().getClassId());
			writeC(f.isOnline() ? 1 : 0); // Online status - No idea why this and f.getStatus are used
			writeD(f.getMapId());
			writeD(f.getLastOnlineTime()); // Date friend was last online as a Unix timestamp.
			writeS(f.getNote());
			writeC(f.getStatus().getId());
		}
	}
}

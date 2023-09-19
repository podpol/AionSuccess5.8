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
import com.aionemu.gameserver.model.gameobjects.player.FriendList;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.HousingService;

public class SM_FRIEND_LIST extends AionServerPacket
{
	@Override
	protected void writeImpl(AionConnection con) {
		FriendList list = con.getActivePlayer().getFriendList();
		writeH((0 - list.getSize()));
		writeC(0);
		for (Friend friend : list) {
			writeD(friend.getOid());
			writeS(friend.getName());
			writeD(friend.getLevel());
			writeD(friend.getPlayerClass().getClassId());
			writeC(friend.isOnline() ? 1 : 0);
			writeD(friend.getMapId());
			writeD(friend.getLastOnlineTime());
			writeS(friend.getNote());
			writeC(friend.getStatus().getId());
			int address = HousingService.getInstance().getPlayerAddress(friend.getOid());
			if (address > 0) {
				House house = HousingService.getInstance().getPlayerStudio(friend.getOid());
				if (house == null) {
					house = HousingService.getInstance().getHouseByAddress(address);
					writeD(house.getAddress().getId());
				} else {
					writeD(address);
				}
				writeC(house.getDoorState().getPacketValue());
			} else {
				writeD(0);
				writeC(0);
			}
			writeS(friend.getFriendNote());
		}
	}
}
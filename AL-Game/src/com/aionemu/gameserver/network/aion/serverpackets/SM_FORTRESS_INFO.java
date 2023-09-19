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

public class SM_FORTRESS_INFO extends AionServerPacket {

	private int locationId;
	private boolean teleportStatus;
	private int unk;

	public SM_FORTRESS_INFO(int locationId, boolean teleportStatus) {
		this.locationId = locationId;
		this.teleportStatus = teleportStatus;
	}

	protected void writeImpl(AionConnection con) {
		writeD(1);//4.3 protocol changed
		writeD(locationId);
		writeD(unk);//4.3 protocol changed
		writeC(teleportStatus ? 1 : 0);
	}

}
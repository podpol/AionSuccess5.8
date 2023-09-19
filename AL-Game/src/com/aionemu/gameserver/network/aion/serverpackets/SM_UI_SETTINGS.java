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

public class SM_UI_SETTINGS extends AionServerPacket
{
	private byte[] data;
	private int type;
	
	public SM_UI_SETTINGS(byte[] data, int type) {
		this.data = data;
		this.type = type;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeC(type);
		writeH(0x1C00);
		writeB(data);
		if (0x1C00 > data.length)
			writeB(new byte[0x1C00 - data.length]);
	}
}
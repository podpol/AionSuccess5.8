package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_LEAVE_GROUP_MEMBER extends AionServerPacket
{
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(0x00);
		writeC(0x00);
		writeD(0xFF);
		writeD(0x00);
		writeH(0x00);
	}
}
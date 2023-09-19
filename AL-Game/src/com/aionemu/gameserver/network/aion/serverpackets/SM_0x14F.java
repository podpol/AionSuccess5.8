package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_0x14F extends AionServerPacket{

	public SM_0x14F() {
	}
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeC(2);
    }
}

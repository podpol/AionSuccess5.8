package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 14/02/2017.
 */
public class SM_0x125 extends AionServerPacket {

	public SM_0x125() {
	}
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeH(1);
        writeC(1);
    }
}

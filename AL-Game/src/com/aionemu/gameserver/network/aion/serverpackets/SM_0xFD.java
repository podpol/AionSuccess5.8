package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 14/02/2017.
 */
public class SM_0xFD extends AionServerPacket{

	public SM_0xFD() {
	}
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeC(2);
        writeH(1);
        writeD(281602);
        writeC(0);
        writeD(1586932);
    }
}

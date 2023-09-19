package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 14/02/2017.
 */

public class SM_BLACKCLOUD_TRADE extends AionServerPacket
{
	public SM_BLACKCLOUD_TRADE() {
	}
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(0);
    }
}
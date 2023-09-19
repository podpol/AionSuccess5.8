package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 10/02/2017.
 */

public class SM_UNK_98 extends AionServerPacket
{
    @Override
    protected void writeImpl(AionConnection con) {
        writeC(1);
        writeD(0);
        writeD(0);
        writeD(0);
        writeD(0);
    }
}

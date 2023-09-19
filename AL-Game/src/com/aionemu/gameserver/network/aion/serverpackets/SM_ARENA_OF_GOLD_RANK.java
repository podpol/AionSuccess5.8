package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 14/02/2017.
 */

public class SM_ARENA_OF_GOLD_RANK extends AionServerPacket
{
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(1);
        writeD(0);
        writeD(125);
        writeD(0);
        writeD(0);
        writeD(0);
        writeD(0);
    }
}
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/02/2017.
 */

public class SM_UNK_125 extends AionServerPacket
{
    @Override
    protected void writeImpl(AionConnection con) {
        writeH(1131);
        writeC(1);
    }
}
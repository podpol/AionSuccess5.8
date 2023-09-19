package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/02/2017.
 */

public class SM_UNK_14D extends AionServerPacket
{
    @Override
	protected void writeImpl(AionConnection con) {
        writeC(2);
    }
}
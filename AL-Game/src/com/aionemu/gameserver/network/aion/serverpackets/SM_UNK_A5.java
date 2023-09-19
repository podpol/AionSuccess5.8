package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/02/2017.
 */

public class SM_UNK_A5 extends AionServerPacket
{
    private int value;
	
    public SM_UNK_A5(int value){
        this.value = value;
    }
	
    protected void writeImpl(AionConnection con) {
        writeC(value);
        writeH(0);
    }
}
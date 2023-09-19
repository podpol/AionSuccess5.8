package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/05/2017.
 */

public class SM_PLAYER_PROTECTION extends AionServerPacket {

    private int time;

    public SM_PLAYER_PROTECTION(int time){
        this.time = time;
    }

    protected void writeImpl(AionConnection con){
        writeD(time);
    }
}
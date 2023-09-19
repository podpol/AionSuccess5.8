package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/05/2017.
 */
public class SM_GODSTONE_DESTROY extends AionServerPacket {

    private int objectId;
    private int godstoneId;
    private int time;

    public SM_GODSTONE_DESTROY(int objectId, int godstoneId, int time){
        this.objectId = objectId;
        this.godstoneId = godstoneId;
        this.time = time;
    }

    @Override
    protected void writeImpl(AionConnection con) {
        writeD(objectId);
        writeD(godstoneId);
        writeD(time);
    }
}

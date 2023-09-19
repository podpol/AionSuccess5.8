package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/05/2017.
 */

public class SM_FLAG_UPDATE extends AionServerPacket {

    Npc npc;

    public SM_FLAG_UPDATE(Npc npc){
        this.npc = npc;
    }

    @Override
    protected void writeImpl(AionConnection con) {
        writeD(npc.getNpcId());
        writeD(npc.getObjectId());
    }
}
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/05/2017.
 */
public class SM_EVENT_BUFF extends AionServerPacket
{
    private final Player player;
    private final int value;

    public SM_EVENT_BUFF(Player player, int id){
        this.player = player;
        this.value = id;
    }

    protected void writeImpl(AionConnection con){
        writeD(player.getObjectId());
        writeD(value);
    }
}


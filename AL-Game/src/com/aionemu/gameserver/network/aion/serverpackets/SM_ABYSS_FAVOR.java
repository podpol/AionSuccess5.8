package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by Wnkrz on 28/08/2017.
 */

public class SM_ABYSS_FAVOR extends AionServerPacket
{
    @Override
    protected void writeImpl(AionConnection con) {
        Player player = con.getActivePlayer();
		//Percent (50000 = 5%, 100000 = 10%)
        writeQ(player.getCommonData().getAbyssFavor());
    }
}
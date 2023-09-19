package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke on 16/05/2017.
 */

public class SM_ABYSS_LANDING_LEVEL extends AionServerPacket
{
    private int id;
    private int level;
    private int newLevel;
	
    public SM_ABYSS_LANDING_LEVEL(int id, int level, int newLevel) {
        this.id = id;
        this.level = level;
        this.newLevel = newLevel;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeC(id);
        writeC(level);
        writeC(newLevel);
    }
}
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke
 */

public class SM_BATTLEFIELD_UNION extends AionServerPacket
{
    int fortressId;
    boolean isAvailable;
    int timer;
    int memberSize;
    int maxSize;
	
    public SM_BATTLEFIELD_UNION(int fortressId, boolean isAvailable, int memberSize, int maxSize) {
        this.fortressId = fortressId;
        this.isAvailable = isAvailable;
        this.memberSize = memberSize;
        this.maxSize = maxSize;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(fortressId);
        writeC(isAvailable ? 0 : 1);
        writeD(-2080374784);
        writeD(4161);
        writeD(memberSize);
        writeD(maxSize);
    }
}
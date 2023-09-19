package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by wanke
 */

public class SM_BATTLEFIELD_UNION_REGISTER extends AionServerPacket
{
    int requestId;
    boolean isRegister;
	
    public SM_BATTLEFIELD_UNION_REGISTER(int requestId, boolean register) {
        this.requestId = requestId;
        this.isRegister = register;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(requestId);
        writeC(isRegister ? 0 : 1);
    }
}

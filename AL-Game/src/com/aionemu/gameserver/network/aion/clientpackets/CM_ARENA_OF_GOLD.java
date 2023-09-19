package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ARENA_OF_GOLD_RANK;

/**
 * Created by wanke on 14/02/2017.
 */

public class CM_ARENA_OF_GOLD extends AionClientPacket
{
    public CM_ARENA_OF_GOLD(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    int unkD;
	
    @Override
    protected void readImpl() {
        this.unkD = readD();
    }
	
    @Override
    protected void runImpl() {
        sendPacket(new SM_ARENA_OF_GOLD_RANK());
    }
}
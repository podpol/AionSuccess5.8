package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;

/**
 * Created by wanke on 14/02/2017.
 */

public class CM_ARENA_OF_GOLD_RANK extends AionClientPacket
{
    public CM_ARENA_OF_GOLD_RANK(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    int unkD;
    int unkC;
	
    @Override
    protected void readImpl() {
        this.unkD = readD();
        this.unkC = readC();
    }
	
    @Override
    protected void runImpl() {
    }
}
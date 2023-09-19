package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;

import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_UNK_98;


/**
 * Created by Wnkrz on 29/08/2017.
 */
public class CM_UNK_127 extends AionClientPacket {

    public CM_UNK_127(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }

    @Override
    protected void readImpl() {
        // empty
    }

    @Override
    protected void runImpl() {
        sendPacket(new SM_UNK_98());
    }
}

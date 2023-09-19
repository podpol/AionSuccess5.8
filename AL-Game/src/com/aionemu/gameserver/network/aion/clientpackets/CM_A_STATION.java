package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.AStationService;

public class CM_A_STATION extends AionClientPacket
{
    private int action;
	
    public CM_A_STATION(int opcode, State state, State... states) {
        super(opcode, state, states);
    }
	
    @Override
    protected void readImpl() {
        action = readH();
        readH();
        readD();
        readD();
        readD();
    }
	
    @Override
    protected void runImpl() {
        Player requested = getConnection().getActivePlayer();
        switch (action) {
            case 1:
                AStationService.getInstance().handleMoveThere(requested);
            break;
            case 2:
                AStationService.getInstance().handleMoveBack(requested);
            break;
        }
    }
}
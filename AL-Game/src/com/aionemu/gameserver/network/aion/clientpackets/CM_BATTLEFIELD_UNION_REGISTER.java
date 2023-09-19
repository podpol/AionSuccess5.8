package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.services.siegeservice.BattlefieldUnionService;

/**
 * Created by wanke on 14/02/2017.
 */

public class CM_BATTLEFIELD_UNION_REGISTER extends AionClientPacket
{
    private int requestId;
    private int fortressId;
	
    public CM_BATTLEFIELD_UNION_REGISTER(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        requestId = readD();
    }
	
    @Override
    protected void runImpl() {
        if (SiegeService.getInstance().isSiegeInProgress(1011)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1011);
        } else if (SiegeService.getInstance().isSiegeInProgress(1131)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1131);
        } else if (SiegeService.getInstance().isSiegeInProgress(1132)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1132);
        } else if (SiegeService.getInstance().isSiegeInProgress(1141)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1141);
        } else if (SiegeService.getInstance().isSiegeInProgress(1221)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1221);
        } else if (SiegeService.getInstance().isSiegeInProgress(1231)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1231);
        } else if (SiegeService.getInstance().isSiegeInProgress(1241)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 1241);
        } else if (SiegeService.getInstance().isSiegeInProgress(7011)) {
            BattlefieldUnionService.getInstance().onRegister(getConnection().getActivePlayer(), requestId, 7011);
        }
    }
}
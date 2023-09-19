package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.serverpackets.SM_SEASON_RANKING;
import com.aionemu.gameserver.services.ranking.SeasonRankingUpdateService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;

import java.util.List;


/**
 * Created by Wnkrz on 24/07/2017.
 */

public class CM_SEASON_RANKING extends AionClientPacket
{
    private static Logger log = LoggerFactory.getLogger(CM_SEASON_RANKING.class);
    private int tableId;
    private int serverSwitch;
	
    public CM_SEASON_RANKING(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        tableId = readD();
        serverSwitch = readC();
    }
	
    @Override
    protected void runImpl() {
        List<SM_SEASON_RANKING> results = SeasonRankingUpdateService.getInstance().getPlayers(tableId);
        for (SM_SEASON_RANKING packet: results) {
            sendPacket(packet);
		}
    }
}
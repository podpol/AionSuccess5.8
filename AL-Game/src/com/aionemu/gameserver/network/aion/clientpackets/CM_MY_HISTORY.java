package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MY_HISTORY;
import com.aionemu.gameserver.services.ranking.SeasonRankingService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wnkrz on 24/07/2017.
 */

public class CM_MY_HISTORY extends AionClientPacket
{
    private static Logger log = LoggerFactory.getLogger(CM_SEASON_RANKING.class);
    private int tableId;
	
    public CM_MY_HISTORY(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        tableId = readD();
    }
	
    @Override
    protected void runImpl() {
        final Player player = this.getConnection().getActivePlayer();
        SeasonRankingService.getInstance().loadPacketPlayer(player, tableId);
    }
}
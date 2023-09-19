package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.ranking.SeasonRankingResult;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.List;

/**
 * Created by Wnkrz on 24/07/2017.
 */

public class SM_SEASON_RANKING extends AionServerPacket
{
    private int tableId;
    private int server_switch;
    private List<SeasonRankingResult> data;
    private int lastUpdate;
	
    public SM_SEASON_RANKING(int tableId, int s_switch, List<SeasonRankingResult> data, int lastUpdate) {
        this.tableId = tableId;
        this.data = data;
        this.lastUpdate = lastUpdate;
        this.server_switch = s_switch;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(tableId);
        writeC(server_switch);
        writeC(0);
        writeD(lastUpdate);
        writeH(data.size());
        for (SeasonRankingResult rs : data){
            writeD(tableId);
            writeD(rs.getPoints());
            writeD(rs.getRank());
            writeD(rs.getOldRank());
            writeD(0); // Sex ? 0=male / 1=female
            writeD(rs.getPlayerId());
            writeD(rs.getPlayerRace());
            writeD(rs.getPlayerClass().getClassId());
            writeC(NetworkConfig.GAMESERVER_ID);
            writeS(rs.getPlayerName(), 52);// Player Name
        }
    }
}
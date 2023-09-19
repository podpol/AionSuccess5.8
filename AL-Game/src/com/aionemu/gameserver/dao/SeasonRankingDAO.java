package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.ranking.Arena6V6Ranking;
import com.aionemu.gameserver.model.gameobjects.player.ranking.ArenaOfTenacityRank;
import com.aionemu.gameserver.model.gameobjects.player.ranking.GoldArenaRank;
import com.aionemu.gameserver.model.gameobjects.player.ranking.TowerOfChallengeRank;
import com.aionemu.gameserver.model.ranking.SeasonRankingResult;

import java.util.ArrayList;

/**
 * Created by Wnkrz on 24/07/2017.
 */
public abstract class SeasonRankingDAO implements DAO {

    @Override
    public final String getClassName() {
        return SeasonRankingDAO.class.getName();
    }

    public abstract ArrayList<SeasonRankingResult> getCompetitionRankingPlayers(int tableId);
    public abstract GoldArenaRank loadGoldArenaRank(int playerId, int tableId);
    public abstract ArenaOfTenacityRank loadArenaOfTenacityRank(int playerId, int tableId);
    public abstract TowerOfChallengeRank loadTowerOfChallengeRank(int playerId, int tableId);
    public abstract Arena6V6Ranking loadArena6v6Rank(int playerId, int tableId);

    public abstract boolean storeGoldArenaRank(Player player);
    public abstract boolean storeTowerRank(Player player);
    public abstract boolean storeTenacityRank(Player player);
    public abstract boolean store6v6Rank(Player player);
}

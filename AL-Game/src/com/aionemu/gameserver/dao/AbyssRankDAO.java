/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen, ArchSoft,   *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.AbyssRankingResult;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.AbyssRank;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.stats.AbyssRankEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ATracer
 */
public abstract class AbyssRankDAO implements DAO {

	@Override
	public final String getClassName() {
		return AbyssRankDAO.class.getName();
	}

	public abstract void loadAbyssRank(Player player);

	public abstract AbyssRank loadAbyssRank(int playerId);

	public abstract boolean storeAbyssRank(Player player);

	public abstract ArrayList<AbyssRankingResult> getAbyssRankingPlayers(Race race);

	public abstract ArrayList<AbyssRankingResult> getAbyssRankingLegions(Race race);

	public abstract Map<Integer, Integer> loadPlayersAp(Race race, final int lowerApLimit, final int maxOfflineDays);

    public abstract Map<Integer, Integer> loadPlayersGp(Race race, final int lowerGpLimit, final int maxOfflineDays);

	public abstract void updateAbyssRank(int playerId, AbyssRankEnum rankEnum);

	public abstract void updateRankList();
	
	public abstract void removePlayer(List<Player> listP);
}

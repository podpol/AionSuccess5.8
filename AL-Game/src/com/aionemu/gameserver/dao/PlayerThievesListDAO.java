/*
 * Player Thieves List
 */
package com.aionemu.gameserver.dao;

import com.aionemu.gameserver.services.events.thievesguildservice.ThievesStatusList;
import com.aionemu.commons.database.dao.DAO;

/**
 * @author Ghostfur (Aion-Unique)
 */
public abstract class PlayerThievesListDAO implements DAO {

	@Override
	public final String getClassName() {
		return PlayerThievesListDAO.class.getName();
	}

	public abstract ThievesStatusList loadThieves(int playerId);
	
	public abstract boolean saveNewThieves(ThievesStatusList thieves);

	public abstract void storeThieves(ThievesStatusList thieves);

}
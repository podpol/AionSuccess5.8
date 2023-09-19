package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.town.Town;

import java.util.Map;

public abstract class TownDAO implements DAO
{
	public abstract Map<Integer, Town> load(Race race);
	public abstract void store(Town town);
	
	@Override
	public String getClassName() {
		return TownDAO.class.getName();
	}
}
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

import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.templates.housing.HousingLand;

import java.util.Collection;
import java.util.Map;

/**
 * @author Rolandas
 */
public abstract class HousesDAO implements IDFactoryAwareDAO {

	@Override
	public String getClassName() {
		return HousesDAO.class.getName();
	}

	public abstract boolean supports(String databaseName, int majorVersion, int minorVersion);

	public abstract boolean isIdUsed(int houseObjectId);

	public abstract void storeHouse(House house);
	
	public abstract Map<Integer, House> loadHouses(Collection<HousingLand> lands, boolean studios);

	public abstract void deleteHouse(int playerId);
}

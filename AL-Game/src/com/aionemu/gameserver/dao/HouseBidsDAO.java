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
import com.aionemu.gameserver.model.house.PlayerHouseBid;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Rolandas
 */
public abstract class HouseBidsDAO implements DAO {

	@Override
	public final String getClassName() {
		return HouseBidsDAO.class.getName();
	}

	public abstract Set<PlayerHouseBid> loadBids();

	public abstract boolean addBid(int playerId, int houseId, long bidOffer, Timestamp time);

	public abstract void changeBid(int playerId, int houseId, long newBidOffer, Timestamp time);

	public abstract void deleteHouseBids(int houseId);

}

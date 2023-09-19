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
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author ATracer
 */
public abstract class ItemCooldownsDAO implements DAO {

	/**
	 * Returns unique identifier for ItemCooldownsDAO
	 * 
	 * @return unique identifier for ItemCooldownsDAO
	 */
	@Override
	public final String getClassName() {
		return ItemCooldownsDAO.class.getName();
	}

	/**
	 * @param player
	 */
	public abstract void loadItemCooldowns(Player player);

	/**
	 * @param player
	 */
	public abstract void storeItemCooldowns(Player player);

}

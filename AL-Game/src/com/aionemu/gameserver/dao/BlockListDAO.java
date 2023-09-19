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
import com.aionemu.gameserver.model.gameobjects.player.BlockList;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * Responsible for saving and loading data on players' block lists
 * 
 * @author Ben
 */
public abstract class BlockListDAO implements DAO {

	/**
	 * Loads the blocklist for the player given
	 * 
	 * @param player
	 * @return BlockList
	 */
	public abstract BlockList load(Player player);

	/**
	 * Adds the given object id to the list of blocked players for the given player
	 * 
	 * @param playerObjId
	 *          ID of player to edit the blocklist of
	 * @param objIdToBlock
	 *          ID of player to add to the blocklist
	 * @return Success
	 */
	public abstract boolean addBlockedUser(int playerObjId, int objIdToBlock, String reason);

	/**
	 * Deletes the given object id from the list of blocked players for the given player
	 * 
	 * @param playerObjId
	 *          ID of player to edit the blocklist of
	 * @param objIdToDelete
	 *          ID of player to remove from the blocklist
	 * @return Success
	 */
	public abstract boolean delBlockedUser(int playerObjId, int objIdToDelete);

	/**
	 * Sets the reason for blocking a player
	 * 
	 * @param playerObjId
	 *          Object ID of the player whos list is being edited
	 * @param blockedObjId
	 *          Object ID of the player whos reason is being edited
	 * @param reason
	 *          The reason to be set
	 * @return true or false
	 */
	public abstract boolean setReason(int playerObjId, int blockedObjId, String reason);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClassName() {
		return BlockListDAO.class.getName();
	}

}

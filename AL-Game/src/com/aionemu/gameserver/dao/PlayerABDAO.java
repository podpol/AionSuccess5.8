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
import com.aionemu.gameserver.model.atreian_bestiary.PlayerABList;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author Ranastic
 */

public abstract class PlayerABDAO implements DAO {

	@Override
	public String getClassName() {
		return PlayerABDAO.class.getName();
	}
	
	public abstract PlayerABList load(Player paramPlayer);
	public abstract boolean store(int playerObjId, int id, int kill_count, int level, int levelUpable);
	public abstract boolean delete(int playerObjId, int slot);
	public abstract int getKillCountById(int playerObjId, int id);
	public abstract int getLevelById(int playerObjId, int id);
	public abstract int getClaimRewardById(int playerObjId, int id);
}
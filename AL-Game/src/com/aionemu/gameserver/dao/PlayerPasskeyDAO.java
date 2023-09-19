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

/**
 * @author cura
 */
public abstract class PlayerPasskeyDAO implements DAO {

	/**
	 * @param accountId
	 * @param passkey
	 */
	public abstract void insertPlayerPasskey(int accountId, String passkey);

	/**
	 * @param accountId
	 * @param oldPasskey
	 * @param newPasskey
	 * @return
	 */
	public abstract boolean updatePlayerPasskey(int accountId, String oldPasskey, String newPasskey);

	/**
	 * @param accountId
	 * @param newPasskey
	 * @return
	 */
	public abstract boolean updateForcePlayerPasskey(int accountId, String newPasskey);

	/**
	 * @param accountId
	 * @param passkey
	 * @return
	 */
	public abstract boolean checkPlayerPasskey(int accountId, String passkey);

	/**
	 * @param accountId
	 * @return
	 */
	public abstract boolean existCheckPlayerPasskey(int accountId);

	/*
	 * (non-Javadoc)
	 * @see com.aionemu.commons.database.dao.DAO#getClassName()
	 */
	@Override
	public final String getClassName() {
		return PlayerPasskeyDAO.class.getName();
	}
}
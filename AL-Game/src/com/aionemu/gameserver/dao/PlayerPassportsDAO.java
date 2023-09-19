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

import java.sql.Timestamp;
import java.util.List;

import com.aionemu.commons.database.dao.DAO;

/**
 * @author Ghostfur (Aion-Unique)
 */
public abstract class PlayerPassportsDAO implements DAO {

	public abstract void insertPassport(int accountId, int passportId, int stamps, Timestamp last_stamp);

	public abstract void updatePassport(int accountId, int passportId, int stamps, boolean rewarded, Timestamp last_stamp);

	public abstract int getStamps(int accountId, int passportId);

	public abstract Timestamp getLastStamp(int accountId, int passportId);

	public abstract List<Integer> getPassports(int accountId);

	@Override
	public final String getClassName() {
		return PlayerPassportsDAO.class.getName();
	}
}
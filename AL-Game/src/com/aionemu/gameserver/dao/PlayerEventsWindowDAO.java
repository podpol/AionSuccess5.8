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
import com.aionemu.gameserver.model.event_window.PlayerEventWindowList;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author Ghostfur (Aion-Unique)
 */
public abstract class PlayerEventsWindowDAO implements DAO {

	public abstract PlayerEventWindowList load(Player accountId);

	public abstract void insert(int accountId, int eventId, Timestamp last_stamp);

	public abstract boolean store(int accountId, int eventId, Timestamp last_stamp, int elapsed);

	public abstract void delete(int accountId, int eventId);

	public abstract Timestamp getLastStamp(int accountId, int eventId);

	public abstract int getElapsed(int accountId, int eventId);

	public abstract void updateElapsed(int accountId, int eventId, int elapsed);

	public abstract int getRewardRecivedCount(int accountId, int eventId);

	public abstract void setRewardRecivedCount(int accountId, int eventId, int rewardRecivedCount);

	public abstract List<Integer> getEventsWindow(int accountId);

	public final String getClassName() {
		return PlayerEventsWindowDAO.class.getName();
	}
}
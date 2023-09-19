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
package com.aionemu.gameserver.model.event_window;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerEventsWindowDAO;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class PlayerEventWindowList implements EventWindowList<Player> {

	private final Map<Integer, PlayerEventWindowEntry> entry = new HashMap<>(0);

	public PlayerEventWindowList(List<PlayerEventWindowEntry> list) {
		for (PlayerEventWindowEntry playerEventWindowEntry : list) {
			entry.put(playerEventWindowEntry.getId(), playerEventWindowEntry);
		}
	}

	public PlayerEventWindowEntry[] getAll() {
		ArrayList<PlayerEventWindowEntry> arrayList = new ArrayList<PlayerEventWindowEntry>(entry.values());
		return arrayList.toArray(new PlayerEventWindowEntry[arrayList.size()]);
	}

	public PlayerEventWindowEntry[] getBasic() {
		return entry.values().toArray(new PlayerEventWindowEntry[entry.size()]);
	}

	/**
	 * add player event window list
	 */
	private synchronized boolean add(Player player, int remaining, Timestamp timestamp, int Time, PersistentState persistentState) {
		entry.put(remaining, new PlayerEventWindowEntry(remaining, timestamp, Time, persistentState));
		DAOManager.getDAO(PlayerEventsWindowDAO.class).store(player.getPlayerAccount().getId(), remaining, timestamp, Time);
		return true;
	}

	@Override
	public boolean add(Player player, int remaining, Timestamp timestamp, int Time) {
		return add(player, remaining, timestamp, Time, PersistentState.NEW);
	}

	/**
	 * remove player event window list
	 */
	@Override
	public synchronized boolean remove(Player player, int remaining) {
		PlayerEventWindowEntry playerEventWindowEntry = entry.get(remaining);
		if (playerEventWindowEntry != null) {
			playerEventWindowEntry.setPersistentState(PersistentState.DELETED);
			entry.remove(remaining);
			DAOManager.getDAO(PlayerEventsWindowDAO.class).delete(player.getPlayerAccount().getId(), remaining);
		}
		return entry != null;
	}

	/**
	 * size player event window list
	 */
	@Override
	public int size() {
		return entry.size();
	}
}
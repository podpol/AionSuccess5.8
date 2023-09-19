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
package com.aionemu.gameserver.model.cp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerCreativityPointsDAO;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;

public final class PlayerCPList implements CPList<Player> {

	private final Map<Integer, PlayerCPEntry> entry;

	public PlayerCPList() {
		this.entry = new HashMap<Integer, PlayerCPEntry>(0);
	}

	public PlayerCPList(List<PlayerCPEntry> entries) {
		this();
		for (PlayerCPEntry e : entries) {
			entry.put(e.getSlot(), e);
		}
	}

	public PlayerCPEntry[] getAllCP() {
		List<PlayerCPEntry> allCp = new ArrayList<PlayerCPEntry>();
		allCp.addAll(entry.values());
		return allCp.toArray(new PlayerCPEntry[allCp.size()]);
	}

	public PlayerCPEntry[] getBasicCP() {
		return entry.values().toArray(new PlayerCPEntry[entry.size()]);
	}

	@Override
	public boolean addPoint(Player player, int slot, int point) {
		return addPoint(player, slot, point, PersistentState.NEW);
	}

	private synchronized boolean addPoint(Player player, int slot, int point, PersistentState state) {
		entry.put(slot, new PlayerCPEntry(slot, point, state));
		DAOManager.getDAO(PlayerCreativityPointsDAO.class).storeCP(player.getObjectId(), slot, point);
		return true;
	}

	@Override
	public synchronized boolean removePoint(Player player, int slot) {
		PlayerCPEntry entries = entry.get(slot);
		if (entries != null) {
			entries.setPersistentState(PersistentState.DELETED);
			entry.remove(slot);
			DAOManager.getDAO(PlayerCreativityPointsDAO.class).deleteCP(player.getObjectId(), slot);
		}
		return entry != null;
	}

	@Override
	public int size() {
		return entry.size();
	}
}

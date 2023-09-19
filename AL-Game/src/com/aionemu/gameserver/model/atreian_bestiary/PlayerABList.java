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
package com.aionemu.gameserver.model.atreian_bestiary;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerABDAO;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ranastic
 */

public final class PlayerABList implements ABList<Player>
{
	private final Map<Integer, PlayerABEntry> entry;
	
	public PlayerABList() {
		this.entry = new HashMap<Integer, PlayerABEntry>(0);
	}
	
	public PlayerABList(List<PlayerABEntry> entries) {
		this();
		for (PlayerABEntry e : entries) {
			entry.put(e.getId(), e);
		}
	}
	
	public PlayerABEntry[] getAllAB() {
		List<PlayerABEntry> allCp = new ArrayList<PlayerABEntry>();
		allCp.addAll(entry.values());
		return allCp.toArray(new PlayerABEntry[allCp.size()]);
	}
	
	public PlayerABEntry[] getBasicAB() {
		return entry.values().toArray(new PlayerABEntry[entry.size()]);
	}
	
	@Override
	public boolean add(Player player, int id, int killCount, int level, int claimReward) {
		return add(player, id, killCount, level, claimReward, PersistentState.NEW);
	}
	
	private synchronized boolean add(Player player, int id, int killCount, int level, int claimReward, PersistentState state) {
		entry.put(id, new PlayerABEntry(id, killCount, level, claimReward, state));
		DAOManager.getDAO(PlayerABDAO.class).store(player.getObjectId(), id, killCount, level, claimReward);
		return true;
	}
	
	@Override
	public synchronized boolean remove(Player player, int id) {
		PlayerABEntry entries = entry.get(id);
		if (entries != null) {
			entries.setPersistentState(PersistentState.DELETED);
			entry.remove(id);
			DAOManager.getDAO(PlayerABDAO.class).delete(player.getObjectId(), id);
		}
		return entry != null;
	}
	
	@Override
	public int size() {
		return entry.size();
	}
}
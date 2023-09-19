package com.aionemu.gameserver.model.skill.linked_skill;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerStigmasEquippedDAO;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 
 * @author Ranastic
 *
 */
public final class PlayerEquippedStigmaList implements StigmaList<Player>{
	
	private static final Logger log = LoggerFactory.getLogger(PlayerEquippedStigmaList.class);
	private Map<Integer, EquippedStigmasEntry> itemList;
	private List<EquippedStigmasEntry> deletedItems;
	
	public PlayerEquippedStigmaList() {
		this.itemList = new HashMap<Integer, EquippedStigmasEntry>(0);
		this.deletedItems = new ArrayList<EquippedStigmasEntry>(0);
	}
	
	public PlayerEquippedStigmaList(List<EquippedStigmasEntry> items) {
		this();
		for (EquippedStigmasEntry entry : items) {
			itemList.put(entry.getItemId(), entry);
		}
	}
	
	public EquippedStigmasEntry[] getAllItems() {
		List<EquippedStigmasEntry> allItems = new ArrayList<EquippedStigmasEntry>();
		allItems.addAll(itemList.values());
		return allItems.toArray(new EquippedStigmasEntry[allItems.size()]);
	}
	
	public List<Integer> getAllItemsAsInteger() {
		HashSet<Integer> equippedIds = new HashSet<Integer>();
		for (EquippedStigmasEntry i : itemList.values()) {
			equippedIds.add(i.getItemId());
		}
		return Arrays.asList(equippedIds.toArray(new Integer[0]));
	}
	
	public EquippedStigmasEntry[] getDeletedItems() {
		return deletedItems.toArray(new EquippedStigmasEntry[deletedItems.size()]);
	}

	@Override
	public boolean addItem(Player player, int itemId, String itemName) {
		return addItem(player, itemId, itemName, PersistentState.NEW);
	}

	private synchronized boolean addItem(Player player, int itemId, String itemName, PersistentState state) {
		itemList.put(itemId, new EquippedStigmasEntry(itemId, itemName, state));
		DAOManager.getDAO(PlayerStigmasEquippedDAO.class).storeItems(player);
		return true;
	}
	
	@Override
	public boolean remove(Player player, int itemId) {
		EquippedStigmasEntry entry = itemList.get(itemId);
		if (entry != null) {
			entry.setPersistentState(PersistentState.DELETED);
			deletedItems.add(entry);
		if (player != null)
			DAOManager.getDAO(PlayerStigmasEquippedDAO.class).storeItems(player);
			itemList.remove(itemId);
		}
		return entry != null;
	}

	@Override
	public boolean isItemPresent(int itemId) {
		return itemList.containsKey(itemId);
	}

	@Override
	public int size() {
		return itemList.size();
	}

}

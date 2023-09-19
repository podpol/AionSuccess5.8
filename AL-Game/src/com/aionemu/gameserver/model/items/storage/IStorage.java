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
package com.aionemu.gameserver.model.items.storage;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.item.ItemPacketService.ItemDeleteType;
import com.aionemu.gameserver.services.item.ItemPacketService.ItemUpdateType;
import javolution.util.FastList;

import java.util.List;
import java.util.Queue;

/**
 * Public interface for Storage, later will rename probably
 * 
 * @author ATracer
 */
public interface IStorage {

	/**
	 * @param player
	 */
	void setOwner(Player player);

	/**
	 * @return current kinah count
	 */
	long getKinah();

	/**
	 * @return kinah item or null if storage never had kinah
	 */
	Item getKinahItem();


	/**
	 * @return
	 */
	StorageType getStorageType();

	/**
	 * @param amount
	 */
	void increaseKinah(long amount);

	/**
	 * @param amount
	 * @param updateType
	 */
	void increaseKinah(long amount, ItemUpdateType updateType);


	/**
	 * @param amount
	 * @return
	 */
	boolean tryDecreaseKinah(long amount);

	/**
	 * @param amount
	 */
	void decreaseKinah(long amount);

	/**
	 * @param amount
	 * @param updateType
	 */
	void decreaseKinah(long amount, ItemUpdateType updateType);


	/**
	 * @param item
	 * @param count
	 * @return
	 */
	long increaseItemCount(Item item, long count);

	/**
	 * @param item
	 * @param count
	 * @param updateType
	 * @return
	 */
	long increaseItemCount(Item item, long count, ItemUpdateType updateType);

	/**
	 * @param item
	 * @param count
	 * @return
	 */
	long decreaseItemCount(Item item, long count);

	/**
	 * @param item
	 * @param count
	 * @param updateType
	 * @return
	 */
	long decreaseItemCount(Item item, long count, ItemUpdateType updateType);

	/**
	 * Add operation should be used for new items incoming into storage from outside
	 */
	Item add(Item item);
	
	/**
	 * Put operation is used in some operations like unequip
	 */
	Item put(Item item);

	/**
	 * @param item
	 * @return
	 */
	Item remove(Item item);

	/**
	 * @param item
	 * @return
	 */
	Item delete(Item item);

	/**
	 * @param item
	 * @param deleteType
	 * @return
	 */
	Item delete(Item item, ItemDeleteType deleteType);

	/**
	 * @param itemId
	 * @param count
	 * @return
	 */
	boolean decreaseByItemId(int itemId, long count);

	/**
	 * @param itemObjId
	 * @param count
	 * @return
	 */
	boolean decreaseByObjectId(int itemObjId, long count);

	/**
	 * @param itemObjId
	 * @param count
	 * @param updateType
	 * @return
	 */
	boolean decreaseByObjectId(int itemObjId, long count, ItemUpdateType updateType);

	/**
	 * @param itemId
	 * @return
	 */
	Item getFirstItemByItemId(int itemId);

	/**
	 * @return
	 */
	FastList<Item> getItemsWithKinah();

	/**
	 * @return
	 */
	List<Item> getItems();

	/**
	 * @param itemId
	 * @return
	 */
	List<Item> getItemsByItemId(int itemId);

	/**
	 * @param itemObjId
	 * @return
	 */
	Item getItemByObjId(int itemObjId);

	/**
	 * @param itemId
	 * @return
	 */
	long getItemCountByItemId(int itemId);

	/**
	 * @return
	 */
	boolean isFull();

	/**
	 * @return
	 */
	int getFreeSlots();

	/**
	 * @return
	 */
	int getLimit();

	/**
	 * @return
	 */
	int size();

	/**
	 * @return
	 */
	PersistentState getPersistentState();

	/**
	 * @param persistentState
	 */
	void setPersistentState(PersistentState persistentState);

	/**
	 * @return
	 */
	Queue<Item> getDeletedItems();

	/**
	 * @param item
	 */
	void onLoadHandler(Item item);

}

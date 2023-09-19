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
package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.gameserver.model.trade.TradePSItem;

import java.util.LinkedHashMap;

/**
 * @author Xav Modified by Simple
 */
public class PrivateStore {

	private Player owner;
	private LinkedHashMap<Integer, TradePSItem> items;
	private String storeMessage;

	/**
	 * This method binds a player to the store and creates a list of items
	 * 
	 * @param owner
	 */
	public PrivateStore(Player owner) {
		this.owner = owner;
		this.items = new LinkedHashMap<Integer, TradePSItem>();
	}

	/**
	 * This method will return the owner of the store
	 * 
	 * @return Player
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * This method will return the items being sold
	 * 
	 * @return LinkedHashMap<Integer, TradePSItem>
	 */
	public LinkedHashMap<Integer, TradePSItem> getSoldItems() {
		return items;
	}

	/**
	 * This method will add an item to the list and price
	 * 
	 * @param tradeList
	 * @param price
	 */
	public void addItemToSell(int itemObjId, TradePSItem tradeItem) {
		items.put(itemObjId, tradeItem);
	}

	/**
	 * This method will remove an item from the list
	 * 
	 * @param item
	 */
	public void removeItem(int itemObjId) {
		if (items.containsKey(itemObjId)) {
			LinkedHashMap<Integer, TradePSItem> newItems = new LinkedHashMap<Integer, TradePSItem>();
			for (int itemObjIds : items.keySet()) {
				if (itemObjId != itemObjIds)
					newItems.put(itemObjIds, items.get(itemObjIds));
			}
			this.items = newItems;
		}
	}

	/**
	 * @param itemId
	 *          return tradeItem
	 */
	public TradePSItem getTradeItemByObjId(int itemObjId) {
		return items.get(itemObjId);
	}

	/**
	 * @param storeMessage
	 *          the storeMessage to set
	 */
	public void setStoreMessage(String storeMessage) {
		this.storeMessage = storeMessage;
	}

	/**
	 * @return the storeMessage
	 */
	public String getStoreMessage() {
		return storeMessage;
	}
}

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
package com.aionemu.gameserver.model.trade;

import com.aionemu.gameserver.model.gameobjects.Item;

/**
 * @author ATracer
 */
public class ExchangeItem {

	private int itemObjId;
	private long itemCount;
	private int itemDesc;
	private Item item;

	/**
	 * Used when exchange item != original item
	 * 
	 * @param itemObjId
	 * @param itemCount
	 * @param item
	 */
	public ExchangeItem(int itemObjId, long itemCount, Item item) {
		this.itemObjId = itemObjId;
		this.itemCount = itemCount;
		this.item = item;
		this.itemDesc = item.getItemTemplate().getNameId();
	}

	/**
	 * @param item
	 *          the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @param countToAdd
	 */
	public void addCount(long countToAdd) {
		this.itemCount += countToAdd;
		this.item.setItemCount(itemCount);
	}

	/**
	 * @return the newItem
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @return the itemObjId
	 */
	public int getItemObjId() {
		return itemObjId;
	}

	/**
	 * @return the itemCount
	 */
	public long getItemCount() {
		return itemCount;
	}

	/**
	 * @return the itemDesc
	 */
	public int getItemDesc() {
		return itemDesc;
	}
}

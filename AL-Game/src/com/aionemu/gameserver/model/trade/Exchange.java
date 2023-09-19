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
import com.aionemu.gameserver.model.gameobjects.player.Player;
import javolution.util.FastList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ATracer
 */
public class Exchange {

	private Player activeplayer;
	private Player targetPlayer;

	private boolean confirmed;
	private boolean locked;

	private long kinahCount;

	private Map<Integer, ExchangeItem> items = new HashMap<Integer, ExchangeItem>();
	private List<Item> itemsToUpdate = FastList.newInstance();

	public Exchange(Player activeplayer, Player targetPlayer) {
		super();
		this.activeplayer = activeplayer;
		this.targetPlayer = targetPlayer;
	}

	public void confirm() {
		confirmed = true;
	}

	/**
	 * @return the confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	public void lock() {
		this.locked = true;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param exchangeItem
	 */
	public void addItem(int parentItemObjId, ExchangeItem exchangeItem) {
		this.items.put(parentItemObjId, exchangeItem);
	}

	/**
	 * @param countToAdd
	 */
	public void addKinah(long countToAdd) {
		this.kinahCount += countToAdd;
	}

	/**
	 * @return the activeplayer
	 */
	public Player getActiveplayer() {
		return activeplayer;
	}

	/**
	 * @return the targetPlayer
	 */
	public Player getTargetPlayer() {
		return targetPlayer;
	}

	/**
	 * @return the kinahCount
	 */
	public long getKinahCount() {
		return kinahCount;
	}

	/**
	 * @return the items
	 */
	public Map<Integer, ExchangeItem> getItems() {
		return items;
	}

	public boolean isExchangeListFull() {
		return items.size() > 18;
	}

	/**
	 * @return the itemsToUpdate
	 */
	public List<Item> getItemsToUpdate() {
		return itemsToUpdate;
	}

	/**
	 * @param item
	 */
	public void addItemToUpdate(Item item) {
		itemsToUpdate.add(item);
	}
}

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
package com.aionemu.gameserver.model.templates.pet;

import java.util.Arrays;

/**
 * @author Rolandas
 */
public class PetDopingBag {

	private int[] itemBag = null;
	private boolean isDirty = false;

	public void setFoodItem(int itemId) {
		setItem(itemId, 0);
	}

	public int getFoodItem() {
		if (itemBag == null || itemBag.length < 1)
			return 0;
		return itemBag[0];
	}

	public void setDrinkItem(int itemId) {
		setItem(itemId, 1);
	}

	public int getDrinkItem() {
		if (itemBag == null || itemBag.length < 2)
			return 0;
		return itemBag[1];
	}

	/**
	 * Adds or removes item to the bag
	 * @param itemId - item Id, or 0 to remove
	 * @param slot - slot number; 0 for food, 1 for drink, the rest are for scrolls
	 */
	public void setItem(int itemId, int slot) {
		if (itemBag == null) {
			itemBag = new int[slot + 1];
			isDirty = true;
		}
		else if (slot > itemBag.length - 1) {
			itemBag = Arrays.copyOf(itemBag, slot + 1);
			isDirty = true;
		}
		if (itemBag[slot] != itemId) {
			itemBag[slot] = itemId;
			isDirty = true;
		}
	}

	public int[] getScrollsUsed() {
		if (itemBag == null || itemBag.length < 3)
			return new int[0];
		return Arrays.copyOfRange(itemBag, 2, itemBag.length);
	}

	/**
	 * @return true if the bag needs saving
	 */
	public boolean isDirty() {
		return isDirty;
	}

}

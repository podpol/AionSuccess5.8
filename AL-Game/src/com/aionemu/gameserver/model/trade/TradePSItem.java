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

/**
 * @author Simple
 */
public class TradePSItem extends TradeItem {

	private int itemObjId;
	private long price;

	/**
	 * @param itemId
	 * @param count
	 */
	public TradePSItem(int itemObjId, int itemId, long count, long price) {
		super(itemId, count);
		this.setPrice(price);
		this.setItemObjId(itemObjId);
	}

	/**
	 * @param price
	 *          the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * @param itemObjId
	 *          the itemObjId to set
	 */
	public void setItemObjId(int itemObjId) {
		this.itemObjId = itemObjId;
	}

	/**
	 * @return the itemObjId
	 */
	public int getItemObjId() {
		return itemObjId;
	}

}

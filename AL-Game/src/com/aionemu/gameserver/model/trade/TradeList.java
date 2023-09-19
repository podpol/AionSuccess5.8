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

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.Acquisition;
import com.aionemu.gameserver.model.templates.item.AcquisitionType;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.trade.PricesService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ATracer modified by Wakizashi
 */
public class TradeList {

	private int sellerObjId;

	private List<TradeItem> tradeItems = new ArrayList<TradeItem>();

	private long requiredKinah;

	private int requiredAp;

	private Map<Integer, Long> requiredItems = new HashMap<Integer, Long>();

	public TradeList() {

	}

	public TradeList(int sellerObjId) {
		this.sellerObjId = sellerObjId;
	}

	/**
	 * @param itemId
	 * @param count
	 */
	public void addBuyItem(int itemId, long count) {

		ItemTemplate itemTemplate = DataManager.ITEM_DATA.getItemTemplate(itemId);
		if (itemTemplate != null) {
			TradeItem tradeItem = new TradeItem(itemId, count);
			tradeItem.setItemTemplate(itemTemplate);
			tradeItems.add(tradeItem);
		}
	}

	/**
	 * @param itemId
	 * @param count
	 */
	public void addPSItem(int itemId, long count) {
		TradeItem tradeItem = new TradeItem(itemId, count);
		tradeItems.add(tradeItem);
	}

	/**
	 * @param itemObjId
	 * @param count
	 */
	public void addSellItem(int itemObjId, long count) {
		TradeItem tradeItem = new TradeItem(itemObjId, count);
		tradeItems.add(tradeItem);
	}

	/**
	 * @return price TradeList sum price
	 */
	public boolean calculateBuyListPrice(Player player, int modifier) {
		long availableKinah = player.getInventory().getKinah();
		requiredKinah = 0;

		for (TradeItem tradeItem : tradeItems) {
			requiredKinah += PricesService.getKinahForBuy(tradeItem.getItemTemplate().getPrice(), player.getRace())
				* tradeItem.getCount() * modifier / 100;
		}

		return availableKinah >= requiredKinah;
	}

	/**
	 * @return true or false
	 */
	public boolean calculateAbyssBuyListPrice(Player player) {
		int ap = player.getAbyssRank().getAp();

		this.requiredAp = 0;
		this.requiredItems.clear();

		for (TradeItem tradeItem : tradeItems) {
			Acquisition aquisition = tradeItem.getItemTemplate().getAcquisition();
			if (aquisition == null || aquisition.getType() != AcquisitionType.ABYSS
				&& aquisition.getType() != AcquisitionType.AP)
				continue;

			requiredAp += aquisition.getRequiredAp() * tradeItem.getCount();

			int abysItemId = aquisition.getItemId();
			if (abysItemId == 0) // no abyss required item (medals, etc))
				continue;
			
			long alreadyAddedCount = 0;
			if (requiredItems.containsKey(abysItemId))
				alreadyAddedCount = requiredItems.get(abysItemId);
			if (alreadyAddedCount == 0)
				requiredItems.put(abysItemId, (long) aquisition.getItemCount());
			else
				requiredItems.put(abysItemId, alreadyAddedCount + aquisition.getItemCount() * tradeItem.getCount());
		}

		if (ap < requiredAp) {
			// You do not have enough Abyss Points.
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1300927));
			return false;
		}

		for (Integer itemId : requiredItems.keySet()) {
			long count = player.getInventory().getItemCountByItemId(itemId);
			if (requiredItems.get(itemId) < 1 || count < requiredItems.get(itemId))
				return false;
		}

		return true;
	}

	/**
	 * @return true or false
	 */
	public boolean calculateRewardBuyListPrice(Player player) {
		this.requiredItems.clear();

		for (TradeItem tradeItem : tradeItems) {
			Acquisition aquisition = tradeItem.getItemTemplate().getAcquisition();
			if (aquisition == null || aquisition.getType() != AcquisitionType.REWARD
				&& aquisition.getType() != AcquisitionType.COUPON)
				continue;

			int itemId = aquisition.getItemId();
			long alreadyAddedCount = 0;
			if (requiredItems.containsKey(itemId))
				alreadyAddedCount = requiredItems.get(itemId);
			if (alreadyAddedCount == 0)
				requiredItems.put(itemId, aquisition.getItemCount() * tradeItem.getCount());
			else
				requiredItems.put(itemId, alreadyAddedCount + aquisition.getItemCount() * tradeItem.getCount());
		}

		for (Integer itemId : requiredItems.keySet()) {
			long count = player.getInventory().getItemCountByItemId(itemId);
			if (requiredItems.get(itemId) < 1 || count < requiredItems.get(itemId))
				return false;
		}

		return true;
	}

	/**
	 * @return the tradeItems
	 */
	public List<TradeItem> getTradeItems() {
		return tradeItems;
	}

	public int size() {
		return tradeItems.size();
	}

	/**
	 * @return the npcId
	 */
	public int getSellerObjId() {
		return sellerObjId;
	}

	/**
	 * @return the requiredAp
	 */
	public int getRequiredAp() {
		return requiredAp;
	}

	/**
	 * @return the requiredKinah
	 */
	public long getRequiredKinah() {
		return requiredKinah;
	}

	/**
	 * @return the requiredItems
	 */
	public Map<Integer, Long> getRequiredItems() {
		return requiredItems;
	}
}

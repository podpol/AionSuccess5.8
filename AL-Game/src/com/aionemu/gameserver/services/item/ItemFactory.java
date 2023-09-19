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
package com.aionemu.gameserver.services.item;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ATracer
 */
public class ItemFactory {

	private static final Logger log = LoggerFactory.getLogger(ItemFactory.class);

	public static final Item newItem(int itemId) {
		ItemTemplate itemTemplate = DataManager.ITEM_DATA.getItemTemplate(itemId);
		if (itemTemplate == null) {
			log.error("Item was not populated correctly. Item template is missing for item id: " + itemId);
			return null;
		}
		return new Item(IDFactory.getInstance().nextId(), itemTemplate);
	}

	public static Item newItem(int itemId, long count) {
		Item item = newItem(itemId);
		item.setItemCount(calculateCount(item.getItemTemplate(), count));
		return item;
	}

	private static final long calculateCount(ItemTemplate itemTemplate, long count) {
		long maxStackCount = itemTemplate.getMaxStackCount();
		if (count > maxStackCount && !itemTemplate.isKinah())
			count = maxStackCount;
		return count;
	}

}

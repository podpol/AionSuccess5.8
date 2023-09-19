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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.itemset.ItemPart;
import com.aionemu.gameserver.model.templates.itemset.ItemSetTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author ATracer
 */
@XmlRootElement(name = "item_sets")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemSetData {

	@XmlElement(name = "itemset")
	protected List<ItemSetTemplate> itemsetList;

	private TIntObjectHashMap<ItemSetTemplate> sets;

	// key: item id, value: associated item set template
	// This should provide faster search of the item template set by item id
	private TIntObjectHashMap<ItemSetTemplate> setItems;

	void afterUnmarshal(Unmarshaller u, Object parent) {
		sets = new TIntObjectHashMap<ItemSetTemplate>();
		setItems = new TIntObjectHashMap<ItemSetTemplate>();

		for (ItemSetTemplate set : itemsetList) {
			sets.put(set.getId(), set);

			// Add reference to the ItemSetTemplate from
			for (ItemPart part : set.getItempart()) {
				setItems.put(part.getItemid(), set);
			}
		}
		itemsetList = null;
	}

	/**
	 * @param itemSetId
	 * @return
	 */
	public ItemSetTemplate getItemSetTemplate(int itemSetId) {
		return sets.get(itemSetId);
	}

	/**
	 * @param itemId
	 * @return
	 */
	public ItemSetTemplate getItemSetTemplateByItemId(int itemId) {
		return setItems.get(itemId);
	}

	/**
	 * @return itemSets.size()
	 */
	public int size() {
		return sets.size();
	}
}

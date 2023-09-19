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
package com.aionemu.gameserver.model.items;

import com.aionemu.gameserver.dataholders.loadingutils.adapters.NpcEquipmentList;
import com.aionemu.gameserver.dataholders.loadingutils.adapters.NpcEquippedGearAdapter;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * @author Luno
 */
@XmlJavaTypeAdapter(NpcEquippedGearAdapter.class)
public class NpcEquippedGear implements Iterable<Entry<ItemSlot, ItemTemplate>> {

	private Map<ItemSlot, ItemTemplate> items;
	private short mask;

	private NpcEquipmentList v;

	public NpcEquippedGear(NpcEquipmentList v) {
		this.v = v;
	}

	/**
	 * @return short
	 */
	public short getItemsMask() {
		if (items == null)
			init();
		return mask;
	}

	@Override
	public Iterator<Entry<ItemSlot, ItemTemplate>> iterator() {
		if (items == null)
			init();
		return items.entrySet().iterator();
	}

	/**
	 * Here NPC equipment mask is initialized. All NPC slot masks should be lower than 65536
	 */
	public void init() {
		synchronized (this) {
			if (items == null) {
				items = new TreeMap<ItemSlot, ItemTemplate>();
				for (ItemTemplate item : v.items) {
					ItemSlot[] itemSlots = ItemSlot.getSlotsFor(item.getItemSlot());
					for (ItemSlot itemSlot : itemSlots) {
						if (items.get(itemSlot) == null) {
							items.put(itemSlot, item);
							mask |= itemSlot.getSlotIdMask();
							break;
						}
					}
				}
			}
			v = null;
		}
	}

	/**
	 * @param itemSlot
	 * @return
	 */
	public ItemTemplate getItem(ItemSlot itemSlot) {
		return items != null ? items.get(itemSlot) : null;
	}

}

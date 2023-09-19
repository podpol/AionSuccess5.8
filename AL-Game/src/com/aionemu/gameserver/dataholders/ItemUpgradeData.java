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

import com.aionemu.gameserver.GameServer;
import com.aionemu.gameserver.model.templates.item.upgrade.ItemUpgradeTemplate;
import com.aionemu.gameserver.model.templates.item.upgrade.UpgradeResultItem;
import gnu.trove.map.hash.TIntObjectHashMap;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */
 
@XmlRootElement(name = "item_upgrades")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemUpgradeData
{
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(GameServer.class);
	
	@XmlElement(name = "item_upgrade")
	protected List<ItemUpgradeTemplate> itemUpgradeTemplates;
	
	private TIntObjectHashMap<ItemUpgradeTemplate> itemUpgradeSets;
	private FastMap<Integer, FastMap<Integer, UpgradeResultItem>> upgradeResultItemMap;
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		itemUpgradeSets = new TIntObjectHashMap<ItemUpgradeTemplate>();
		upgradeResultItemMap = new FastMap<Integer, FastMap<Integer, UpgradeResultItem>>();
		for (ItemUpgradeTemplate set : itemUpgradeTemplates) {
			itemUpgradeSets.put(set.getUpgrade_base_item_id(), set);
			upgradeResultItemMap.put(set.getUpgrade_base_item_id(), new FastMap<Integer, UpgradeResultItem>());
			if (!set.getUpgrade_result_item().isEmpty()) {
				for (UpgradeResultItem resultItem : set.getUpgrade_result_item()) {
					upgradeResultItemMap.get(set.getUpgrade_base_item_id()).put(resultItem.getItem_id(), resultItem);
				}
			}
		}
		itemUpgradeTemplates = null;
	}
	
	public ItemUpgradeTemplate getItemUpgradeTemplate(int itemSetId) {
		return itemUpgradeSets.get(itemSetId);
	}
	
	public FastMap<Integer, UpgradeResultItem> getResultItemMap(int baseItemId) {
		if (upgradeResultItemMap.containsKey(baseItemId)) {
			if (!upgradeResultItemMap.get(baseItemId).isEmpty()) {
				return upgradeResultItemMap.get(baseItemId);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public int size() {
		return itemUpgradeSets.size();
	}
}
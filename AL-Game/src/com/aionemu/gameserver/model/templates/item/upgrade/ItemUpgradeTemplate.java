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
package com.aionemu.gameserver.model.templates.item.upgrade;

import com.aionemu.gameserver.model.stats.calc.StatOwner;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

@XmlRootElement(name = "ItemUpgrade")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemUpgradeTemplate implements StatOwner
{
	protected List<UpgradeResultItem> upgrade_result_item;
	
	@XmlAttribute(name = "upgrade_base_item")
	private int upgrade_base_item_id;
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
	}
	
	public List<UpgradeResultItem> getUpgrade_result_item() {
		return upgrade_result_item;
	}
	
	public int getUpgrade_base_item_id() {
		return upgrade_base_item_id;
	}
}
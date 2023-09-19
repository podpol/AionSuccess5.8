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

import com.aionemu.gameserver.model.templates.teleport.MultiReturn;
import com.aionemu.gameserver.model.templates.teleport.MultiReturnLocationList;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlRootElement(name = "multi_returns")
@XmlAccessorType(XmlAccessType.FIELD)
public class MultiReturnItemData
{
	@XmlElement(name = "item")
	private List<MultiReturn> ItemList;
	
	private TIntObjectHashMap<List<MultiReturnLocationList>> ItemLocationList = new TIntObjectHashMap<>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		ItemLocationList.clear();
		for (MultiReturn template: ItemList) {
			ItemLocationList.put(template.getId(), template.getMultiReturnList());
		}
	}
	
	public int size() {
		return ItemLocationList.size();
	}
	
	public MultiReturn getMultiReturnById(int id) {
		for (MultiReturn template: ItemList) {
			if (template.getId() == id) {
				return template;
			}
		}
		return null;
	}
	
	public List<MultiReturn> getMultiReturns() {
		return ItemList;
	}
}
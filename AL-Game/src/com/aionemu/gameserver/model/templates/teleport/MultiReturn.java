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
package com.aionemu.gameserver.model.templates.teleport;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlType(name = "MultiReturn")
public class MultiReturn
{
	@XmlAttribute(name = "id")
	private int id;
	
	@XmlElement(name = "loc")
	private List<MultiReturnLocationList> MultiReturnList;
	
	public int getId() {
		return id;
	}
	
	public MultiReturnLocationList getReturnDataById(int id) {
		if (MultiReturnList != null) {
			return MultiReturnList.get(id);
		}
		return null;
	}
	
	public List<MultiReturnLocationList> getMultiReturnList() {
		return MultiReturnList;
	}
}
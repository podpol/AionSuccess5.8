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
package com.aionemu.gameserver.model.templates.event;


import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.*;

/**
 * @author Rolandas
 */

@XmlType(name = "InventoryDrop")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryDrop
{
	@XmlValue
	private int dropItem;
	
	@XmlAttribute(name = "startlevel", required = false)
	private int startLevel;
	
	@XmlAttribute(name = "endlevel", required = false)
	private int endLevel;
	
	@XmlAttribute(name = "interval", required = true)
	private int interval;
	
	@XmlAttribute(name = "maxCountOfDay", required = false)
	private int maxCountOfDay;
	
	@XmlAttribute(name = "cleanTime", required = false)
	private int cleanTime;
	
	@XmlAttribute
	private Race race = Race.PC_ALL;
	
	public Race getRace() {
		return race;
	}
	
	public int getDropItem() {
		return dropItem;
	}
	
	public int getStartLevel() {
		return startLevel;
	}
	
	public int getEndLevel() {
		return endLevel;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public int getMaxCountOfDay() {
		return maxCountOfDay;
	}
	
	public int getCleanTime() {
		return cleanTime;
	}
}
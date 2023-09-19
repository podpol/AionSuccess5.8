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
package com.aionemu.gameserver.model.templates.zone;

import com.aionemu.gameserver.model.geometry.Area;


/**
 * @author MrPoke
 *
 */
public class ZoneInfo{
	private Area area;
	private ZoneTemplate zoneTemplate;

	/**
	 * @param area
	 * @param zoneTemplate
	 */
	public ZoneInfo(Area area, ZoneTemplate zoneTemplate) {
		this.area = area;
		this.zoneTemplate = zoneTemplate;
	}
	

	
	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	
	/**
	 * @return the zoneTemplate
	 */
	public ZoneTemplate getZoneTemplate() {
		return zoneTemplate;
	}
}

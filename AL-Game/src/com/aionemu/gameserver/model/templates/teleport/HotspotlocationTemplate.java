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


import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "hotspot_template")
@XmlAccessorType(XmlAccessType.NONE)
public class HotspotlocationTemplate
{
	@XmlAttribute(name = "loc_id", required = true)
	private int locId;
	
	@XmlAttribute(name = "mapid", required = true)
	private int mapid = 0;
	
	@XmlAttribute(name = "name", required = true)
	private String name = "";
	
	@XmlAttribute(name = "name_id", required = true)
	private int nameId;
	
	@XmlAttribute(name = "price")
	private int price;
	
	@XmlAttribute(name = "race")
	private Race race = Race.PC_ALL;
	
	@XmlAttribute(name = "posX")
	private float x = 0;
	
	@XmlAttribute(name = "posY")
	private float y = 0;
	
	@XmlAttribute(name = "posZ")
	private float z = 0;
	
	@XmlAttribute(name = "heading")
	private int heading = 0;
	
	public int getLocId() {
		return locId;
	}
	
	public int getMapId() {
		return mapid;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNameId() {
		return nameId;
	}
	
	public int getPrice() {
		return price;
	}
	
	public Race getRace() {
		return race;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public int getHeading() {
		return heading;
	}
}
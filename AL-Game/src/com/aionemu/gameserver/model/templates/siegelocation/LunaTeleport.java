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
package com.aionemu.gameserver.model.templates.siegelocation;

import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LunaTeleport")
public class LunaTeleport
{
	@XmlAttribute(name = "world_id")
	protected int worldId;
	
	@XmlAttribute(name = "race")
	protected Race race = Race.PC_ALL;
	
	@XmlAttribute(name = "x")
	protected float x;
	
	@XmlAttribute(name = "y")
	protected float y;
	
	@XmlAttribute(name = "z")
	protected float z;
	
	@XmlAttribute(name = "h")
	protected byte h;
	
	public int getWorldId() {
		return worldId;
	}
	
	public void setWorldId(int value) {
		worldId = value;
	}
	
	public Race getRace() {
		return race;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float value) {
		x = value;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float value) {
		y = value;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float value) {
		z = value;
	}
	
	public byte getH() {
		return h;
	}
	
	public void setH(byte value) {
		h = value;
	}
}
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
package com.aionemu.gameserver.model.templates.revive_start_points;

import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 22/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorldReviveStartPoints")
public class WorldReviveStartPoints
{
    @XmlAttribute(name = "world_id")
    protected int worldId;
	
    @XmlAttribute(name = "name")
    protected String name;
	
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
	
    @XmlAttribute(name = "max_level")
    protected int maxLevel;
	
    @XmlAttribute(name = "min_level")
    protected int minLevel;
	
    public int getReviveWorld() {
        return worldId;
    }
	
    public Race getRace() {
        return race;
    }
	
    public void setRace(Race value) {
        race = value;
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
	
    public int getMaxlevel() {
        return maxLevel;
    }
	
    public int getMinlevel() {
        return minLevel;
    }
}
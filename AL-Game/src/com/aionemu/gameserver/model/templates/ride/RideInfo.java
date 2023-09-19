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
package com.aionemu.gameserver.model.templates.ride;

import com.aionemu.gameserver.model.templates.RideBound;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="RideInfo", propOrder={"ridebound"})
public class RideInfo
{
    protected RideBound ridebound;
	
    @XmlAttribute(name="cost_fp")
    protected Integer costFp;
	
    @XmlAttribute(name="start_fp")
    protected int startFp;
	
    @XmlAttribute(name="sprint_speed")
    protected float sprintSpeed;
	
    @XmlAttribute(name="fly_speed")
    protected float flySpeed;
	
    @XmlAttribute(name="move_speed")
    protected float moveSpeed;
	
    @XmlAttribute
    protected Integer type;
	
    @XmlAttribute(required=true)
    protected int id;
	
    public RideBound getRideBound() {
        return ridebound;
    }
	
    public Integer getCostFp() {
        return costFp;
    }
	
    public int getStartFp() {
        return startFp;
    }
	
    public float getSprintSpeed() {
        return sprintSpeed;
    }
	
    public float getFlySpeed() {
        return flySpeed;
    }
	
    public float getMoveSpeed() {
        return moveSpeed;
    }
	
    public Integer getType() {
        return type;
    }
	
    public int getNpcId() {
        return id;
    }
	
    public boolean canSprint() {
        return sprintSpeed != 0.0f;
    }
}
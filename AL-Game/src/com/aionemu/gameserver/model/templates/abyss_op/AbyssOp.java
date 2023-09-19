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
package com.aionemu.gameserver.model.templates.abyss_op;


import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rinzler (Encom)
 */

@XmlType(name = "abyss_op")
@XmlAccessorType(XmlAccessType.NONE)
public class AbyssOp
{
	@XmlAttribute(name="id", required = true)
	private int id;
	
	@XmlAttribute(name = "npc_id", required = true)
	private int npcId;
	
	@XmlAttribute(name="type", required = true)
	private AbyssOpType abyssOpType;
	
	@XmlAttribute(name="siege_id", required=true)
	private int siegeId;
	
	@XmlAttribute(name = "race")
	protected Race race = Race.PC_ALL;
	
	@XmlAttribute(name="group_id", required=true)
	private int groupId;
	
	@XmlAttribute(name="points", required=true)
	private int points;
	
	public int getId() {
        return this.id;
    }
	
	public AbyssOpType getAbyssOpType() {
		return abyssOpType;
	}
	
	public Race getRace() {
		return race;
	}
	
	public int getSiegeId() {
		return siegeId;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getNpcId() {
		return npcId;
	}
}
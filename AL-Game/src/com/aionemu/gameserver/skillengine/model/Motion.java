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
package com.aionemu.gameserver.skillengine.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author kecimis
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Motion")
public class Motion {

	@XmlAttribute(required = true)
	protected String name;// TODO enum

	@XmlAttribute
	protected int speed = 100;

	@XmlAttribute(name = "instant_skill")
	protected boolean instantSkill = false;

	public String getName() {
		return this.name;
	}

	public int getSpeed() {
		return this.speed;
	}

	public boolean getInstantSkill() {
		return this.instantSkill;
	}
}

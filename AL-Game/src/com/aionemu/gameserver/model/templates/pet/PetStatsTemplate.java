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
package com.aionemu.gameserver.model.templates.pet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author M@xx
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "petstats")
public class PetStatsTemplate {

	@XmlAttribute(name = "reaction", required = true)
	private String reaction;

	@XmlAttribute(name = "run_speed", required = true)
	private float runSpeed;

	@XmlAttribute(name = "walk_speed", required = true)
	private float walkSpeed;

	@XmlAttribute(name = "height", required = true)
	private float height;

	@XmlAttribute(name = "altitude", required = true)
	private float altitude;

	public String getReaction() {
		return reaction;
	}

	public float getRunSpeed() {
		return runSpeed;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public float getHeight() {
		return height;
	}

	public float getAltitude() {
		return altitude;
	}
}

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
package com.aionemu.gameserver.model.templates.stats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Luno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "player_stats_template")
public class PlayerStatsTemplate extends StatsTemplate {

	@XmlAttribute(name = "power")
	private int power;
	@XmlAttribute(name = "health")
	private int health;
	@XmlAttribute(name = "agility")
	private int agility;
	@XmlAttribute(name = "accuracy")
	private int accuracy;
	@XmlAttribute(name = "knowledge")
	private int knowledge;
	@XmlAttribute(name = "will")
	private int will;

	public int getPower() {
		return power;
	}

	public int getHealth() {
		return health;
	}

	public int getAgility() {
		return agility;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getKnowledge() {
		return knowledge;
	}

	public int getWill() {
		return will;
	}
}

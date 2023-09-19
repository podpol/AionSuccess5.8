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
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "summon_stats_template")
public class SummonStatsTemplate extends StatsTemplate {

	@XmlAttribute(name = "pdefense")
	private int pdefense;
	@XmlAttribute(name = "mresist")
	private int mresist;
	@XmlAttribute(name = "mcrit")
	private int mcrit;

	/**
	 * @return the pdefense
	 */
	public int getPdefense() {
		return pdefense;
	}

	/**
	 * @return the mresist
	 */
	public int getMresist() {
		return mresist;
	}

	/**
	 * @return the mcrit
	 */
	public int getMcrit() {
		return mcrit;
	}

}

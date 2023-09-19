package com.aionemu.gameserver.model.templates.stats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "npc_stats_template")
public class NpcStatsTemplate extends StatsTemplate
{
	@XmlAttribute(name = "run_speed_fight")
	private float runSpeedFight;
	
	@XmlAttribute(name = "pdef")
	private int pdef;
	
	@XmlAttribute(name = "mdef")
	private int mdef;
	
	@XmlAttribute(name = "mresist")
	private int mresist;
	
	@XmlAttribute(name = "crit")
	private int crit;
	
	@XmlAttribute(name = "accuracy")
	private int accuracy;
	
	@XmlAttribute(name = "power")
	private int power;
	
	@XmlAttribute(name = "maxXp")
	private int maxXp;
	
	public float getRunSpeedFight() {
		return runSpeedFight;
	}
	
	public int getPdef() {
		return pdef;
	}
	
	public float getMdef() {
		return mdef;
	}
	
	public int getMresist() {
		return mresist;
	}
	
	public float getCrit() {
		return crit;
	}
	
	public float getAccuracy() {
		return accuracy;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getMaxXp() {
		return maxXp;
	}
}
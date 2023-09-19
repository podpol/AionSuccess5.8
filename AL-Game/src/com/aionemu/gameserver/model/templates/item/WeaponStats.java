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
package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author ATracer
 */
public class WeaponStats {

	@XmlAttribute(name = "min_damage")
	protected int minDamage;
	@XmlAttribute(name = "max_damage")
	protected int maxDamage;
	@XmlAttribute(name = "attack_speed")
	protected int attackSpeed;
	@XmlAttribute(name = "physical_critical")
	protected int physicalCritical;
	@XmlAttribute(name = "physical_accuracy")
	protected int physicalAccuracy;
	@XmlAttribute
	protected int parry;
	@XmlAttribute(name = "magical_accuracy")
	protected int magicalAccuracy;
	@XmlAttribute(name = "boost_magical_skill")
	protected int boostMagicalSkill;
	@XmlAttribute(name = "attack_range")
	protected int attackRange;
	@XmlAttribute(name = "hit_count")
	protected int hitCount;
	@XmlAttribute(name = "reduce_max")
	protected int reduceMax;

	public final int getMinDamage() {
		return minDamage;
	}

	public final int getMaxDamage() {
		return maxDamage;
	}

	public final int getMeanDamage() {
		return (minDamage + maxDamage) / 2;
	}

	public final int getAttackSpeed() {
		return attackSpeed;
	}

	public final int getPhysicalCritical() {
		return physicalCritical;
	}

	public final int getPhysicalAccuracy() {
		return physicalAccuracy;
	}

	public final int getParry() {
		return parry;
	}

	public final int getMagicalAccuracy() {
		return magicalAccuracy;
	}

	public final int getBoostMagicalSkill() {
		return boostMagicalSkill;
	}

	public final int getAttackRange() {
		return attackRange;
	}

	public final int getHitCount() {
		return hitCount;
	}

	public final int getReduceMax() {
		return reduceMax;
	}

}

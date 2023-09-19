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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "stats_template")
public abstract class StatsTemplate
{
	@XmlAttribute(name = "maxHp")
	private int maxHp;
	@XmlAttribute(name = "maxMp")
	private int maxMp;
	@XmlAttribute(name = "walk_speed")
	private float walkSpeed;
	@XmlAttribute(name = "run_speed")
	private float runSpeed;
	@XmlAttribute(name = "fly_speed")
	private float flySpeed;
	@XmlAttribute(name = "attack_speed")
	private float attackSpeed;
	@XmlAttribute(name = "evasion")
	private int evasion;
	@XmlAttribute(name = "block")
	private int block;
	@XmlAttribute(name = "parry")
	private int parry;
	@XmlAttribute(name = "main_hand_attack")
	private int mainHandAttack;
	@XmlAttribute(name = "main_hand_accuracy")
	private int mainHandAccuracy;
	@XmlAttribute(name = "main_hand_crit_rate")
	private int mainHandCritRate;
	@XmlAttribute(name = "magic_accuracy")
	private int magicAccuracy;
	@XmlAttribute(name = "crit_spell")
	private int critSpell;
	@XmlAttribute(name = "strike_resist")
	private int strikeResist;
	@XmlAttribute(name = "spell_resist")
	private int spellResist;
	
	/* ======================================= */
	public int getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	
	public int getMaxMp() {
		return maxMp;
	}
	
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	
	/* ======================================= */
	public float getWalkSpeed() {
		return walkSpeed;
	}
	
	public float getRunSpeed() {
		return runSpeed;
	}
	
	public float getFlySpeed() {
		return flySpeed;
	}
	
	public float getAttackSpeed() {
		return attackSpeed;
	}
	
	/* ======================================= */
	public int getEvasion() {
		return evasion;
	}
	
	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
	
	public int getBlock() {
		return block;
	}
	
	public void setBlock(int block) {
		this.block = block;
	}
	
	public int getParry() {
		return parry;
	}
	
	public void setParry(int parry) {
		this.parry = parry;
	}
	
	public int getStrikeResist() {
		return strikeResist;
	}
	
	public void setStrikeResist(int resist) {
		this.strikeResist = resist;
	}
	
	public int getSpellResist() {
		return spellResist;
	}
	
	public void setSpellResist(int resist) {
		this.spellResist = resist;
	}
	
	/* ======================================= */
	public int getMainHandAttack() {
		return mainHandAttack;
	}
	
	public int getMainHandAccuracy() {
		return mainHandAccuracy;
	}
	
	public int getMainHandCritRate() {
		return mainHandCritRate;
	}
	
	/* ======================================= */
	public int getMagicAccuracy() {
		return magicAccuracy;
	}
	
	public int getMCritical() {
		return critSpell;
	}
}
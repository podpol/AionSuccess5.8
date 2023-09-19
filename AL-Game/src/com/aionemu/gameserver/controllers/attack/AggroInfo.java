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
package com.aionemu.gameserver.controllers.attack;

import com.aionemu.gameserver.model.gameobjects.AionObject;

/**
 * AggroInfo: - hate of creature - damage of creature
 * 
 * @author ATracer, Sarynth
 */
public class AggroInfo {

	private AionObject attacker;
	private int hate;
	private int damage;

	/**
	 * @param attacker
	 */
	AggroInfo(AionObject attacker) {
		this.attacker = attacker;
	}

	/**
	 * @return attacker
	 */
	public AionObject getAttacker() {
		return attacker;
	}

	/**
	 * @param damage
	 */
	public void addDamage(int damage) {
		this.damage += damage;
		if (this.damage < 0)
			this.damage = 0;
	}

	/**
	 * @param damage
	 */
	public void addHate(int damage) {
		this.hate += damage;
		if (this.hate < 1)
			this.hate = 1;
	}

	/**
	 * @return hate
	 */
	public int getHate() {
		return this.hate;
	}

	/**
	 * @param hate
	 */
	public void setHate(int hate) {
		this.hate = hate;
	}

	/**
	 * @return damage
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}

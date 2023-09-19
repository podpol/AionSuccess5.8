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
package com.aionemu.gameserver.utils.stats.enums;

public enum MAIN_HAND_ATTACK
{
	WARRIOR(20),
	GLADIATOR(20),
	TEMPLAR(20),
	SCOUT(20),
	ASSASSIN(20),
	RANGER(18),
	MAGE(14),
	SORCERER(14),
	SPIRIT_MASTER(14),
	PRIEST(18),
	CLERIC(18),
	CHANTER(20),
	//News Class 4.3
	TECHNIST(18),
	GUNSLINGER(18),
	MUSE(14),
	SONGWEAVER(14),
	//News Class 4.5
	AETHERTECH(20);
	
	private int value;
	
	private MAIN_HAND_ATTACK(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
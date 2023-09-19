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

public enum POWER
{
	WARRIOR(110),
	GLADIATOR(110),
	TEMPLAR(110),
	SCOUT(100),
	ASSASSIN(110),
	RANGER(90),
	MAGE(90),
	SORCERER(90),
	SPIRIT_MASTER(90),
	PRIEST(95),
	CLERIC(105),
	CHANTER(110),

	//News Class 4.3
	TECHNIST(100), //GOOD
	GUNSLINGER(100), //GOOD
	MUSE(95), //GOOD
	SONGWEAVER(90), // 95?
	AETHERTECH(110); //115?

	private int value;
	
	private POWER(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
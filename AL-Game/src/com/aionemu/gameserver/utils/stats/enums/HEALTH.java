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

public enum HEALTH
{
	WARRIOR(110),
	GLADIATOR(115),
	TEMPLAR(100),
	SCOUT(100),
	ASSASSIN(100),
	RANGER(90),
	MAGE(90),
	SORCERER(90),
	SPIRIT_MASTER(90),
	PRIEST(95),
	CLERIC(110),
	CHANTER(105),

	//News Class 4.3
	TECHNIST(100),
	GUNSLINGER(105),
	MUSE(95),
	SONGWEAVER(100),
	//News Class 4.5
	AETHERTECH(100);
	
	private int value;
	
	private HEALTH(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
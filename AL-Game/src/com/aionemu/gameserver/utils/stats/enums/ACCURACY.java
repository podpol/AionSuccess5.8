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

public enum ACCURACY
{
	WARRIOR(100),
	GLADIATOR(100),
	TEMPLAR(100),
	SCOUT(110),
	ASSASSIN(110),
	RANGER(100),
	MAGE(95),
	SORCERER(100),
	SPIRIT_MASTER(100),
	PRIEST(100),
	CLERIC(100),
	CHANTER(90),
	//News Class 4.3
	TECHNIST(110),
	GUNSLINGER(100),
	MUSE(100),
	SONGWEAVER(100),
	//News Class 4.5
	AETHERTECH(110);
	
	private int value;
	
	private ACCURACY(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
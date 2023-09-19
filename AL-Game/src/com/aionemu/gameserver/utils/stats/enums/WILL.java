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

public enum WILL
{
	WARRIOR(90),
	GLADIATOR(90),
	TEMPLAR(105),
	SCOUT(90),
	ASSASSIN(90),
	RANGER(110),
	MAGE(115),
	SORCERER(110),
	SPIRIT_MASTER(115),
	PRIEST(110),
	CLERIC(110),
	CHANTER(110),
	//News Class 4.3
	TECHNIST(90),
	GUNSLINGER(100),
	MUSE(110),
	SONGWEAVER(110),
	//News Class 4.5
	AETHERTECH(90);
	
	private int value;
	
	private WILL(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
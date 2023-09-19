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

public enum SPEED
{
	WARRIOR(6),
	GLADIATOR(6),
	TEMPLAR(6),
	SCOUT(6),
	ASSASSIN(6),
	RANGER(6),
	MAGE(6),
	SORCERER(6),
	SPIRIT_MASTER(6),
	PRIEST(6),
	CLERIC(6),
	CHANTER(6),
	//News Class 4.3
	TECHNIST(6),
	GUNSLINGER(6),
	MUSE(6),
	SONGWEAVER(6),
	//News Class 4.5
	AETHERTECH(6);
	
	private int value;
	
	private SPEED(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
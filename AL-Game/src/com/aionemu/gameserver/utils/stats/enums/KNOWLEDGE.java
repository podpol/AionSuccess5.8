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

public enum KNOWLEDGE
{
	WARRIOR(90),
	GLADIATOR(90),
	TEMPLAR(90),
	SCOUT(90),
	ASSASSIN(90),
	RANGER(120),
	MAGE(115),
	SORCERER(120),
	SPIRIT_MASTER(115),
	PRIEST(100),
	CLERIC(105),
	CHANTER(105),
	//News Class 4.3
	TECHNIST(100),
	GUNSLINGER(100),
	MUSE(115),
	SONGWEAVER(110),
	//News Class 4.5
	AETHERTECH(90);
	
	private int value;
	
	private KNOWLEDGE(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
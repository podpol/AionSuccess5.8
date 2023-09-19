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

public enum MAXHP
{
	WARRIOR(1.1688f, 1.1688f, 284),
	GLADIATOR(1.3393f, 48.246f, 342),
	TEMPLAR(1.3288f, 51.878f, 281),
	SCOUT(1.0297f, 40.823f, 219),
	ASSASSIN(1.0488f, 40.38f, 222),
	RANGER(0.5f, 38.5f, 133),
	MAGE(0.7554f, 29.457f, 132),
	SORCERER(0.6352f, 24.852f, 112),
	SPIRIT_MASTER(1, 20.6f, 157),
	PRIEST(1.0303f, 40.824f, 201),
	CLERIC(0.9277f, 35.988f, 229),
	CHANTER(0.9277f, 35.988f, 229),
	//News Class 4.3
	TECHNIST(1.0297f, 40.823f, 219),
	GUNSLINGER(1.0488f, 40.38f, 222),
	MUSE(0.7554f, 29.457f, 132),
	SONGWEAVER(1, 20.6f, 157),
	//News Class 4.5
	AETHERTECH(0.9277f, 35.988f, 229);
	
	private float a;
	private float b;
	private float c;
	
	private MAXHP(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public int getMaxHpFor(int level) {
		return Math.round(a * (level - 1) * (level - 1) + b * (level - 1) + c);
	}
}
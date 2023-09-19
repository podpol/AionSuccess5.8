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
package com.aionemu.gameserver.model.team.legion;

/**
 * @author Simple
 */
public enum LegionHistoryType {
	CREATE(0), // No parameters
	JOIN(1), // Parameter: name
	KICK(2), // Parameter: name
	LEVEL_UP(3), // Parameter: legion level
	APPOINTED(4), // Parameter: legion level
	EMBLEM_REGISTER(5), // No parameters
	EMBLEM_MODIFIED(6), // No parameters
	ITEM_DEPOSIT(15), // Parameter: name
	ITEM_WITHDRAW(16), // Parameter: name
	KINAH_DEPOSIT(17), // Parameter: name
	KINAH_WITHDRAW(18); // Parameter: name

	private byte historyType;

	private LegionHistoryType(int historyType) {
		this.historyType = (byte) historyType;
	}

	/**
	 * Returns client-side id for this
	 * 
	 * @return byte
	 */
	public byte getHistoryId() {
		return this.historyType;
	}
}

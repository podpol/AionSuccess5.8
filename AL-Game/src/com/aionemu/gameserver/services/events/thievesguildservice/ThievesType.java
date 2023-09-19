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
package com.aionemu.gameserver.services.events.thievesguildservice;

/**
 * @author Ghostfur (Aion-Unique)
 */
public enum ThievesType {
	
	NONE(0), // Нет
	BRONZE(1),
	SILVER(2),
	GOLD(3),
	PLATINUM(4),
	MITHRIL(5),
	SERAMIUM(6);

	private int id;

	private ThievesType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public static ThievesType getThievesType(int id) {
		for (ThievesType type : values()) {
			if (id == type.getId()) {
				return type;
			}
		}
		return ThievesType.NONE;
	}
	
}
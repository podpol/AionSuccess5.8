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
package com.aionemu.gameserver.model.templates.housing;

/**
 * @author Rolandas
 */
public enum HouseType {
	ESTATE(0, 3, "a"),
	MANSION(1, 2, "b"),
	HOUSE(2, 1, "c"),
	STUDIO(3, 0, "d"),
	PALACE(4, 4, "s");

	private HouseType(int index, int id, String abbrev) {
		this.abbrev = abbrev;
		this.limitTypeIndex = index;
		this.id = id;
	}

	private String abbrev;
	private int limitTypeIndex;
	private int id; 

	public String getAbbreviation() {
		return abbrev;
	}
	
	public int getLimitTypeIndex() {
		return limitTypeIndex;
	}
	
	public int getId() {
		return id;
	}

	public String value() {
		return name();
	}

	public static HouseType fromValue(String value) {
		return valueOf(value);
	}
}

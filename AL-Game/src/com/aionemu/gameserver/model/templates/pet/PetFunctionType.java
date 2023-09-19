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
package com.aionemu.gameserver.model.templates.pet;

/**
 * @author Rinzler
 * Formula: dataBitCount*2^5 OR id
 */
public enum PetFunctionType
{
	WAREHOUSE(0, true),
	FOOD(1, 64),
	DOPING(2, 256),
	LOOT(3, 8),
	APPEARANCE(1),
	NONE(4, true),
	CHEER(5), //need test
	MERCHAND(6), //need test
	BAG(-1),
	WING(-2);
	
	private short id;
	private boolean isPlayerFunc = false;
	
	PetFunctionType(int id, boolean isPlayerFunc) {
		this(id);
		this.isPlayerFunc = isPlayerFunc;
	}
	
	PetFunctionType(int id, int dataBitCount) {
        this(dataBitCount << 5 | id);
		this.isPlayerFunc = true;
	}
	
	PetFunctionType(int id) {
		this.id = (short) (id & 0xFFFF);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isPlayerFunction() {
		return isPlayerFunc;
	}
}
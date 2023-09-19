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
package com.aionemu.gameserver.model.autogroup;

public enum EntryRequestType
{
	NEW_GROUP_ENTRY((byte) 0),
	FAST_GROUP_ENTRY((byte) 1),
	GROUP_ENTRY((byte) 2),
	SPECIAL_PURPOSE((byte) 3);
	
	private byte id;
	
	private EntryRequestType(byte id) {
		this.id = id;
	}
	
	public byte getId() {
		return id;
	}
	
	public boolean isNewGroupEntry() {
		return id == 0;
	}
	
	public boolean isFastGroupEntry() {
		return id == 1;
	}
	
	public boolean isGroupEntry() {
		return id == 2;
	}
	
	public boolean isSpecialPurpose() {
		return id == 3;
	}
	
	public static EntryRequestType getTypeById(byte id) {
		for (EntryRequestType ert : values()) {
			if (ert.getId() == id) {
				return ert;
			}
		}
		return null;
	}
}
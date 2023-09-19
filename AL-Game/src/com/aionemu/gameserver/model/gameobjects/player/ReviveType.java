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

package com.aionemu.gameserver.model.gameobjects.player;

public enum ReviveType
{
	BIND_REVIVE(0),
	REBIRTH_REVIVE(1),
	ITEM_SELF_REVIVE(2),
	SKILL_REVIVE(3),
	KISK_REVIVE(4),
	INSTANCE_REVIVE(6),
	VORTEX_REVIVE(8),
	START_POINT_REVIVE(11);
	
	private int typeId;
	
	private ReviveType(int typeId) {
		this.typeId = typeId;
	}
	
	public int getReviveTypeId() {
		return typeId;
	}
	
	public static ReviveType getReviveTypeById(int id, Player pl) {
		for (ReviveType rt : values()) {
			if (rt.typeId == id) {
				return rt;
			}
		}
		throw new IllegalArgumentException("Unsupported revive type: " + id);
	}
}
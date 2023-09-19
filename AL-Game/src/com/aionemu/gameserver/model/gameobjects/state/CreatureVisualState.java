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
package com.aionemu.gameserver.model.gameobjects.state;

/**
 * @author Sweetkr
 */
public enum CreatureVisualState {
	VISIBLE(0), // Normal
	HIDE1(1), // Hide I
	HIDE2(2), // Hide II
	HIDE3(3), // Hide by Artifact?
	HIDE5(5), //No idea :D
	HIDE10(10), // Hide from Npc?
	HIDE13(13), // Hide from Npc?
	HIDE20(20), // Hide from Npc?
	BLINKING(64); // Blinking when entering to zone

	private int id;

	private CreatureVisualState(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}

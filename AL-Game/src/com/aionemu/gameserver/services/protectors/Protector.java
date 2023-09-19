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
package com.aionemu.gameserver.services.protectors;

import com.aionemu.gameserver.model.gameobjects.player.Player;

public class Protector
{
	public int victims;
	private Player owner;
	private int guardType;
	private int guardRank;
	
	public Protector(Player owner) {
		this.owner = owner;
	}
	
	public void refreshOwner(Player player) {
		owner = player;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setRank(int rank) {
		guardRank = rank;
	}
	
	public int getRank() {
		return guardRank;
	}
	
	public void setType(int type) {
		guardType = type;
	}
	
	public int getType() {
		return guardType;
	}
}
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
package com.aionemu.gameserver.model;

import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author synchro2
 */

public class Wedding {

	private Player player;
	private Player partner;
	private Player priest;
	private boolean accepted;

	public Wedding(Player player, Player partner, Player priest) {
		super();
		this.player = player;
		this.partner = partner;
		this.priest = priest;
	}

	public void setAccept() {
		this.accepted = true;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Player getPartner() {
		return this.partner;
	}

	public Player getPriest() {
		return this.priest;
	}

	public boolean isAccepted() {
		return this.accepted;
	}

}
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

import com.aionemu.gameserver.model.PlayerClass;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;

public class AGPlayer
{
	private Integer objectId;
	private Race race;
	private PlayerClass playerClass;
	private String name;
	private boolean isInInstance;
	private boolean isOnline;
	private boolean isPressEnter;
	
	public AGPlayer(Player player) {
		objectId = player.getObjectId();
		race = player.getRace();
		playerClass = player.getPlayerClass();
		name = player.getName();
		isOnline = true;
	}
	
	public Integer getObjectId() {
		return objectId;
	}
	
	public Race getRace() {
		return race;
	}
	
	public String getName() {
		return name;
	}
	
	public PlayerClass getPlayerClass() {
		return playerClass;
	}
	
	public void setInInstance(boolean result) {
		isInInstance = result;
	}
	
	public boolean isInInstance() {
		return isInInstance;
	}
	
	public boolean isOnline() {
		return isOnline;
	}
	
	public void setOnline(boolean result) {
		isOnline = result;
	}
	
	public boolean isPressedEnter() {
		return isPressEnter;
	}
	
	public void setPressEnter(boolean result) {
		isPressEnter = result;
	}
}
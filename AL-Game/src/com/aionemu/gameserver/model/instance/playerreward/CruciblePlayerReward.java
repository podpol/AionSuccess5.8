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
package com.aionemu.gameserver.model.instance.playerreward;

/****/
/** Author Rinzler (Encom)
/****/

public class CruciblePlayerReward extends InstancePlayerReward
{
	private int insignia;
	private int spawnPosition;
	private boolean isRewarded = false;
	private boolean isPlayerLeave = false;
	private boolean isPlayerDefeated = false;
	
	public CruciblePlayerReward(Integer object) {
		super(object);
	}
	
	public boolean isRewarded() {
		return isRewarded;
	}
	
	public void setRewarded() {
		isRewarded = true;
	}
	
	public void setInsignia(int insignia) {
		this.insignia = insignia;
	}
	
	public int getInsignia() {
		return insignia;
	}
	
	public void setSpawnPosition(int spawnPosition) {
		this.spawnPosition = spawnPosition;
	}
	
	public int getSpawnPosition() {
		return spawnPosition;
	}
	
	public boolean isPlayerLeave() {
		return isPlayerLeave;
	}
	
	public void setPlayerLeave() {
		isPlayerLeave = true;
	}
	
	public void setPlayerDefeated(boolean value) {
		isPlayerDefeated = value;
	}
	
	public boolean isPlayerDefeated() {
		return isPlayerDefeated;
	}
}
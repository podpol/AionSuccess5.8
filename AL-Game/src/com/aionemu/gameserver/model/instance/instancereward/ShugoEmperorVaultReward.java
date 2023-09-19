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
package com.aionemu.gameserver.model.instance.instancereward;

import com.aionemu.gameserver.model.instance.playerreward.ShugoEmperorVaultPlayerReward;

/****/
/** Author Rinzler (Encom)
/****/

public class ShugoEmperorVaultReward extends InstanceReward<ShugoEmperorVaultPlayerReward>
{
	private int points;
	private int npcKills;
	private int rank = 7;
	
	public ShugoEmperorVaultReward(Integer mapId, int instanceId) {
		super(mapId, instanceId);
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addNpcKill() {
		npcKills++;
	}
	
	public int getNpcKills() {
		return npcKills;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
}
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

import com.aionemu.gameserver.model.autogroup.AGPlayer;
import com.aionemu.gameserver.utils.idfactory.IDFactory;

import java.util.List;

public class HarmonyGroupReward extends PvPArenaPlayerReward
{
	private int id;
	private List<AGPlayer> players;
	
	public HarmonyGroupReward(Integer object, int timeBonus, byte buffId, List<AGPlayer> players) {
		super(object, timeBonus, buffId);
		this.players = players;
		id = IDFactory.getInstance().nextId();
	}
	
	public List<AGPlayer> getAGPlayers() {
		return players;
	}
	
	public boolean containPlayer(Integer object) {
		for (AGPlayer agp : players) {
			if (agp.getObjectId().equals(object)) {
				return true;
			}
		}
		return false;
	}
	
	public AGPlayer getAGPlayer(Integer object) {
		for (AGPlayer agp : players) {
			if (agp.getObjectId().equals(object)) {
				return agp;
			}
		}
		return null;
	}
	
	public int getId() {
        return id;
    }
}
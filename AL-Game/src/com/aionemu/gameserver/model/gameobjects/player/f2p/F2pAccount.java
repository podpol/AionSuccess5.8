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
package com.aionemu.gameserver.model.gameobjects.player.f2p;

import com.aionemu.gameserver.model.IExpirable;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ranastic (Encom)
/****/

public class F2pAccount implements IExpirable
{
	private int deleteTime = 0;
	private boolean active = false;
	
	public F2pAccount(int deletionTime) {
		deleteTime = deletionTime;
	}
	
	public int getRemainingTime() {
		if (deleteTime == 0) {
			return 0;
		}
		return deleteTime - (int)(System.currentTimeMillis() / 1000L);
	}
	
	public int getExpireTime() {
		return deleteTime;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void expireEnd(Player player) {
		setActive(false);
		player.getF2p().remove();
		PacketSendUtility.sendBrightYellowMessageOnCenter(player, "<F2p Pack> is expired!!!");
	}
	
	public boolean canExpireNow() {
		return true;
	}
	
	public void expireMessage(Player player, int time) {
		PacketSendUtility.sendBrightYellowMessageOnCenter(player, "<F2p Pack> end!!!");
	}
}
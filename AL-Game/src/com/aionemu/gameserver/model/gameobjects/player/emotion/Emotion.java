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
package com.aionemu.gameserver.model.gameobjects.player.emotion;

import com.aionemu.gameserver.model.IExpirable;
import com.aionemu.gameserver.model.gameobjects.player.Player;


/**
 * @author MrPoke
 *
 */
public class Emotion implements IExpirable{
	private int id;
	private int dispearTime;

	/**
	 * @param id
	 * @param dispearTime
	 */
	public Emotion(int id, int dispearTime) {
		this.id = id;
		this.dispearTime = dispearTime;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public int getRemainingTime(){
		if (dispearTime == 0)
			return 0;
		return dispearTime-(int)(System.currentTimeMillis()/1000);
	}

	@Override
	public int getExpireTime() {
		return dispearTime;
	}

	@Override
	public void expireEnd(Player player) {
		player.getEmotions().remove(id);
		
	}

	@Override
	public void expireMessage(Player player, int time) {
	}

	@Override
	public boolean canExpireNow() {
		return true;
	}
}

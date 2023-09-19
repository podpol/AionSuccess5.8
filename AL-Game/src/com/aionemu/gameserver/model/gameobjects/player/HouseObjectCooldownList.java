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

import javolution.util.FastMap;

/**
 * @author Rolandas
 */
public class HouseObjectCooldownList {

	private FastMap<Integer, Long> houseObjectCooldowns;

	HouseObjectCooldownList(Player owner) {
	}

	public boolean isCanUseObject(int objectId) {
		if (houseObjectCooldowns == null || !houseObjectCooldowns.containsKey(objectId))
			return true;

		Long coolDown = houseObjectCooldowns.get(objectId);
		if (coolDown == null)
			return true;

		if (coolDown < System.currentTimeMillis()) {
			houseObjectCooldowns.remove(objectId);
			return true;
		}

		return false;
	}

	public long getHouseObjectCooldown(int objectId) {
		if (houseObjectCooldowns == null || !houseObjectCooldowns.containsKey(objectId))
			return 0;

		return houseObjectCooldowns.get(objectId);
	}

	public FastMap<Integer, Long> getHouseObjectCooldowns() {
		return houseObjectCooldowns;
	}

	public void setHouseObjectCooldowns(FastMap<Integer, Long> houseObjectCooldowns) {
		this.houseObjectCooldowns = houseObjectCooldowns;
	}

	public void addHouseObjectCooldown(int objectId, int delay) {
		if (houseObjectCooldowns == null) {
			houseObjectCooldowns = new FastMap<Integer, Long>();
		}

		long nextUseTime = System.currentTimeMillis() + (delay * 1000);
		houseObjectCooldowns.put(objectId, nextUseTime);
	}
	
	public int getReuseDelay(int objectId) {
		if (isCanUseObject(objectId))
			return 0;
		long cd = getHouseObjectCooldown(objectId);
		int delay = (int) ((cd - System.currentTimeMillis()) / 1000);
		return delay;
	}
}

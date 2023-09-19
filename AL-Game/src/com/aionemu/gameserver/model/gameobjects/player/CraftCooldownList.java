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
 * @author synchro2
 */
public class CraftCooldownList {

	private FastMap<Integer, Long> craftCooldowns;

	CraftCooldownList(Player owner) {
	}

	public boolean isCanCraft(int delayId) {
		if (craftCooldowns == null || !craftCooldowns.containsKey(delayId))
			return true;

		Long coolDown = craftCooldowns.get(delayId);
		if (coolDown == null)
			return true;

		if (coolDown < System.currentTimeMillis()) {
			craftCooldowns.remove(delayId);
			return true;
		}

		return false;
	}

	public long getCraftCooldown(int delayId) {
		if (craftCooldowns == null || !craftCooldowns.containsKey(delayId))
			return 0;

		return craftCooldowns.get(delayId);
	}

	public FastMap<Integer, Long> getCraftCoolDowns() {
		return craftCooldowns;
	}

	public void setCraftCoolDowns(FastMap<Integer, Long> craftCoolDowns) {
		this.craftCooldowns = craftCoolDowns;
	}

	public void addCraftCooldown(int delayId, int delay) {
		if (craftCooldowns == null) {
			craftCooldowns = new FastMap<Integer, Long>();
		}

		long nextUseTime = System.currentTimeMillis() + (delay * 1000);
		craftCooldowns.put(delayId, nextUseTime);
	}
}
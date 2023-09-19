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
package com.aionemu.gameserver.model.items;

/**
 * @author ATracer
 */
public class ItemCooldown {

	/**
	 * time of next reuse
	 */
	private long time;
	/**
	 * Use delay in ms
	 */
	private int useDelay;

	/**
	 * @param time
	 * @param useDelay
	 */
	public ItemCooldown(long time, int useDelay) {
		this.time = time;
		this.useDelay = useDelay;
	}

	/**
	 * @return the time
	 */
	public long getReuseTime() {
		return time;
	}

	/**
	 * @return the useDelay
	 */
	public int getUseDelay() {
		return useDelay;
	}
}

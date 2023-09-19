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

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * @author evilset
 */
public class BindPointPosition {

	private int mapId;
	private float x;
	private float y;
	private float z;
	private byte heading;
	private PersistentState persistentState;

	/**
	 * @param mapId
	 * @param x
	 * @param y
	 * @param z
	 * @param heading
	 */
	public BindPointPosition(int mapId, float x, float y, float z, byte heading) {
		this.mapId = mapId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.heading = heading;
		this.persistentState = PersistentState.NEW;
	}

	/**
	 * @return Returns the mapId.
	 */
	public int getMapId() {
		return mapId;
	}

	/**
	 * @return Returns the x.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return Returns the y.
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return Returns the z.
	 */
	public float getZ() {
		return z;
	}

	/**
	 * @return Returns the heading.
	 */
	public byte getHeading() {
		return heading;
	}

	/**
	 * @return the persistentState
	 */
	public PersistentState getPersistentState() {
		return persistentState;
	}

	/**
	 * @param persistentState
	 *          the persistentState to set
	 */
	public void setPersistentState(PersistentState persistentState) {
		switch (persistentState) {
			case UPDATE_REQUIRED:
				if (this.persistentState == PersistentState.NEW)
					break;
			default:
				this.persistentState = persistentState;
		}
	}
}

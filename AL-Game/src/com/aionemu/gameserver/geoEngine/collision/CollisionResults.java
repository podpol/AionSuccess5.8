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
package com.aionemu.gameserver.geoEngine.collision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CollisionResults implements Iterable<CollisionResult> {

	private final ArrayList<CollisionResult> results = new ArrayList<CollisionResult>();
	private boolean sorted = true;
	private final boolean onlyFirst;
	private final byte intentions;
	private final int instanceId;

	public CollisionResults(byte intentions, boolean searchFirst, int instanceId) {
		this.intentions = intentions;
		this.onlyFirst = searchFirst;
		this.instanceId = instanceId;
	}

	public void clear() {
		results.clear();
	}

	@Override
	public Iterator<CollisionResult> iterator() {
		if (!sorted) {
			Collections.sort(results);
			sorted = true;
		}

		return results.iterator();
	}

	public void addCollision(CollisionResult result) {
		if (Float.isNaN(result.getDistance())) {
			return;
		}
		results.add(result);
		if (!onlyFirst) {
			sorted = false;
		}
	}

	public int size() {
		return results.size();
	}

	public CollisionResult getClosestCollision() {
		if (size() == 0) {
			return null;
		}

		if (!sorted) {
			Collections.sort(results);
			sorted = true;
		}

		return results.get(0);
	}

	public CollisionResult getFarthestCollision() {
		if (size() == 0) {
			return null;
		}

		if (!sorted) {
			Collections.sort(results);
			sorted = true;
		}

		return results.get(size() - 1);
	}

	public CollisionResult getCollision(int index) {
		if (!sorted) {
			Collections.sort(results);
			sorted = true;
		}

		return results.get(index);
	}

	/**
	 * Internal use only.
	 *
	 * @param index
	 * @return
	 */
	public CollisionResult getCollisionDirect(int index) {
		return results.get(index);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CollisionResults[");
		for (CollisionResult result : results) {
			sb.append(result).append(", ");
		}
		if (results.size() > 0) {
			sb.setLength(sb.length() - 2);
		}

		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return Returns the onlyFirst.
	 */
	public boolean isOnlyFirst() {
		return onlyFirst;
	}

	/**
	 * @return the intention
	 */
	public byte getIntentions() {
		return intentions;
	}

	public int getInstanceId() {
		return instanceId;
	}
}

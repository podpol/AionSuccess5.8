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
package com.aionemu.gameserver.geoEngine.collision.bih;

import java.util.Comparator;

import com.aionemu.gameserver.geoEngine.math.Vector3f;

public class TriangleAxisComparator implements Comparator<BIHTriangle> {

	private final int axis;

	public TriangleAxisComparator(int axis) {
		this.axis = axis;
	}

	@Override
	public int compare(BIHTriangle o1, BIHTriangle o2) {
		float v1, v2;
		Vector3f c1 = o1.getCenter();
		Vector3f c2 = o2.getCenter();
		switch (axis) {
			case 0:
				v1 = c1.x;
				v2 = c2.x;
				break;
			case 1:
				v1 = c1.y;
				v2 = c2.y;
				break;
			case 2:
				v1 = c1.z;
				v2 = c2.z;
				break;
			default:
				assert false;
				return 0;
		}
		if (v1 > v2) {
			return 1;
		}
		else if (v1 < v2) {
			return -1;
		}
		else {
			return 0;
		}
	}
}

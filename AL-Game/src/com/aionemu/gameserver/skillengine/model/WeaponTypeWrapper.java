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
package com.aionemu.gameserver.skillengine.model;

import com.aionemu.gameserver.model.templates.item.WeaponType;
import com.aionemu.gameserver.services.MotionLoggingService;

/**
 * @author kecimis
 */
public class WeaponTypeWrapper implements Comparable<WeaponTypeWrapper> {

	private WeaponType mainHand = null;
	private WeaponType offHand = null;

	public WeaponTypeWrapper(WeaponType mainHand, WeaponType offHand) {
		if (mainHand != null && offHand != null) {
			this.mainHand = WeaponType.SWORD_1H;
			this.offHand = WeaponType.SWORD_1H;
		}
		else {
			this.mainHand = mainHand;
			this.offHand = offHand;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeaponTypeWrapper other = (WeaponTypeWrapper) obj;
		if (!getOuterType().equals(other.getOuterType()))
			return false;
		if (mainHand != other.mainHand)
			return false;
		if (offHand != other.offHand)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "mainHandType=\"" + (mainHand != null ? mainHand.toString() : "null") + "\"" + " offHandType=\"" + (offHand != null ? offHand.toString() : "null");
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getOuterType().hashCode();
		result = prime * result + ((mainHand == null) ? 0 : mainHand.hashCode());
		result = prime * result + ((offHand == null) ? 0 : offHand.hashCode());
		return result;
	}

	@Override
	public int compareTo(WeaponTypeWrapper o) {
		if (mainHand == null || o.getMainHand() == null)
			return 0;
		else if (offHand != null && o.getOffHand() != null)
			return 0;
		else if (offHand != null && o.getOffHand() == null)
			return 1;
		else if (offHand == null && o.getOffHand() != null)
			return -1;
		else
			return mainHand.toString().compareTo(o.getMainHand().toString());
	}

	public WeaponType getMainHand() {
		return this.mainHand;
	}

	public WeaponType getOffHand() {
		return this.offHand;
	}

	private MotionLoggingService getOuterType() {
		return MotionLoggingService.getInstance();
	}
}

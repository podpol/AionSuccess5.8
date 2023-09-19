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
package com.aionemu.gameserver.model.templates.housing;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "LimitType")
@XmlEnum
public enum LimitType
{
	NONE(0, new int[] {0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0}),
	OWNER_POT(1, new int[] {8, 6, 4, 3, 8}, new int[] {0, 0, 0, 0, 4}),
	VISITOR_POT(2, new int[] {9, 7, 5, 2, 8}, new int[] {0, 0, 0, 0, 4}),
	STORAGE(3, new int[] {7, 6, 5, 4, 8}, new int[] {0, 0, 0, 0, 4}),
	POT(4, new int[] {7, 6, 5, 4, 3}, new int[] {7, 6, 5, 4, 1}),
	COOKING(5, new int[] {2, 2, 2, 2, 2}, new int[] {2, 2, 2, 2, 2}),
	PICTURE(6, new int[] {1, 1, 1, 1, 1}, new int[] {1, 1, 1, 1, 0}),
	JUKEBOX(7, new int[] {1, 1, 1, 1, 1}, new int[] {1, 1, 1, 1, 0});
	
	int id;
	int[] personalLimits;
	int[] trialLimits;
	
	private LimitType(int id, int[] maxPersonalLimits, int[] maxTrialLimits) {
		this.id = id;
		this.personalLimits = maxPersonalLimits;
		this.trialLimits = maxTrialLimits;
	}
	
	public String value() {
		return name();
	}
	
	public int getId() {
		return id;
	}
	
	public int getObjectPlaceLimit(HouseType houseType) {
		return personalLimits[houseType.getLimitTypeIndex()];
	}
	
	public int getTrialObjectPlaceLimit(HouseType houseType) {
		return trialLimits[houseType.getLimitTypeIndex()];
	}
	
	public static LimitType fromValue(String value) {
		return valueOf(value);
	}
}
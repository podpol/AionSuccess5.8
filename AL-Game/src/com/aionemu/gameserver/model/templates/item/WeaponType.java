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
package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rinzler (Encom)
 */
@XmlType(name = "weapon_type")
@XmlEnum
public enum WeaponType

{
	//Weapon Type 4.8
	DAGGER_1H(new int[] {66, 45}, 1),
	MACE_1H(new int[] {39, 46}, 1),
	SWORD_1H(new int[] {37, 44}, 1),
	TOOLHOE_1H(new int[] {}, 1),
	BOOK_2H(new int[] {100}, 2),
	ORB_2H(new int[] {111}, 2),
	POLEARM_2H(new int[] {52}, 2),
	STAFF_2H(new int[] {89}, 2),
	SWORD_2H(new int[] {51}, 2),
	TOOLPICK_2H(new int[] {}, 2),
	TOOLROD_2H(new int[] {}, 2),
	BOW(new int[] {53}, 2),
	GUN_1H(new int[] {117, 112}, 1),
	CANNON_2H(new int[] {113}, 2),
	HARP_2H(new int[] {124, 114}, 2),
	KEYBLADE_2H(new int[] {115}, 2),
	KEYHAMMER_2H(new int[] {}, 2);
	
	private int slots;
	private int[] requiredSkill;
	
	private WeaponType(int[] requiredSkills, int slots) {
		this.requiredSkill = requiredSkills;
		this.slots = slots;
	}
	
	public int[] getRequiredSkills() {
		return requiredSkill;
	}
	
	public int getRequiredSlots() {
		return slots;
	}
	
	public int getMask() {
		return 1 << this.ordinal();
	}
}
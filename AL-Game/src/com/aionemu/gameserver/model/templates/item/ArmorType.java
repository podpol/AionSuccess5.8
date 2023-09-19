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

@XmlType(name = "armor_type")
@XmlEnum
public enum ArmorType
{
	//Armor Type 4.8
	NO_ARMOR(new int[] {}),
	CHAIN(new int[] {42, 49}),
	CLOTHES(new int[] {40}),
	LEATHER(new int[] {41, 48}),
	PLATE(new int[] {54}),
	ROBE(new int[] {103, 106}),
	SHARD(new int[] {}),
	SHIELD(new int[] {43, 50}),
	WING(new int[] {}),
	PLUME(new int[] {}),
	BRACELET(new int[] {});
	
	private int[] requiredSkills;
	
	private ArmorType(int[] requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	
	public int[] getRequiredSkills() {
		return requiredSkills;
	}
	
	public int getMask() {
		return 1 << this.ordinal();
	}
}
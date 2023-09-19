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
package com.aionemu.gameserver.model.templates.pet;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FoodType")
@XmlEnum
public enum FoodType
{
	AETHER_CRYSTAL_BISCUIT,
    AETHER_GEM_BISCUIT,
    AETHER_POWDER_BISCUIT,
    ARMOR,
    BALAUR_SCALES,
    BONES,
    EXCLUDES,
    FLUIDS,
	HIGH_CRAFT_STEP,
    HEALTHY_FOOD_ALL,
    HEALTHY_FOOD_SPICY,
	INFERNAL_DIABOL_AP,
	INNOCENT_MEREK_XP,
    MISCELLANEOUS,
	NEW_YEAR_PET_FOOD,
    POPPY_SNACK,
    POPPY_SNACK_TASTY,
    POPPY_SNACK_NUTRITIOUS,
	SHUGO_COIN,
    SOULS,
    STINKY,
    THORNS;
	
	public String value() {
		return name();
	}
	
	public static FoodType fromValue(String value) {
		return valueOf(value);
	}
}
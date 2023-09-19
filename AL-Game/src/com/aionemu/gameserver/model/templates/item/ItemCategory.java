package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item_category")
@XmlEnum
public enum ItemCategory
{
	MANASTONE,
	SPECIAL_MANASTONE,
	PRIMARY_MANASTONE,
	GODSTONE,
	ENCHANTMENT,
	ENCHANTMENT_STIGMA,
	ENCHANTMENT_AMPLIFICATION,
	FLUX,
	BALIC_EMOTION,
	BALIC_MATERIAL,
	RAWHIDE,
	SOULSTONE,
	RECIPE,
	GATHERABLE,
	GATHERABLE_BONUS,
	SWORD,
	DAGGER,
	MACE,
	ORB,
	SPELLBOOK,
	GREATSWORD,
	POLEARM,
	STAFF,
	BOW,
	SHIELD,
	JACKET,
	PANTS,
	SHOES,
	GLOVES,
	SHOULDERS,
	NECKLACE,
	EARRINGS,
	RINGS,
	HELMET,
	BELT,
	SKILLBOOK,
	STIGMA,
	COINS,
	MEDALS,
	QUEST,
	KEY,
	TEMPERING,
	CRAFT_BOOST,
    COMBINATION,
	
	//4.0
	GUN,
	CANNON,
	HARP,
	KEYBLADE,
	KEYHAMMER,
	PLUME,
    NONE,
	
	//5.1
	ESTIMA,
	BRACELET
}
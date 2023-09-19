package com.aionemu.gameserver.configs.main;

import com.aionemu.commons.configuration.Property;

public class EnchantsConfig
{
	@Property(key = "gameserver.socket.manastone", defaultValue = "50")
	public static float SOCKET_MANASTONE;
	@Property(key = "gameserver.enchant.item", defaultValue = "50")
	public static float ENCHANT_ITEM;
	@Property(key = "gameserver.enchant.plume", defaultValue = "50")
	public static float ENCHANT_PLUME;
	@Property(key = "gameserver.enchant.bracelet", defaultValue = "50")
	public static float ENCHANT_BRACELET;
	@Property(key = "gameserver.enchant.accessory", defaultValue = "50")
	public static float ENCHANT_ACCESSORY;
	@Property(key = "gameserver.enchant.stigma", defaultValue = "50")
	public static float ENCHANT_STIGMA;
	@Property(key = "gameserver.manastone.clean", defaultValue = "false")
	public static boolean CLEAN_STONE;
	@Property(key = "gameserver.enchant.cast.speed", defaultValue = "4000")
	public static int ENCHANT_SPEED;
	@Property(key = "gameserver.enchant.item.broke", defaultValue = "true")
	public static boolean ENABLE_ARCHDAEVA_ITEM_BROKE;

}
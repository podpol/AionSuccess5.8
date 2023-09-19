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
package com.aionemu.gameserver.configs.main;

import com.aionemu.commons.configuration.Property;

/**
 * @author synchro2
 */
public class WeddingsConfig {

	@Property(key = "gameserver.weddings.enable", defaultValue = "false")
	public static boolean WEDDINGS_ENABLE;

	@Property(key = "gameserver.weddings.gift.enable", defaultValue = "false")
	public static boolean WEDDINGS_GIFT_ENABLE;

	@Property(key = "gameserver.weddings.gift", defaultValue = "0")
	public static int WEDDINGS_GIFT;

	@Property(key = "gameserver.weddings.suit.enable", defaultValue = "false")
	public static boolean WEDDINGS_SUIT_ENABLE;

	@Property(key = "gameserver.weddings.suit", defaultValue = "")
	public static String WEDDINGS_SUITS;

	@Property(key = "gameserver.weddings.membership", defaultValue = "0")
	public static byte WEDDINGS_MEMBERSHIP;

	@Property(key = "gameserver.weddings.command.membership", defaultValue = "0")
	public static byte WEDDINGS_COMMAND_MEMBERSHIP;

	@Property(key = "gameserver.weddings.same_sex", defaultValue = "false")
	public static boolean WEDDINGS_SAME_SEX;

	@Property(key = "gameserver.weddings.races", defaultValue = "false")
	public static boolean WEDDINGS_DIFF_RACES;

	@Property(key = "gameserver.weddings.kinah", defaultValue = "0")
	public static int WEDDINGS_KINAH;

	@Property(key = "gameserver.weddings.announce", defaultValue = "true")
	public static boolean WEDDINGS_ANNOUNCE;

	@Property(key = "gameserver.weddings.tag", defaultValue = "\uE020 %s")
	public static String TAG_WEDDING;
}
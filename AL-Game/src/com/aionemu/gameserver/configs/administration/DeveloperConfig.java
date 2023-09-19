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
package com.aionemu.gameserver.configs.administration;

import com.aionemu.commons.configuration.Property;

public class DeveloperConfig
{
	/**
	 * if false - not spawns will be loaded
	 */
	@Property(key = "gameserver.developer.spawn.enable", defaultValue = "true")
	public static boolean SPAWN_ENABLE;
	/**
	 * if true - checks spawns being outside any known zones
	 */
	@Property(key = "gameserver.developer.spawn.check", defaultValue = "false")
	public static boolean SPAWN_CHECK;
	/**
	 * if set, adds specified stat bonus for items with random bonusess
	 */
	@Property(key = "gameserver.developer.itemstat.id", defaultValue = "0")
	public static int ITEM_STAT_ID;
	/**
	 * Show sended cm/sm packets in game server log
	 */
	@Property(key = "gameserver.developer.showpackets.enable", defaultValue = "false")
	public static boolean SHOW_PACKETS;
	/**
	 * Display Packets Name in Chat Window
	 */
	@Property(key = "gameserver.developer.show.packetnames.inchat.enable", defaultValue = "false")
	public static boolean SHOW_PACKET_NAMES_INCHAT;
	/**
	 * Display Packets Hex-Bytes in Chat Window
	 */
	@Property(key = "gameserver.developer.show.packetbytes.inchat.enable", defaultValue = "false")
	public static boolean SHOW_PACKET_BYTES_INCHAT;
	/**
	 * How many Packet Bytes should be shown in Chat Window? Default: 200-Hexed bytes
	 */
	@Property(key = "gameserver.developer.show.packetbytes.inchat.total", defaultValue = "200")
	public static int TOTAL_PACKET_BYTES_INCHAT;
	/**
	 * Filters which Packets should be shown in Chat Windows? Default: * e.g. SM_MOVE, CM_CASTSPELL, CM_ATTACK
	 */
	@Property(key = "gameserver.developer.filter.packets.inchat", defaultValue = "*")
	public static String FILTERED_PACKETS_INCHAT;
	/**
	 * if Player Access Level is meet, display Packets-Name or Hex-Bytes in Chat Window Tip: Player Access-Level higher than or equal to 3 is recommended 10 - Server-Owner 9 - Server-CoOwner 8 -
	 * Server-Admin 7 - Server-CoAdmin 6 - Developer 5 - Admin 4 - Head-GM 3 - Senior-GM 2 - Junior-GM 1 - Supporter 0 - Players
	 */
	@Property(key = "gameserver.developer.show.packets.inchat.accesslevel", defaultValue = "6")
	public static int SHOW_PACKETS_INCHAT_ACCESSLEVEL;
}
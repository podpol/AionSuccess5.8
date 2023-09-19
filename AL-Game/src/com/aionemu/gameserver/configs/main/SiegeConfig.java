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

public class SiegeConfig
{
	@Property(key = "gameserver.siege.enable", defaultValue = "true")
	public static boolean SIEGE_ENABLED;
	
	@Property(key = "gameserver.siege.medal.rate", defaultValue = "1")
	public static int SIEGE_MEDAL_RATE;
	
	@Property(key = "gameserver.siege.shield.enable", defaultValue = "true")
	public static boolean SIEGE_SHIELD_ENABLED;
	
    @Property(key="gameserver.siege.assault.enable", defaultValue="false")
    public static boolean BALAUR_AUTO_ASSAULT;
	
    @Property(key="gameserver.siege.assault.rate", defaultValue="1")
    public static float BALAUR_ASSAULT_RATE;
	
	@Property(key = "gameserver.auto.siege.race", defaultValue = "false")
	public static boolean SIEGE_AUTO_RACE;
	
	@Property(key = "gameserver.auto.siege.id", defaultValue = "1131,1132;1141")
	public static String SIEGE_AUTO_LOCID;
}
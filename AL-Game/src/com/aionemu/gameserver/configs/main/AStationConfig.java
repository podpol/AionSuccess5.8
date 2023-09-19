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
 * @author Ranastic
 */

public class AStationConfig
{
	@Property(key = "a.station.server.id", defaultValue = "2")
    public static int A_STATION_SERVER_ID;
    @Property(key = "a.station.max.level", defaultValue = "65")
    public static int A_STATION_MAX_LEVEL;
    @Property(key = "a.station.enable", defaultValue = "true")
    public static boolean A_STATION_ENABLE;
}
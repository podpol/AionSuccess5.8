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

public class BrokerConfig
{
    @Property(key = "gameserver.broker.save.manager.interval", defaultValue = "6")
    public static int SAVE_MANAGER_INTERVAL;
    @Property(key = "gameserver.broker.time.check.expired.items.interval", defaultValue = "60")
    public static int CHECK_EXPIRED_ITEMS_INTERVAL;
    @Property(key = "gameserver.broker.antihack.punishment", defaultValue = "0")
    public static int ANTI_HACK_PUNISHMENT;
    @Property(key = "gameserver.broker.items.expiretime", defaultValue = "8")
    public static int ITEMS_EXPIRE_TIME;
}
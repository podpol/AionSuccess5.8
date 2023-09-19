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
public class PunishmentConfig {

	@Property(key = "gameserver.punishment.enable", defaultValue = "false")
	public static boolean PUNISHMENT_ENABLE;

	@Property(key = "gameserver.punishment.type", defaultValue = "1")
	public static int PUNISHMENT_TYPE;

	@Property(key = "gameserver.punishment.time", defaultValue = "1440")
	public static int PUNISHMENT_TIME;
  @Property(key="gameserver.punishment.reduceap", defaultValue="0")
  public static int PUNISHMENT_REDUCEAP;
}
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
 * @Author Ghostfur (Aion-Unique)
 */
public class PvPConfig {


  /**
   * Allowed Kills in configuered time for full AP. Move to separate config when more pvp options.
   */
  @Property(key = "gameserver.pvp.maxkills", defaultValue = "5")
  public static int MAX_DAILY_PVP_KILLS;

  /**
   * Enable Toll Reward for PvP
   */
  @Property(key = "gameserver.pvp.toll.rewarding.enable", defaultValue = "false")
  public static boolean ENABLE_TOLL_REWARD;
  @Property(key = "gameserver.pvp.toll.reward.chance", defaultValue = "50")
  public static int TOLL_CHANCE;
  @Property(key = "gameserver.pvp.toll.reward.quantity", defaultValue = "5")
  public static int TOLL_QUANTITY;

  /**
   * Killing Spree System
   */
  @Property(key = "gameserver.pvp.killingspree.enable", defaultValue = "false")
  public static boolean ENABLE_KILLING_SPREE_SYSTEM;
  @Property(key = "gameserver.pvp.raw.killcount.spree", defaultValue = "5")
  public static int SPREE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.carnage", defaultValue = "10")
  public static int CARNAGE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.genocide", defaultValue = "15")
  public static int GENOCIDE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.rampage", defaultValue = "20")
  public static int RAMPAGE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.dominating", defaultValue = "25")
  public static int DOMINATING_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.unstoppable", defaultValue = "30")
  public static int UNSTOPPABLE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.insanemonster", defaultValue = "35")
  public static int INSANEMONSTER_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.godlike", defaultValue = "40")
  public static int GODLIKE_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.wickedsick", defaultValue = "45")
  public static int WICKEDSICK_KILL_COUNT;
  @Property(key = "gameserver.pvp.raw.killcount.muthafakaaas", defaultValue = "50")
  public static int MUTHAFAKAAAS_KILL_COUNT;

  /**
   * SPREE REWARDING
   */
  @Property(key = "gameserver.pvp.spree_reward1.count", defaultValue = "10")
  public static int SPREE_REWARD_COUNT1;
  @Property(key = "gameserver.pvp.spree_reward2.count", defaultValue = "10")
  public static int SPREE_REWARD_COUNT2;
  @Property(key = "gameserver.pvp.spree_reward1.item", defaultValue = "166020000")
  public static int SPREE_REWARD_ITEM1;
  @Property(key = "gameserver.pvp.spree_reward2.item", defaultValue = "166030005")
  public static int SPREE_REWARD_ITEM2;

  /**
   * PvP GP Reward
   */
  @Property(key = "gameserver.enable.gp.reward", defaultValue = "false")
  public static boolean ENABLE_GP_REWARD;
  @Property(key = "gameserver.enable.gp.lose", defaultValue = "false")
  public static boolean ENABLE_GP_LOSE;
  @Property(key = "gameserver.enable.gp.lose.fixed", defaultValue = "false")
  public static boolean ENABLE_GP_FIXED_LOSE;
  @Property(key = "gameserver.gp.lose", defaultValue = "100")
  public static int GP_LOSE;

  /**
   * PvP Chainkill Restriction
   */
  @Property(key="gameserver.pvp.chainkill.time.restriction", defaultValue="0")
  public static int CHAIN_KILL_TIME_RESTRICTION;
  @Property(key="gameserver.pvp.chainkill.number.restriction", defaultValue="30")
  public static int CHAIN_KILL_NUMBER_RESTRICTION;
  @Property(key="gameserver.pvp.max.leveldiff.restriction", defaultValue="9")
  public static int MAX_AUTHORIZED_LEVEL_DIFF;

  /**
   * Medal rewarding
   */
  @Property(key="gameserver.pvp.medal.rewarding.enable", defaultValue="false")
  public static boolean ENABLE_MEDAL_REWARDING;
  @Property(key="gameserver.pvp.medal.reward.chance", defaultValue="10")
  public static float MEDAL_REWARD_CHANCE;
  @Property(key="gameserver.pvp.medal.reward.quantity", defaultValue="1")
  public static int MEDAL_REWARD_QUANTITY;

  /**
   * Medal rewarding
   */
  @Property(key="gameserver.pvp.special_reward.type", defaultValue="0")
  public static int GENOCIDE_SPECIAL_REWARDING;
  @Property(key="gameserver.pvp.special_reward.chance", defaultValue="2")
  public static float SPECIAL_REWARD_CHANCE;
}
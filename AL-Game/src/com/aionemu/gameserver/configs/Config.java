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
package com.aionemu.gameserver.configs;

import com.aionemu.commons.configs.CommonsConfig;
import com.aionemu.commons.configs.DatabaseConfig;
import com.aionemu.commons.configuration.ConfigurableProcessor;
import com.aionemu.commons.utils.PropertiesUtils;
import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.configs.administration.DeveloperConfig;
import com.aionemu.gameserver.configs.main.*;
import com.aionemu.gameserver.configs.network.IPConfig;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Config
{
	protected static final Logger log = LoggerFactory.getLogger(Config.class);
	
	public static void load() {
		try {
			Properties myProps = null;
			try {
				log.info("Loading: mygs.properties");
				myProps = PropertiesUtils.load("./config/mygs.properties");
			} catch (Exception e) {
				log.info("No override properties found");
			}
			String administration = "./config/administration";
			Properties[] adminProps = PropertiesUtils.loadAllFromDirectory(administration);
			PropertiesUtils.overrideProperties(adminProps, myProps);
			ConfigurableProcessor.process(AdminConfig.class, adminProps);
			ConfigurableProcessor.process(DeveloperConfig.class, adminProps);
			String main = "./config/main";
			Properties[] mainProps = PropertiesUtils.loadAllFromDirectory(main);
			PropertiesUtils.overrideProperties(mainProps, myProps);
			ConfigurableProcessor.process(AIConfig.class, mainProps);
			ConfigurableProcessor.process(BrokerConfig.class, mainProps);
			ConfigurableProcessor.process(CommonsConfig.class, mainProps);
			ConfigurableProcessor.process(CacheConfig.class, mainProps);
			ConfigurableProcessor.process(CleaningConfig.class, mainProps);
			ConfigurableProcessor.process(CraftConfig.class, mainProps);
			ConfigurableProcessor.process(CustomConfig.class, mainProps);
			ConfigurableProcessor.process(DropConfig.class, mainProps);
			ConfigurableProcessor.process(EnchantsConfig.class, mainProps);
			ConfigurableProcessor.process(EventsConfig.class, mainProps);
			ConfigurableProcessor.process(FallDamageConfig.class, mainProps);
			ConfigurableProcessor.process(AStationConfig.class, mainProps);
			ConfigurableProcessor.process(GSConfig.class, mainProps);
			ConfigurableProcessor.process(GeoDataConfig.class, mainProps);
			ConfigurableProcessor.process(GroupConfig.class, mainProps);
			ConfigurableProcessor.process(HousingConfig.class, mainProps);
			ConfigurableProcessor.process(HTMLConfig.class, mainProps);
			ConfigurableProcessor.process(InGameShopConfig.class, mainProps);
			ConfigurableProcessor.process(AbyssLandingConfig.class, mainProps);
			ConfigurableProcessor.process(LegionConfig.class, mainProps);
			ConfigurableProcessor.process(LoggingConfig.class, mainProps);
			ConfigurableProcessor.process(MembershipConfig.class, mainProps);
			ConfigurableProcessor.process(NameConfig.class, mainProps);
			ConfigurableProcessor.process(PeriodicSaveConfig.class, mainProps);
			ConfigurableProcessor.process(PlayerTransferConfig.class, mainProps);
			ConfigurableProcessor.process(PricesConfig.class, mainProps);
			ConfigurableProcessor.process(PunishmentConfig.class, mainProps);
			ConfigurableProcessor.process(PvPConfig.class, mainProps);
			ConfigurableProcessor.process(RankingConfig.class, mainProps);
			ConfigurableProcessor.process(RateConfig.class, mainProps);
			ConfigurableProcessor.process(SecurityConfig.class, mainProps);
			ConfigurableProcessor.process(ShutdownConfig.class, mainProps);
			ConfigurableProcessor.process(SiegeConfig.class, mainProps);
			ConfigurableProcessor.process(ThreadConfig.class, mainProps);
			ConfigurableProcessor.process(WeddingsConfig.class, mainProps);
			ConfigurableProcessor.process(WorldConfig.class, mainProps);
			ConfigurableProcessor.process(AdvCustomConfig.class, mainProps);
			ConfigurableProcessor.process(AutoGroupConfig.class, mainProps);
			ConfigurableProcessor.process(PvPModConfig.class, mainProps);
			ConfigurableProcessor.process(FFAConfig.class, mainProps);
			ConfigurableProcessor.process(ArchDaevaConfig.class, mainProps);
			String network = "./config/network";
			Properties[] networkProps = PropertiesUtils.loadAllFromDirectory(network);
			PropertiesUtils.overrideProperties(networkProps, myProps);
			ConfigurableProcessor.process(DatabaseConfig.class, networkProps);
			ConfigurableProcessor.process(NetworkConfig.class, networkProps);
		} catch (Exception e) {
			log.error("Can't load gameserver configuration: ", e);
			throw new Error("Can't load gameserver configuration: ", e);
		}
		IPConfig.load();
	}
	
	public static void reload() {
		try {
			Properties myProps = null;
			try {
				log.info("Loading: mygs.properties");
				myProps = PropertiesUtils.load("./config/mygs.properties");
			} catch (Exception e) {
				log.info("No override properties found");
			}
			String administration = "./config/administration";
			Properties[] adminProps = PropertiesUtils.loadAllFromDirectory(administration);
			PropertiesUtils.overrideProperties(adminProps, myProps);
			ConfigurableProcessor.process(AdminConfig.class, adminProps);
			ConfigurableProcessor.process(DeveloperConfig.class, adminProps);
			String main = "./config/main";
			Properties[] mainProps = PropertiesUtils.loadAllFromDirectory(main);
			PropertiesUtils.overrideProperties(mainProps, myProps);
			ConfigurableProcessor.process(AIConfig.class, mainProps);
			ConfigurableProcessor.process(BrokerConfig.class, mainProps);
			ConfigurableProcessor.process(CommonsConfig.class, mainProps);
			ConfigurableProcessor.process(CacheConfig.class, mainProps);
			ConfigurableProcessor.process(CleaningConfig.class, mainProps);
			ConfigurableProcessor.process(CraftConfig.class, mainProps);
			ConfigurableProcessor.process(CustomConfig.class, mainProps);
			ConfigurableProcessor.process(DropConfig.class, mainProps);
			ConfigurableProcessor.process(EnchantsConfig.class, mainProps);
			ConfigurableProcessor.process(EventsConfig.class, mainProps);
			ConfigurableProcessor.process(FallDamageConfig.class, mainProps);
			ConfigurableProcessor.process(AStationConfig.class, mainProps);
			ConfigurableProcessor.process(GSConfig.class, mainProps);
			ConfigurableProcessor.process(GeoDataConfig.class, mainProps);
			ConfigurableProcessor.process(GroupConfig.class, mainProps);
			ConfigurableProcessor.process(HousingConfig.class, mainProps);
			ConfigurableProcessor.process(HTMLConfig.class, mainProps);
			ConfigurableProcessor.process(InGameShopConfig.class, mainProps);
			ConfigurableProcessor.process(AbyssLandingConfig.class, mainProps);
			ConfigurableProcessor.process(LegionConfig.class, mainProps);
			ConfigurableProcessor.process(LoggingConfig.class, mainProps);
			ConfigurableProcessor.process(MembershipConfig.class, mainProps);
			ConfigurableProcessor.process(NameConfig.class, mainProps);
			ConfigurableProcessor.process(PeriodicSaveConfig.class, mainProps);
			ConfigurableProcessor.process(PlayerTransferConfig.class, mainProps);
			ConfigurableProcessor.process(PricesConfig.class, mainProps);
			ConfigurableProcessor.process(PunishmentConfig.class, mainProps);
			ConfigurableProcessor.process(PvPConfig.class, mainProps);
			ConfigurableProcessor.process(RankingConfig.class, mainProps);
			ConfigurableProcessor.process(RateConfig.class, mainProps);
			ConfigurableProcessor.process(SecurityConfig.class, mainProps);
			ConfigurableProcessor.process(ShutdownConfig.class, mainProps);
			ConfigurableProcessor.process(SiegeConfig.class, mainProps);
			ConfigurableProcessor.process(ThreadConfig.class, mainProps);
			ConfigurableProcessor.process(WeddingsConfig.class, mainProps);
			ConfigurableProcessor.process(WorldConfig.class, mainProps);
			ConfigurableProcessor.process(AdvCustomConfig.class, mainProps);
			ConfigurableProcessor.process(AutoGroupConfig.class, mainProps);
			ConfigurableProcessor.process(PvPModConfig.class, mainProps);
			ConfigurableProcessor.process(FFAConfig.class, mainProps);
			ConfigurableProcessor.process(ArchDaevaConfig.class, mainProps);
		} catch (Exception e) {
			log.error("Can't reload configuration: ", e);
			throw new Error("Can't reload configuration: ", e);
		}
	}
}
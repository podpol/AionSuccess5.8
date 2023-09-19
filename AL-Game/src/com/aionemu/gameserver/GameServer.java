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
package com.aionemu.gameserver;

import com.aionemu.commons.database.DatabaseFactory;
import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.commons.network.NioServer;
import com.aionemu.commons.network.ServerCfg;
import com.aionemu.commons.services.CronService;
import com.aionemu.commons.utils.AEInfos;
import com.aionemu.gameserver.ai2.AI2Engine;
import com.aionemu.gameserver.cache.HTMLCache;
import com.aionemu.gameserver.configs.Config;
import com.aionemu.gameserver.configs.main.*;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.instance.InstanceEngine;
import com.aionemu.gameserver.model.GameEngine;
import com.aionemu.gameserver.model.house.MaintenanceTask;
import com.aionemu.gameserver.model.siege.Influence;
import com.aionemu.gameserver.network.BannedMacManager;
import com.aionemu.gameserver.network.aion.GameConnectionFactoryImpl;
import com.aionemu.gameserver.network.chatserver.ChatServer;
import com.aionemu.gameserver.network.loginserver.LoginServer;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.services.abyss.AbyssRankCleaningService;
import com.aionemu.gameserver.services.abyss.AbyssRankUpdateService;
import com.aionemu.gameserver.services.abysslandingservice.LandingUpdateService;
import com.aionemu.gameserver.services.drop.DropRegistrationService;
import com.aionemu.gameserver.services.events.*;
import com.aionemu.gameserver.services.events.ShugoSweepService;
//import com.aionemu.gameserver.services.events.ThreesUpgradeService;
import com.aionemu.gameserver.services.gc.GarbageCollector;
import com.aionemu.gameserver.services.instance.*;
import com.aionemu.gameserver.services.player.LunaShopService;
import com.aionemu.gameserver.services.player.PlayerEventService;
import com.aionemu.gameserver.services.player.PlayerLimitService;
import com.aionemu.gameserver.services.ranking.SeasonRankingUpdateService;
import com.aionemu.gameserver.services.reward.RewardService;
import com.aionemu.gameserver.services.teleport.HotspotTeleportService;
import com.aionemu.gameserver.services.territory.TerritoryService;
import com.aionemu.gameserver.services.transfers.PlayerTransferService;
import com.aionemu.gameserver.spawnengine.ShugoImperialTombSpawnManager;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.spawnengine.TemporarySpawnEngine;
import com.aionemu.gameserver.taskmanager.TaskManagerFromDB;
import com.aionemu.gameserver.taskmanager.tasks.PacketBroadcaster;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.utils.chathandlers.ChatProcessor;
import com.aionemu.gameserver.utils.cron.ThreadPoolManagerRunnableRunner;
import com.aionemu.gameserver.utils.gametime.DateTimeUtil;
import com.aionemu.gameserver.utils.gametime.GameTimeManager;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.aionemu.gameserver.utils.javaagent.JavaAgentUtils;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.geo.GeoService;
import com.aionemu.gameserver.world.zone.ZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ch.lambdaj.Lambda;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * GameServer is the main class of the application and represents the whole game server.
 * This class is also an entry point with main() method.
 *
 * @author Ghostfur (Aion-Unique)
 */
public class GameServer
{
	public static final Logger log = LoggerFactory.getLogger(GameServer.class);
	public static HashSet<String> npcs_count = new HashSet<String>();

	private static void initalizeLoggger() {
		new File("./log/backup/").mkdirs();
		File[] files = new File("log").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".log");
			}
		});
		if (files != null && files.length > 0) {
			byte[] buf = new byte[1024];
			try {
				String outFilename = "./log/backup/" + new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date()) + ".zip";
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
				out.setMethod(ZipOutputStream.DEFLATED);
				out.setLevel(Deflater.BEST_COMPRESSION);

				for (File logFile : files) {
					FileInputStream in = new FileInputStream(logFile);
					out.putNextEntry(new ZipEntry(logFile.getName()));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
					logFile.delete();
				}
				out.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			lc.reset();
			configurator.doConfigure("config/slf4j-logback.xml");
		}
		catch (JoranException je) {
			throw new RuntimeException("[LoggerFactory] Failed to configure loggers, shutting down...", je);
		}
	}

	/**
	 * Launching method for GameServer
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Lambda.enableJitting(true);
		final GameEngine[] parallelEngines = { QuestEngine.getInstance(), InstanceEngine.getInstance(), AI2Engine.getInstance(), ChatProcessor.getInstance() };

		final CountDownLatch progressLatch = new CountDownLatch(parallelEngines.length);
			initalizeLoggger();
			initUtilityServicesAndConfig();
		Util.printSection(" *** StaticData *** ");
			DataManager.getInstance();
		Util.printSection(" *** IDFactory *** ");
			IDFactory.getInstance();
		Util.printSection(" *** World *** ");
			ZoneService.getInstance().load(null);
			HotspotTeleportService.getInstance();
			RoadService.getInstance();
			System.gc();
			World.getInstance();

	/**
	 * Event
	 */
		Util.printSection(" *** Luna Shop System *** ");
			LunaShopService.getInstance().init();
		Util.printSection(" *** Minion System *** ");
			//MinionService.getInstance().init();
		Util.printSection(" *** Shugo Sweep System *** ");
			ShugoSweepService.getInstance().initShugoSweep();
		Util.printSection(" *** Atreian Passport System *** ");
			AtreianPassportService.getInstance().onStart();
		Util.printSection(" *** Event Window System *** ");
		 	EventWindowService.getInstance().initialize();

	/**
	 * GeoData
	 */
		Util.printSection(" *** Geodata *** ");
		GeoService.getInstance().initializeGeo();
		DropRegistrationService.getInstance();
		GameServer gs = new GameServer();
		DAOManager.getDAO(PlayerDAO.class).setPlayersOffline(false);

	/**
	* Engines
	*/
		Util.printSection(" *** Engines *** ");
		for (int i = 0; i < parallelEngines.length; i++) {
			final int index = i;
			ThreadPoolManager.getInstance().execute(new Runnable() {
				public void run() {
					parallelEngines[index].load(progressLatch);
				}
			});
		}
		try {
			progressLatch.await();
		}
		catch (InterruptedException e1) {
		}

		/**
        * Location Data
        */
		Util.printSection(" *** Siege Location Data *** ");
			SiegeService.getInstance().initSiegeLocations();
		Util.printSection(" *** Base Location Data *** ");
			BaseService.getInstance().initBaseReset();
			BaseService.getInstance().initBaseLocations();
		Util.printSection(" *** Outpost Location Data *** ");
			OutpostService.getInstance().initOupostReset();
			OutpostService.getInstance().initOutpostLocations();
		Util.printSection(" *** Vortex Location Data *** ");
			VortexService.getInstance().initVortex();
			VortexService.getInstance().initVortexLocations();
		Util.printSection(" *** Beritra Location Data *** ");
			BeritraService.getInstance().initBeritra();
			BeritraService.getInstance().initBeritraLocations();
		Util.printSection(" *** Agent Location Data *** ");
			AgentService.getInstance().initAgent();
			AgentService.getInstance().initAgentLocations();
		Util.printSection(" *** Anoha Location Data *** ");
			AnohaService.getInstance().initAnoha();
			AnohaService.getInstance().initAnohaLocations();
		Util.printSection(" *** Svs Location Data *** ");
			SvsService.getInstance().initSvs();
			SvsService.getInstance().initSvsLocations();
		Util.printSection(" *** Rvr Location Data *** ");
			RvrService.getInstance().initRvr();
			RvrService.getInstance().initRvrLocations();
		Util.printSection(" *** Concert Location Data *** ");
			IuService.getInstance().initConcert();
			IuService.getInstance().initConcertLocations();
		Util.printSection(" *** Nightmare Circus Location Data *** ");
			NightmareCircusService.getInstance().initCircus();
			NightmareCircusService.getInstance().initCircusLocations();
		Util.printSection(" *** Dynamic Rift Location Data *** ");
			DynamicRiftService.getInstance().initDynamicRift();
			DynamicRiftService.getInstance().initDynamicRiftLocations();
		Util.printSection(" *** Instance Rift Location Data *** ");
			InstanceRiftService.getInstance().initInstance();
			InstanceRiftService.getInstance().initInstanceLocations();
		Util.printSection(" *** Zorshiv Dredgion Location Data *** ");
			ZorshivDredgionService.getInstance().initZorshivDredgion();
			ZorshivDredgionService.getInstance().initZorshivDredgionLocations();
		Util.printSection(" *** Moltenus Location Data *** ");
			MoltenusService.getInstance().initMoltenus();
			MoltenusService.getInstance().initMoltenusLocations();
		Util.printSection(" *** Rift Location Data *** ");
			RiftService.getInstance().initRifts();
			RiftService.getInstance().initRiftLocations();
		Util.printSection(" *** Conquest/Offering Location Data *** ");
			ConquestService.getInstance().initOffering();
			ConquestService.getInstance().initConquestLocations();
		Util.printSection(" *** Idian Depths Location Data *** ");
			IdianDepthsService.getInstance().initIdianDepths();
			IdianDepthsService.getInstance().initIdianDepthsLocations();
		Util.printSection(" *** Tower Of Eternity Location Data *** ");
			TowerOfEternityService.getInstance().initTowerOfEternity();
			TowerOfEternityService.getInstance().initTowerOfEternityLocation();
		Util.printSection(" *** Abyss Landing Location Data *** ");
			AbyssLandingService.getInstance().initLandingLocations();
			LandingUpdateService.getInstance().initResetQuestPoints();
			LandingUpdateService.getInstance().initResetAbyssLandingPoints();
			AbyssLandingSpecialService.getInstance().initLandingSpecialLocations();
		Util.printSection(" *** Threes Upgrade Data *** ");
			//ThreesUpgradeService.getInstance().initThrees();

		/**
        * Spawns
        */
		Util.printSection(" *** Spawns *** ");
			SpawnEngine.spawnAll();

		if (EventsConfig.ENABLE_EVENT_SERVICE) {
			EventService.getInstance().start();
		}
		if (EventsConfig.EVENT_ENABLED) {
			PlayerEventService.getInstance();
		}
		if (EventsConfig.ENABLE_CRAZY) {
			CrazyDaevaService.getInstance().startTimer();
		}
		if (RankingConfig.TOP_RANKING_UPDATE_SETTING) {
			AbyssRankUpdateService.getInstance().scheduleUpdateHour();
		} else {
			AbyssRankUpdateService.getInstance().scheduleUpdateMinute();
		}
			//Reward Weekly Manager 5.3
			AbyssRankUpdateService.getInstance().initRewardWeeklyManager();

		/**
		 * Schedules Garbage Collector to be launched at the specified time to be optimized unused memory
		 */
		GarbageCollector.getInstance().start();

		PacketBroadcaster.getInstance();

		TemporarySpawnEngine.spawnAll();

		/**
		 * Cleaning
		 */
		Util.printSection(" *** Cleaning *** ");
			DatabaseCleaningService.getInstance();
			AbyssRankCleaningService.getInstance();

		/**
		 * Scheduled Services
		 */
		Util.printSection(" *** Scheduled Services *** ");
		if (EventsConfig.ENABLE_PIG_POPPY_EVENT) {
			PigPoppyEventService.ScheduleCron();
		}
		if (EventsConfig.ENABLE_ABYSS_EVENT) {
			TreasureAbyssService.ScheduleCron();
		}
		if (EventsConfig.IMPERIAL_TOMB_ENABLE) {
			ShugoImperialTombSpawnManager.getInstance().start();
		}

		/**
        * Custom Events
        */
		Util.printSection(" *** Custom Events *** ");
		    if (FFAConfig.FFA_ENABLED) {
			FFAService.getInstance();
		    }
		    if (PvPModConfig.BG_ENABLED) {
			LadderService.getInstance();
			BGService.getInstance();
		    }
		    BanditService.getInstance().onInit();

	/**
	* Siege Schedule Initialization
	*/
	    Util.printSection(" *** Sieges *** ");
		    SiegeService.getInstance().initSieges();
			BaseService.getInstance().initBases();

	/**
	* Dredgion
	*/
		Util.printSection(" *** Dredgion *** ");
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			DredgionService2.getInstance().initDredgion();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			AsyunatarService.getInstance().initAsyunatar();
		    }

	/**
	* Battlefield
	*/
		Util.printSection(" *** Battlefield *** ");
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			KamarBattlefieldService.getInstance().initKamarBattlefield();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			EngulfedOphidanBridgeService.getInstance().initEngulfedOphidan();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			SuspiciousOphidanBridgeService.getInstance().initSuspiciousOphidan();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			IronWallWarfrontService.getInstance().initIronWallWarfront();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			IdgelDomeService.getInstance().initIdgelDome();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			IdgelDomeLandmarkService.getInstance().initLandmark();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			HallOfTenacityService.getInstance().initHallOfTenacity();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			GrandArenaTrainingCampService.getInstance().initGrandArenaTrainingCamp();
		    }
		    if (AutoGroupConfig.AUTO_GROUP_ENABLED) {
			IDRunService.getInstance().initIDRun();
		    }

	/**
	* Protector/Conqueror
	*/
		Util.printSection(" *** Protector/Conqueror initialization *** ");
		    ProtectorConquerorService.getInstance().initSystem();

	/**
	 * Dispute Land
	 */
		Util.printSection(" *** Dispute Land initialization *** ");
		    DisputeLandService.getInstance().initDisputeLand();
		    OutpostService.getInstance().initOutposts();

		/**
		 * HTML
		 */
		Util.printSection(" *** HTML *** ");
			HTMLCache.getInstance();

		if (CustomConfig.ENABLE_REWARD_SERVICE) {
			RewardService.getInstance();
		}
		if (WeddingsConfig.WEDDINGS_ENABLE) {
			WeddingService.getInstance();
		}

		/**
        * Services
        */
		Util.printSection(" *** Services *** ");
			PeriodicSaveService.getInstance();
		    AdminService.getInstance();
		    PlayerTransferService.getInstance();
		    TerritoryService.getInstance().initTerritory();
		    GameTimeService.getInstance();
		    AnnouncementService.getInstance();
		    DebugService.getInstance();
		    WeatherService.getInstance();
		    BrokerService.getInstance();
		    Influence.getInstance();
		    ExchangeService.getInstance();
		    PetitionService.getInstance();
		    InstanceService.load();
		    FlyRingService.getInstance();
		    CuringZoneService.getInstance();
		    SpringZoneService.getInstance();
			BoostEventService.getInstance().onStart();
		    TaskManagerFromDB.getInstance();
		    //LimitedItemTradeService.getInstance().start();
            GameTimeManager.startClock();

		    if (CustomConfig.LIMITS_ENABLED) {
			PlayerLimitService.getInstance().scheduleUpdate();
		    }
		    if (AIConfig.SHOUTS_ENABLE) {
			NpcShoutsService.getInstance();
		    }
		    if (SiegeConfig.SIEGE_SHIELD_ENABLED) {
			ShieldService.getInstance().spawnAll();
		    }

		/**
		 * Season Ranking Update
		 */
		Util.printSection(" *** Season Ranking *** ");
		SeasonRankingUpdateService.getInstance().onStart();

		/**
        * Housing
        */
		Util.printSection(" *** Housing *** ");
		    HousingBidService.getInstance().start();
		    MaintenanceTask.getInstance();
		    TownService.getInstance();
		    ChallengeTaskService.getInstance();

	/**
	* System
	*/
		Util.printSection(" *** System *** ");
		    System.gc();
		    AEVersions.printFullVersionInfo();
		    AEInfos.printAllInfos();
		    System.out.println("");
		    log.info("[Aion-Rexus] GameServer started in " + (System.currentTimeMillis() - start) / 1000 + " seconds.");

	/**
	* Developers Messenger
	*/
		Util.printSection(" *** Developers *** ");
		try {
			ZCXInfo.getInfo();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		gs.startServers();
		Runtime.getRuntime().addShutdownHook(ShutdownHook.getInstance());
		ZCXInfo.checkForRatioLimitation();
		onStartup();
	}

	/**
	 * Starts servers for connection with aion client and login\chat server.
	 */
	private void startServers() {
		Util.printSection(" *** Network *** ");
		NioServer nioServer = new NioServer(NetworkConfig.NIO_READ_WRITE_THREADS, new ServerCfg(NetworkConfig.GAME_BIND_ADDRESS, NetworkConfig.GAME_PORT, "Game Connections", new GameConnectionFactoryImpl()));
		BannedMacManager.getInstance();

		LoginServer ls = LoginServer.getInstance();
		ChatServer cs = ChatServer.getInstance();

		ls.setNioServer(nioServer);
		cs.setNioServer(nioServer);

		// Nio must go first
		nioServer.connect();
		System.out.println("");
		ls.connect();

		if (GSConfig.ENABLE_CHAT_SERVER)
			cs.connect();

		Util.printSection(" *** Misc *** ");
	}

	/**
	 * Initialize all helper services, that are not directly related to aion gs, which includes:
	 */
	private static void initUtilityServicesAndConfig() {
		// Set default uncaught exception handler
		Thread.setDefaultUncaughtExceptionHandler(new ThreadUncaughtExceptionHandler());
		// make sure that callback code was initialized
		if (JavaAgentUtils.isConfigured()) {
			log.info("JavaAgent [Callback Support] is configured.");
		}
		// Initialize cron service
		CronService.initSingleton(ThreadPoolManagerRunnableRunner.class);
		Util.printSection(" *** Config *** ");
		// init config
		Config.load();
		// DateTime zone override from configs
		DateTimeUtil.init();
		Util.printSection(" *** DataBase *** ");
		DatabaseFactory.init();
		// Initialize DAOs
		DAOManager.init();
		// Initialize thread pools
		Util.printSection(" *** Threads *** ");
		ThreadConfig.load();
		ThreadPoolManager.getInstance();
	}

	private static Set<StartupHook> startUpHooks = new HashSet<StartupHook>();

	public synchronized static void addStartupHook(StartupHook hook) {
		if (startUpHooks != null) {
			startUpHooks.add(hook);
		}
		else {
			hook.onStartup();
		}
	}
	private synchronized static void onStartup() {
		final Set<StartupHook> startupHooks = startUpHooks;

		startUpHooks = null;

		for (StartupHook hook : startupHooks) {
			hook.onStartup();
		}
	}

	public interface StartupHook {
		public void onStartup();
	}
}
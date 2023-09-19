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
package com.aionemu.gameserver.services.gc;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.GameServer;
import com.aionemu.gameserver.configs.main.GSConfig;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class GarbageCollector extends Thread {

	private static final Logger log = LoggerFactory.getLogger(GarbageCollector.class);

	private static long g_Period = (30 * 60 * 1000); // 30 minutes

	public GarbageCollector() {
		g_Period = (GSConfig.GC_OPTIMIZATION_TIME < 1) ? 30 : GSConfig.GC_OPTIMIZATION_TIME;
		g_Period = g_Period * 60 * 1000;
	}

	/**
	 * instantiate class
	 */
	private static class SingletonHolder {

		protected static final GarbageCollector instance = new GarbageCollector();
	}

	public static GarbageCollector getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	public void run() {
		if (GSConfig.ENABLE_MEMORY_GC) {
			GameServer.log.info("[GarbageCollector] Garbage Collector is scheduled to start in " + String.valueOf((g_Period / 1000) / 60) + " minutes.");
			StartMemoryOptimization();
		}
		else {
			GameServer.log.info("[GarbageCollector] Garbage Collector is turned off by administrator.");
		}
	}

	private void StartMemoryOptimization() {

		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					// When we reload configs, it need to initialized again.
					g_Period = (GSConfig.GC_OPTIMIZATION_TIME < 1) ? 30 : GSConfig.GC_OPTIMIZATION_TIME;
					g_Period = g_Period * 60 * 1000;

					if (GSConfig.ENABLE_MEMORY_GC) {
						log.info("[GarbageCollector] Garbage Collector is optimizing memory to free unused heap memory.");
						System.gc();
						System.runFinalization();
						log.info("[GarbageCollector] Garbage Collector has finished optimizing memory.");
					}
				}
				catch (Exception e) {
					log.error("[GarbageCollector] Error on optimizing memory: " + e.getMessage());
				}
			}
		}, g_Period);
	}
}

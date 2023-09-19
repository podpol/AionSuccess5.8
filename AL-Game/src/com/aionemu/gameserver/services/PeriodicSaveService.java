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
package com.aionemu.gameserver.services;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.configs.main.PeriodicSaveConfig;
import com.aionemu.gameserver.dao.InventoryDAO;
import com.aionemu.gameserver.dao.ItemStoneListDAO;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.Future;

/**
 * @author ATracer
 */
public class PeriodicSaveService {

	private static final Logger log = LoggerFactory.getLogger(PeriodicSaveService.class);

	private Future<?> legionWhUpdateTask;

	public static final PeriodicSaveService getInstance() {
		return SingletonHolder.instance;
	}

	private PeriodicSaveService() {

		int DELAY_LEGION_ITEM = PeriodicSaveConfig.LEGION_ITEMS * 1000;

		legionWhUpdateTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new LegionWhUpdateTask(),
			DELAY_LEGION_ITEM, DELAY_LEGION_ITEM);
	}

	private class LegionWhUpdateTask implements Runnable {

		@Override
		public void run() {
			log.info("Legion WH update task started.");
			long startTime = System.currentTimeMillis();
			Iterator<Legion> legionsIterator = LegionService.getInstance().getCachedLegionIterator();
			int legionWhUpdated = 0;
			while (legionsIterator.hasNext()) {
				Legion legion = legionsIterator.next();
				FastList<Item> allItems = legion.getLegionWarehouse().getItemsWithKinah();
				allItems.addAll(legion.getLegionWarehouse().getDeletedItems());
				try {
					/**
					 * 1. save items first
					 */
					DAOManager.getDAO(InventoryDAO.class).store(allItems, null, null, legion.getLegionId());

					/**
					 * 2. save item stones
					 */
					DAOManager.getDAO(ItemStoneListDAO.class).save(allItems);
				}
				catch (Exception ex) {
					log.error("Exception during periodic saving of legion WH", ex);
				}

				legionWhUpdated++;
			}
			long workTime = System.currentTimeMillis() - startTime;
			log.info("Legion WH update: " + workTime + " ms, legions: " + legionWhUpdated + ".");
		}
	}

	/**
	 * Save data on shutdown
	 */
	public void onShutdown() {
		log.info("Starting data save on shutdown.");
		// save legion warehouse
		legionWhUpdateTask.cancel(false);
		new LegionWhUpdateTask().run();
		log.info("Data successfully saved.");
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {

		protected static final PeriodicSaveService instance = new PeriodicSaveService();
	}
}

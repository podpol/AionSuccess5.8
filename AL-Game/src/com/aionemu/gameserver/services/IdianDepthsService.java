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

import com.aionemu.commons.services.CronService;
import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.idiandepths.IdianDepthsLocation;
import com.aionemu.gameserver.model.idiandepths.IdianDepthsStateType;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.spawns.idiandepthsspawns.*;
import com.aionemu.gameserver.services.idiandepthsservice.Idian;
import com.aionemu.gameserver.services.idiandepthsservice.IdianDepths;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class IdianDepthsService
{
	private Map<Integer, IdianDepthsLocation> idianDepths;
	private static final int duration = CustomConfig.IDIAN_DEPTHS_DURATION;
	private final Map<Integer, IdianDepths<?>> activeIdianDepths = new FastMap<Integer, IdianDepths<?>>().shared();
	private static Logger log = LoggerFactory.getLogger(IdianDepthsService.class);

	public void initIdianDepthsLocations() {
		if (CustomConfig.IDIAN_DEPTHS_ENABLED) {
			idianDepths = DataManager.IDIAN_DEPTHS_DATA.getIdianDepthsLocations();
			for (IdianDepthsLocation loc: getIdianDepthsLocations().values()) {
				spawn(loc, IdianDepthsStateType.CLOSED);
			}
			log.info("[IdianDepthsService] Loaded " + idianDepths.size() + " locations.");

			CronService.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					for (IdianDepthsLocation loc: getIdianDepthsLocations().values()) {
				        startIdianDepths(loc.getId());
					}
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					    @Override
					    public void visit(Player player) {
						    PacketSendUtility.sendSys3Message(player, "\uE0AA", "<Idian Depths> open !!!");
					    }
					});
				}
			}, CustomConfig.IDIAN_DEPTHS_SCHEDULE);
		} else {
			log.info("[IdianDepthsService] Idian Depths is disabled in config...");
			idianDepths = Collections.emptyMap();
		}
	}

	public void initIdianDepths() {
		if (CustomConfig.IDIAN_DEPTHS_ENABLED) {
			log.info("[IdianDepthsService] is initialized...");
		}
	}
	
	public void startIdianDepths(final int id) {
		final IdianDepths<?> idian;
		synchronized (this) {
			if (activeIdianDepths.containsKey(id)) {
				return;
			}
			idian = new Idian(idianDepths.get(id));
			activeIdianDepths.put(id, idian);
		}
		idian.start();
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				stopIdianDepths(id);
			}
		}, duration * 3600 * 1000);
	}
	
	public void stopIdianDepths(int id) {
		if (!isIdianDepthsInProgress(id)) {
			return;
		}
		IdianDepths<?> idian;
		synchronized (this) {
			idian = activeIdianDepths.remove(id);
		} if (idian == null || idian.isClosed()) {
			return;
		}
		idian.stop();
	}
	
	public void spawn(IdianDepthsLocation loc, IdianDepthsStateType istate) {
		if (istate.equals(IdianDepthsStateType.OPEN)) {
		}
		List<SpawnGroup2> locSpawns = DataManager.SPAWNS_DATA2.getIdianDepthsSpawnsByLocId(loc.getId());
		for (SpawnGroup2 group : locSpawns) {
			for (SpawnTemplate st : group.getSpawnTemplates()) {
				IdianDepthsSpawnTemplate idianDepthsttemplate = (IdianDepthsSpawnTemplate) st;
				if (idianDepthsttemplate.getIStateType().equals(istate)) {
					loc.getSpawned().add(SpawnEngine.spawnObject(idianDepthsttemplate, 1));
				}
			}
		}
	}
	
	public void despawn(IdianDepthsLocation loc) {
		if (loc.getSpawned() == null) {
        	return;
		} for (VisibleObject obj: loc.getSpawned()) {
            Npc spawned = (Npc) obj;
            spawned.setDespawnDelayed(true);
            if (spawned.getAggroList().getList().isEmpty()) {
                spawned.getController().cancelTask(TaskId.RESPAWN);
                obj.getController().onDelete();
            }
        }
        loc.getSpawned().clear();
	}
	
	public boolean isIdianDepthsInProgress(int id) {
		return activeIdianDepths.containsKey(id);
	}
	
	public Map<Integer, IdianDepths<?>> getActiveIdianDepths() {
		return activeIdianDepths;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public IdianDepthsLocation getIdianDepthsLocation(int id) {
		return idianDepths.get(id);
	}
	
	public Map<Integer, IdianDepthsLocation> getIdianDepthsLocations() {
		return idianDepths;
	}
	
	public static IdianDepthsService getInstance() {
		return IdianDepthsServiceHolder.INSTANCE;
	}
	
	private static class IdianDepthsServiceHolder {
		private static final IdianDepthsService INSTANCE = new IdianDepthsService();
	}
}
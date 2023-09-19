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
import com.aionemu.gameserver.configs.schedule.CircusSchedule;
import com.aionemu.gameserver.configs.schedule.CircusSchedule.Circus;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.nightmarecircus.NightmareCircusLocation;
import com.aionemu.gameserver.model.nightmarecircus.NightmareCircusStateType;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.spawns.nightmarecircusspawns.NightmareCircusSpawnTemplate;
import com.aionemu.gameserver.services.nightmarecircusservice.CircusInstance;
import com.aionemu.gameserver.services.nightmarecircusservice.CircusStartRunnable;
import com.aionemu.gameserver.services.nightmarecircusservice.Nightmare;
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

public class NightmareCircusService
{
	private CircusSchedule circusSchedule;
	private Map<Integer, NightmareCircusLocation> nightmareCircus;
	private static final int duration = CustomConfig.NIGHTMARE_CIRCUS_DURATION;
	private final Map<Integer, CircusInstance<?>> activeNightmareCircus = new FastMap<Integer, CircusInstance<?>>().shared();
	private static Logger log = LoggerFactory.getLogger(NightmareCircusService.class);

	public void initCircusLocations() {
		if (CustomConfig.NIGHTMARE_CIRCUS_ENABLE) {
			nightmareCircus = DataManager.NIGHTMARE_CIRCUS_DATA.getNightmareCircusLocations();
			for (NightmareCircusLocation loc: getNightmareCircusLocations().values()) {
				spawn(loc, NightmareCircusStateType.CLOSED);
			}
			log.info("[NightmareCircusService] Loaded " + nightmareCircus.size() + " locations.");
		} else {
			log.info("[NightmareCircusService] Nightmare Circus is disabled in config...");
			nightmareCircus = Collections.emptyMap();
		}
	}
	
	public void initCircus() {
		if (CustomConfig.NIGHTMARE_CIRCUS_ENABLE) {
			log.info("[NightmareCircusService] is initialized...");
		    circusSchedule = CircusSchedule.load();
		    for (Circus circus: circusSchedule.getCircussList()) {
			    for (String circusTime: circus.getCircusTimes()) {
				    CronService.getInstance().schedule(new CircusStartRunnable(circus.getId()), circusTime);
			    }
			}
		}
	}
	
	public void startNightmareCircus(final int id) {
		final CircusInstance<?> nightmare;
		synchronized (this) {
			if (activeNightmareCircus.containsKey(id)) {
				return;
			}
			nightmare = new Nightmare(nightmareCircus.get(id));
			activeNightmareCircus.put(id, nightmare);
		}
		nightmare.start();
		dreamFaerieMsg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				stopNightmareCircus(id);
			}
		}, duration * 3600 * 1000);
	}
	
	public void stopNightmareCircus(int id) {
		if (!isNightmareCircusInProgress(id)) {
			return;
		}
		CircusInstance<?> nightmare;
		synchronized (this) {
			nightmare = activeNightmareCircus.remove(id);
		} if (nightmare == null || nightmare.isClosed()) {
			return;
		}
		nightmare.stop();
	}
	
	public void spawn(NightmareCircusLocation loc, NightmareCircusStateType nstate) {
		if (nstate.equals(NightmareCircusStateType.OPEN)) {
		}
		List<SpawnGroup2> locSpawns = DataManager.SPAWNS_DATA2.getNightmareCircusSpawnsByLocId(loc.getId());
		for (SpawnGroup2 group : locSpawns) {
			for (SpawnTemplate st : group.getSpawnTemplates()) {
				NightmareCircusSpawnTemplate nightmareCircustemplate = (NightmareCircusSpawnTemplate) st;
				if (nightmareCircustemplate.getNStateType().equals(nstate)) {
					loc.getSpawned().add(SpawnEngine.spawnObject(nightmareCircustemplate, 1));
				}
			}
		}
	}
	
	public boolean dreamFaerieMsg(int id) {
        switch (id) {
            case 1:
                World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						PacketSendUtility.sendSys3Message(player, "\uE09B", "<Nightmare Circus> is now open !!!");
					}
				});
			    return true;
            default:
                return false;
        }
    }
	
	public void despawn(NightmareCircusLocation loc) {
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
	
	public boolean isNightmareCircusInProgress(int id) {
		return activeNightmareCircus.containsKey(id);
	}
	
	public Map<Integer, CircusInstance<?>> getActiveNightmareCircus() {
		return activeNightmareCircus;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public NightmareCircusLocation getNightmareCircusLocation(int id) {
		return nightmareCircus.get(id);
	}
	
	public Map<Integer, NightmareCircusLocation> getNightmareCircusLocations() {
		return nightmareCircus;
	}
	
	public static NightmareCircusService getInstance() {
		return NightmareCircusServiceHolder.INSTANCE;
	}
	
	private static class NightmareCircusServiceHolder {
		private static final NightmareCircusService INSTANCE = new NightmareCircusService();
	}
}
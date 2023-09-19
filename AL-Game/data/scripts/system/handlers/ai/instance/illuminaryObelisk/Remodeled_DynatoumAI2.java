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
package ai.instance.illuminaryObelisk;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("remodeled_dynatoum")
public class Remodeled_DynatoumAI2 extends AggressiveNpcAI2
{
	private Future<?> phaseTask;
	private boolean canThink = true;
	private Future<?> remodeledDynatoumFormTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean isStartedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 234686: //Remodeled Dynatoum.
					/**
					 * You have about 6 minutes to finish the boss, so all party members must be ready before activating the seal.
					 */
					//The Remodeled Dynatoum bomb has begun counting down.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402425, 0);
					//The Remodeled Dynatoum will explode in 5 minutes.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402426, 60000);
					//The Remodeled Dynatoum will explode in 1 minute.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402427, 300000);
					//The Remodeled Dynatoum is going to explode.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402428, 360000);
					remodeledDynatoumFormTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							AI2Actions.deleteOwner(Remodeled_DynatoumAI2.this);
						}
					}, 360000);
				break;
			}
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 85) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 55) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 35) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		}
	}
	
	private void startPhaseTask() {
		phaseTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelPhaseTask();
				} else {
					List<Player> players = getLifedPlayers();
					if (!players.isEmpty()) {
						int size = players.size();
						if (players.size() < 6) {
							for (Player p: players) {
								spawnMaintenanceDevice(p);
							}
						} else {
							int count = Rnd.get(6, size);
							for (int i = 0; i < count; i++) {
								if (players.isEmpty()) {
									break;
								}
								spawnMaintenanceDevice(players.get(Rnd.get(players.size())));
							}
						}
					}
				}
			}
		}, 20000, 40000);
	}
	
	private void spawnMaintenanceDevice(Player player) {
		final float x = player.getX();
		final float y = player.getY();
		final float z = player.getZ();
		if (x > 0 && y > 0 && z > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						spawn(284861, x, y, z, (byte) 0); //Maintenance Device.
					}
				}
			}, 3000);
		}
	}
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	private List<Player> getLifedPlayers() {
		List<Player> players = new ArrayList<Player>();
		for (Player player: getKnownList().getKnownPlayers().values()) {
			if (!PlayerActions.isAlreadyDead(player)) {
				players.add(player);
			}
		}
		return players;
	}
	
	private void cancelPhaseTask() {
		if (phaseTask != null && !phaseTask.isDone()) {
			phaseTask.cancel(true);
		}
	}
	
	private void cancelRemodeledDynatoumFormTask() {
		if (remodeledDynatoumFormTask != null && !remodeledDynatoumFormTask.isDone()) {
			remodeledDynatoumFormTask.cancel(true);
		}
	}
	
	private void deleteHelpers() {
		WorldMapInstance instance = getPosition().getWorldMapInstance();
		if (instance != null) {
			deleteNpcs(instance.getNpcs(284861)); //Maintenance Device.
		}
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 234686: //Remodeled Dynatoum.
			    boost();
		    break;
		}
	}
	
	@Override
	protected void handleDespawned() {
		cancelPhaseTask();
		cancelRemodeledDynatoumFormTask();
		super.handleDespawned();
	}
	
	@Override
	protected void handleBackHome() {
		canThink = true;
		deleteHelpers();
		cancelPhaseTask();
		isAggred.set(false);
		isStartedEvent.set(false);
		cancelRemodeledDynatoumFormTask();
		super.handleBackHome();
	}
	
	@Override
	protected void handleDied() {
		cancelPhaseTask();
		cancelRemodeledDynatoumFormTask();
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(284861)); //Maintenance Device.
		}
		super.handleDied();
	}
	
	private void boost() {
	    SkillEngine.getInstance().getSkill(getOwner(), 21671, 1, getOwner()).useNoAnimationSkill(); //Boost.
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
}
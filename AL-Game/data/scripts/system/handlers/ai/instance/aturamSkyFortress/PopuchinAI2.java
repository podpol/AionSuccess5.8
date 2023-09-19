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
package ai.instance.aturamSkyFortress;

import ai.AggressiveNpcAI2;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
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

@AIName("popuchin")
public class PopuchinAI2 extends AggressiveNpcAI2
{
	private Future<?> bombTask;
	private boolean canThink = true;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean isStartedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			getPosition().getWorldMapInstance().getDoors().get(126).setOpen(false);
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 90) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 70) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 50) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 30) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 10) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		}
	}
	
	private void startPhaseTask() {
		bombTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelBombTaskTask();
				} else {
					SkillEngine.getInstance().getSkill(getOwner(), 19412, 1, getOwner()).useNoAnimationSkill(); //Drop The Drana Bomb.
					List<Player> players = getLifedPlayers();
					if (!players.isEmpty()) {
						int size = players.size();
						if (players.size() < 1) {
							for (Player p: players) {
								spawnShulackBomb(p);
							}
						} else {
							int count = Rnd.get(1, size);
							for (int i = 0; i < count; i++) {
								if (players.isEmpty()) {
									break;
								}
								spawnShulackBomb(players.get(Rnd.get(players.size())));
							}
						}
					}
				}
			}
		}, 20000, 40000);
	}
	
	private void spawnShulackBomb(Player player) {
		final float x = player.getX();
		final float y = player.getY();
		final float z = player.getZ();
		if (x > 0 && y > 0 && z > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						switch (Rnd.get(1, 2)) {
						    case 1:
							    spawn(217374, x, y, z, (byte) 0); //Shulack Guided Bomb.
							break;
							case 2:
							    spawn(217375, x, y, z, (byte) 0); //Shulack Thermo Bomb.
							break;
						}
					}
				}
			}, 1000);
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
	
	private void cancelBombTaskTask() {
		if (bombTask != null && !bombTask.isDone()) {
			bombTask.cancel(true);
		}
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
	
	@Override
	protected void handleDespawned() {
		cancelBombTaskTask();
		super.handleDespawned();
	}
	
	@Override
	protected void handleBackHome() {
		canThink = true;
		deleteHelpers();
		isAggred.set(false);
		cancelBombTaskTask();
		isStartedEvent.set(false);
		getPosition().getWorldMapInstance().getDoors().get(126).setOpen(true);
		super.handleBackHome();
	}
	
	private void deleteHelpers() {
		WorldMapInstance instance = getPosition().getWorldMapInstance();
		if (instance != null) {
			deleteNpcs(instance.getNpcs(217374)); //Shulack Guided Bomb.
			deleteNpcs(instance.getNpcs(217375)); //Shulack Thermo Bomb.
		}
	}
	
	@Override
	protected void handleDied() {
		cancelBombTaskTask();
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(217374)); //Shulack Guided Bomb.
			deleteNpcs(p.getWorldMapInstance().getNpcs(217375)); //Shulack Thermo Bomb.
		}
		getPosition().getWorldMapInstance().getDoors().get(126).setOpen(false);
		super.handleDied();
	}
}
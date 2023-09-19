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
package ai.instance.empyreanCrucible;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("king_consierd")
public class KingConsierdAI2 extends AggressiveNpcAI2
{
	private List<Integer> percents = new ArrayList<Integer>();
	private AtomicBoolean isHome = new AtomicBoolean(true);
	private Future<?> eventTask;
	private Future<?> skillTask;
	
	@Override
	public void handleSpawned() {
		super.handleSpawned();
		addPercents();
	}
	
	@Override
	public void handleDespawned() {
		cancelTasks();
		percents.clear();
		super.handleDespawned();
	}
	
	@Override
	public void handleDied() {
		cancelTasks();
		super.handleDied();
	}
	
	@Override
	public void handleBackHome() {
		cancelTasks();
		addPercents();
		super.handleBackHome();
	}
	
	@Override
	public void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
		if (isHome.compareAndSet(true, false)) {
			startBloodThirstTask();
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					SkillEngine.getInstance().getSkill(getOwner(), 19691, 1, getTarget()).useNoAnimationSkill();
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							SkillEngine.getInstance().getSkill(getOwner(), 17954, 10, getTarget()).useNoAnimationSkill();
						}
					}, 4000);
				}
			}, 2000);
		}
	}
	
	private void startBloodThirstTask() {
		eventTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				SkillEngine.getInstance().getSkill(getOwner(), 19624, 10, getOwner()).useNoAnimationSkill();
			}
		}, 180 * 1000);
	}
	
	private void startSkillTask() {
		skillTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelTasks();
				} else {
					SkillEngine.getInstance().getSkill(getOwner(), 17951, 10, getTarget()).useNoAnimationSkill();
					List<Player> players = getLifedPlayers();
					if (!players.isEmpty()) {
						int size = players.size();
						if (players.size() < 6) {
							for (Player p: players) {
								spawnBabyConsierd(p);
							}
						} else {
							int count = Rnd.get(1, size);
							for (int i = 0; i < count; i++) {
								if (players.isEmpty()) {
									break;
								}
								spawnBabyConsierd(players.get(Rnd.get(players.size())));
								SkillEngine.getInstance().getSkill(getOwner(), 17952, 10, getTarget()).useNoAnimationSkill();
							}
						}
					}
				}
			}
		}, 3000, 15000);
	}
	
	private void spawnBabyConsierd(Player player) {
		final float x = player.getX();
		final float y = player.getY();
		final float z = player.getZ();
		if (x > 0 && y > 0 && z > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						spawn(282378, x, y, z, (byte) 0); //Baby Consierd.
					}
				}
			}, 3000);
		}
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
	
	private void cancelTasks() {
		if (eventTask != null && !eventTask.isDone()) {
			eventTask.cancel(true);
		} if (skillTask != null && !skillTask.isCancelled()) {
			skillTask.cancel(true);
		}
	}
	
	private void checkPercentage(int percentage) {
		for (Integer percent : percents) {
			if (percentage <= percent) {
				percents.remove(percent);
				if (percent == 75) {
					startSkillTask();
				} else if (percent == 25) {
					SkillEngine.getInstance().getSkill(getOwner(), 19690, 1, getTarget()).useNoAnimationSkill();
				}
				break;
			}
		}
	}
	
	private void addPercents() {
		percents.clear();
		Collections.addAll(percents, new Integer[] {75, 25});
	}
}
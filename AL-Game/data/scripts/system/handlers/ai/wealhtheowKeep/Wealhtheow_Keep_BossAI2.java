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
package ai.wealhtheowKeep;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("wealhtheow_keep_boss")
public class Wealhtheow_Keep_BossAI2 extends AggressiveNpcAI2
{
	private Future<?> phaseTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean isStartedEvent = new AtomicBoolean(false);
	
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		announceWealhtheowKeepBoss();
	}
	
	@Override
	protected void handleDespawned() {
		cancelPhaseTask();
		super.handleDespawned();
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 95) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 75) {
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
		} if (hpPercentage <= 15) {
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
								spawnExplosiveSacrifice(p);
							}
						} else {
							int count = Rnd.get(6, size);
							for (int i = 0; i < count; i++) {
								if (players.isEmpty()) {
									break;
								}
								spawnExplosiveSacrifice(players.get(Rnd.get(players.size())));
							}
						}
					}
				}
			}
		}, 3000, 15000);
	}
	
	private void spawnExplosiveSacrifice(Player player) {
		final float x = player.getX();
		final float y = player.getY();
		final float z = player.getZ();
		if (x > 0 && y > 0 && z > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						spawn(855262, x, y, z, (byte) 0); //Explosive Sacrifice.
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
	
	private void cancelPhaseTask() {
		if (phaseTask != null && !phaseTask.isDone()) {
			phaseTask.cancel(true);
		}
	}
	
	@Override
	protected void handleBackHome() {
		cancelPhaseTask();
		isStartedEvent.set(false);
		isAggred.set(false);
		super.handleBackHome();
	}
	
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(855262)); //Explosive Sacrifice.
		}
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(701481, 780.46515f, 288.62924f, 143.18782f, (byte) 45);
                spawn(701481, 787.1314f, 288.72644f, 143.20233f, (byte) 30);
                spawn(701481, 793.9525f, 289.05054f, 143.18248f, (byte) 15);
			}
		}, 10000);
		treasureChest();
		cancelPhaseTask();
		super.handleDied();
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
	
	private void treasureChest() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//A treasure chest has appeared.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDAbRe_Core_NmdC_BoxSpawn);
			}
		});
	}
	
	private void announceWealhtheowKeepBoss() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF5_Fortress_RuneElite);
			}
		});
	}
}
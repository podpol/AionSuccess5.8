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
package com.aionemu.gameserver.taskmanager.tasks;

import com.aionemu.gameserver.ai2.AI2Logger;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.world.WorldMapTemplate;
import com.aionemu.gameserver.taskmanager.AbstractFIFOPeriodicTaskManager;
import com.aionemu.gameserver.world.knownlist.VisitorWithOwner;

import java.util.*;
import java.util.Map.Entry;

public class MovementNotifyTask extends AbstractFIFOPeriodicTaskManager<Creature>
{
	private static Map<Integer, int[]> moveBroadcastCounts = new HashMap<Integer, int[]>();
	
	static {
		Iterator<WorldMapTemplate> iter = DataManager.WORLD_MAPS_DATA.iterator();
		while (iter.hasNext())
			moveBroadcastCounts.put(iter.next().getMapId(), new int[2]);
	}
	
	private static final class SingletonHolder {
		private static final MovementNotifyTask INSTANCE = new MovementNotifyTask();
	}
	
	public static MovementNotifyTask getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private final MoveNotifier MOVE_NOTIFIER = new MoveNotifier();
	
	public MovementNotifyTask() {
		super(500);
	}
	
	@Override
	protected void callTask(Creature creature) {
		if (creature.getLifeStats().isAlreadyDead())
			return;
		int limit =
		creature.getWorldId() == 400010000 || //Reshanta.
		creature.getWorldId() == 400020000 || //Belus.
		creature.getWorldId() == 400030000 || //Transidium Annex.
		creature.getWorldId() == 400040000 || //Aspida.
		creature.getWorldId() == 400050000 || //Atanatos.
		creature.getWorldId() == 400060000 ? 200 : Integer.MAX_VALUE; //Disillon.
		int iterations = creature.getKnownList().doOnAllNpcsWithOwner(MOVE_NOTIFIER, limit);
		if (!(creature instanceof Player)) {
			int[] maxCounts = moveBroadcastCounts.get(creature.getWorldId());
			synchronized (maxCounts) {
				if (iterations > maxCounts[0]) {
					maxCounts[0] = iterations;
					maxCounts[1] = creature.getObjectTemplate().getTemplateId();
				}
			}
		}
	}
	
	public String[] dumpBroadcastStats() {
		List<String> lines = new ArrayList<String>();
		lines.add("------- Movement broadcast counts -------");
		for (Entry<Integer, int[]> entry : moveBroadcastCounts.entrySet()) {
			lines.add("WorldId=" + entry.getKey() + ": " + entry.getValue()[0] + " (NpcId " + entry.getValue()[1] + ")");
		}
		lines.add("-----------------------------------------");
		return lines.toArray(new String[0]);
	}
	
	@Override
	protected String getCalledMethodName() {
		return "notifyOnMove()";
	}
	
	private class MoveNotifier implements VisitorWithOwner<Npc, VisibleObject> {
		@Override
		public void visit(Npc object, VisibleObject owner) {
			if (object.getAi2().getState() == AIState.DIED || object.getLifeStats().isAlreadyDead()) {
				if (object.getAi2().isLogging()) {
					AI2Logger.moveinfo(object, "WARN: NPC died but still in knownlist");
				}
				return;
			}
			object.getAi2().onCreatureEvent(AIEventType.CREATURE_MOVED, (Creature) owner);
		}
	}
}
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
package ai.instance.tallocsHollow;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("celestius")
public class CelestiusAI2 extends AggressiveNpcAI2
{
	private Future<?> helpersTask;
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isHome.compareAndSet(true, false)) {
			startHelpersCall();
		}
	}
	
	private void cancelHelpersTask() {
		if (helpersTask != null && !helpersTask.isDone()) {
			helpersTask.cancel(true);
		}
	}
	
	private void startHelpersCall() {
		helpersTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead() && getLifeStats().getHpPercentage() < 90) {
					deleteHelpers();
					cancelHelpersTask();
				} else {
					deleteHelpers();
					SkillEngine.getInstance().getSkill(getOwner(), 18981, 44, getOwner()).useNoAnimationSkill();
					startCelestiusRushEvent();
				}
			}
		}, 1000, 25000);
	}
	
	private void rushTalocHollow(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	private void startCelestiusRushEvent() {
		rushTalocHollow((Npc)spawn(281514, 518f, 813f, 1378f, (byte) 0), 539.357f, 826.74567f, 1376.8346f, false);
		rushTalocHollow((Npc)spawn(281514, 551f, 795f, 1376f, (byte) 0), 546.886848f, 819.90924f, 1376.3254f, false);
		rushTalocHollow((Npc)spawn(281514, 574f, 854f, 1375f, (byte) 0), 549.684f, 835.2079f, 1377.119f, false);
	}
	
	private void deleteHelpers() {
		WorldPosition p = getPosition();
		if (p != null) {
			WorldMapInstance instance = p.getWorldMapInstance();
			if (instance != null) {
				List<Npc> npcs = instance.getNpcs(281514);
				for (Npc npc : npcs) {
					SpawnTemplate template =  npc.getSpawn();
					if (npc != null && (template.getX() == 518 || template.getX() == 551 || template.getX() == 574)) {
						npc.getController().onDelete();
					}
				}
			}
		}
	}
	
	@Override
	protected void handleBackHome() {
		cancelHelpersTask();
		deleteHelpers();
		isHome.set(true);
		super.handleBackHome();
	}
	
	@Override
	protected void handleDespawned() {
		cancelHelpersTask();
		deleteHelpers();
		super.handleDespawned();
	}
	
	@Override
	protected void handleDied() {
		cancelHelpersTask();
		deleteHelpers();
		super.handleDied();
	}
}
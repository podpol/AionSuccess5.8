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
package ai.worlds.morheim;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("chieftain_muhamurru")
public class ChieftainMuhamurruAI2 extends AggressiveNpcAI2
{
	private Future<?> hideTask;
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	public void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isHome.compareAndSet(true, false)) {
			sendMsg(1500397);
			startHideTask();
		}
	}
	
	private void cancelPhaseTask() {
		if (hideTask != null && !hideTask.isDone()) {
			hideTask.cancel(true);
		}
	}
	
	private void startHideTask() {
		hideTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelPhaseTask();
				} else {
					SkillEngine.getInstance().getSkill(getOwner(), 19660, 60, getOwner()).useNoAnimationSkill();
					sendMsg(1500398);
					startEvent(2000, 1500399, 19661);
					startEvent(6000, 1500399, 19661);
					startEvent(8000, 1500400, 19662);
				}
			}
		}, 14000, 14000);
	}
	
	private void startEvent(int time, final int msg, final int skill) {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				if (!isAlreadyDead() && !isHome.get()) {
					Creature target = getOwner();
					if (skill == 19661) {
						VisibleObject npcTarget = target.getTarget();
						if (npcTarget != null && npcTarget instanceof Creature) {
							target = (Creature) npcTarget;
						}
					} if (target != null && isInRange(target, 5)) {
						SkillEngine.getInstance().getSkill(getOwner(), skill, 60, target).useNoAnimationSkill();
					}
					getEffectController().removeEffect(19660);
					sendMsg(msg);
				}
			}
		}, time);
	}
	
	private void sendMsg(int msg) {
		NpcShoutsService.getInstance().sendMsg(getOwner(), msg, getObjectId(), 0, 0);
	}
	
	@Override
	protected void handleDied() {
		cancelPhaseTask();
		sendMsg(1500401);
		super.handleDied();
	}
	
	@Override
	protected void handleDespawned() {
		cancelPhaseTask();
		super.handleDespawned();
	}
	
	@Override
	protected void handleBackHome() {
		getEffectController().removeEffect(19660);
		cancelPhaseTask();
		isHome.set(true);
		super.handleBackHome();
	}
}
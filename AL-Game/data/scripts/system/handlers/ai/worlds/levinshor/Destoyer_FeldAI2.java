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
package ai.worlds.levinshor;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("destoyer_feld")
public class Destoyer_FeldAI2 extends AggressiveNpcAI2
{
	private Future<?> task;
	private AtomicBoolean isStart = new AtomicBoolean(false);
	private AtomicBoolean isStart85Event = new AtomicBoolean(false);
	private AtomicBoolean isStart65Event = new AtomicBoolean(false);
	private AtomicBoolean isStart45Event = new AtomicBoolean(false);
	private AtomicBoolean isStart25Event = new AtomicBoolean(false);
	
	@Override
	public void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isStart.compareAndSet(false, true)) {
			task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						SkillEngine.getInstance().getSkill(getOwner(), 19348, 60, getOwner()).useNoAnimationSkill();
						SkillEngine.getInstance().getSkill(getOwner(), 19512, 1, getOwner()).useNoAnimationSkill();
					}
				}
			}, 20000, 50000);
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 85) {
			if (isStart85Event.compareAndSet(false, true)) {
				buff();
			}
		} if (hpPercentage <= 65) {
			if (isStart65Event.compareAndSet(false, true)) {
				buff();
			}
		} if (hpPercentage <= 45) {
			if (isStart45Event.compareAndSet(false, true)) {
				buff();
			}
		} if (hpPercentage <= 25) {
			if (isStart25Event.compareAndSet(false, true)) {
				buff();
			}
		}
	}
	
	private void buff() {
		SkillEngine.getInstance().getSkill(getOwner(), 19511, 60, getOwner()).useNoAnimationSkill();
	}
	
	@Override
	public void handleDied() {
		cancelTask();
		super.handleDied();
	}
	
	private void cancelTask() {
		if (task != null && !task.isDone()) {
			task.cancel(true);
		}
	}
	
	@Override
	protected void handleDespawned() {
		cancelTask();
		super.handleDespawned();
	}
	
	@Override
	public void handleBackHome() {
		cancelTask();
		super.handleBackHome();
		isStart85Event.set(false);
		isStart65Event.set(false);
		isStart45Event.set(false);
		isStart25Event.set(false);
		isStart.set(false);
	}
}
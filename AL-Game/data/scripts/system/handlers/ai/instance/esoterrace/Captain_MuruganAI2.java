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
package ai.instance.esoterrace;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("captain_murugan")
public class Captain_MuruganAI2 extends AggressiveNpcAI2
{
	private Future<?> task;
	private Future<?> specialSkillTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			getPosition().getWorldMapInstance().getDoors().get(70).setOpen(false);
			startTaskEvent();
		}
	}
	
	private void startTaskEvent() {
		VisibleObject target = getTarget();
		if (target != null && target instanceof Player) {
			SkillEngine.getInstance().getSkill(getOwner(), 19324, 1, target).useNoAnimationSkill();
		}
		task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelTask();
				} else {
					//I'll get rid of the cursed ones first!
					sendMsg(1500193, getObjectId(), false, 0);
					SkillEngine.getInstance().getSkill(getOwner(), 19325, 1, getOwner()).useNoAnimationSkill();
					if (getLifeStats().getHpPercentage() <= 50) {
						specialSkillTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
							@Override
							public void run() {
								if (!isAlreadyDead()) {
									//I'll get rid of the cursed ones first!
									sendMsg(1500193, getObjectId(), false, 0);
									VisibleObject target = getTarget();
									if (target != null && target instanceof Player) {
										SkillEngine.getInstance().getSkill(getOwner(), 19324, 1, target).useNoAnimationSkill();
									}
									specialSkillTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
										@Override
										public void run() {
											if (!isAlreadyDead()) {
												VisibleObject target = getTarget();
												if (target != null && target instanceof Player) {
													SkillEngine.getInstance().getSkill(getOwner(), 19324, 1, target).useNoAnimationSkill();
												}
											}
										}
									}, 4000);
								}
							}
						}, 10000);
					}
				}
			}
		}, 20000, 20000);
	}
	
	private void cancelTask() {
		if (task != null && !task.isDone()) {
			task.cancel(true);
		}
	}
	
	private void cancelSpecialSkillTask() {
		if (specialSkillTask != null && !specialSkillTask.isDone()) {
			specialSkillTask.cancel(true);
		}
	}
	
	@Override
	protected void handleBackHome() {
		cancelTask();
		cancelSpecialSkillTask();
		super.handleBackHome();
	}
	
	@Override
	protected void handleDespawned() {
		cancelTask();
		cancelSpecialSkillTask();
		super.handleDespawned();
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
	
	@Override
	protected void handleDied() {
		cancelTask();
		cancelSpecialSkillTask();
		//Power is... overflowing...
		sendMsg(1500195, getObjectId(), false, 0);
		//My lord Surama... I.. am... sorry.
		sendMsg(1500194, getObjectId(), false, 5000);
		super.handleDied();
	}
}
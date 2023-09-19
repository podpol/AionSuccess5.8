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
package ai.instance.tiamatStronghold;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("adjutantanuhart")
public class AdjutantAnuhartAI2 extends AggressiveNpcAI2
{
	
	private Future<?> bladeStormTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private List<Integer> percents = new ArrayList<Integer>();
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			startBladeStormTask();
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void startBladeStormTask() {
		bladeStormTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelTask();
				} else {
					startBladeStormEvent();
				}
			}
		}, 5000, 40000);
	}
	
	private void startBladeStormEvent() {
		shield();
		SkillEngine.getInstance().getSkill(getOwner(), 20747, 1, getOwner()).useNoAnimationSkill();
		spawn(283099, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Blade Storm.
	}
	
	private void cancelTask() {
		if (bladeStormTask != null && !bladeStormTask.isCancelled()) {
			bladeStormTask.cancel(true);
		}
	}
	
	private void checkPercentage(int hpPercentage) {
		for (Integer percent : percents) {
			if (hpPercentage <= percent) {
				switch (percent) {
					case 90:
						swiftAttack(20940);
						startBladeStormTask();
					break;
					case 70:
						swiftAttack(20939);
					break;
					case 50:
						swiftAttack(20938);
						startBladeStormTask();
					break;
					case 30:
						swiftAttack(20940);
					break;
					case 20:
						swiftAttack(20939);
						startBladeStormTask();
					break;
					case 10:
						swiftAttack(20938);
					break;
				}
				percents.remove(percent);
				break;
			}
		}
	}
	
	private void swiftAttack(int buff) {
		AI2Actions.targetSelf(this);
		AI2Actions.useSkill(this, buff);
	}
	
	private void shield() {
		AI2Actions.targetSelf(this);
		AI2Actions.useSkill(this, 20749);
	}
	
	private void addPercent() {
		percents.clear();
		Collections.addAll(percents, new Integer[]{90, 70, 50, 30, 20, 10});
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		addPercent();
	}
	
	@Override
	protected void handleBackHome() {
		addPercent();
		super.handleBackHome();
		cancelTask();
	}
	
	@Override
	protected void handleDespawned() {
		super.handleDespawned();
		cancelTask();
	}
	
	@Override
	protected void handleDied() {
		cancelTask();
		percents.clear();
		super.handleDied();
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
}
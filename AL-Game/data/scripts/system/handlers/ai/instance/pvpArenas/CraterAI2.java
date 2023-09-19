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
package ai.instance.pvpArenas;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("crater")
public class CraterAI2 extends AggressiveNpcAI2
{
	private Future<?> eventTask;
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		startEventTask();
	}
	
	@Override
	protected void handleDied() {
		cancelEventTask();
		super.handleDied();
	}
	
	@Override
	protected void handleDespawned() {
		cancelEventTask();
		super.handleDespawned();
	}
	
	private void cancelEventTask() {
		if (eventTask != null &&
		   !eventTask.isDone()) {
			eventTask.cancel(true);
		}
	}
	
	private void startEventTask() {
		eventTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelEventTask();
				} else {
					SkillEngine.getInstance().getSkill(getOwner(), 20070, 1, getOwner()).useNoAnimationSkill();
				}
			}
		}, 1000, 1000);
	}
}
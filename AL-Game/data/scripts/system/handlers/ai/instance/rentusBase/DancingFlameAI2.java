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
package ai.instance.rentusBase;

import ai.GeneralNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.poll.AIAnswer;
import com.aionemu.gameserver.ai2.poll.AIAnswers;
import com.aionemu.gameserver.ai2.poll.AIQuestion;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("dancing_flame")
public class DancingFlameAI2 extends GeneralNpcAI2
{
	private Future<?> task;
	
	private void startTask() {
		task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelTask();
				} else {
					if (isPlayerInRange()) {
						WorldPosition p = getPosition();
						if (getNpcId() == 282996) {
							spawn(282998, p.getX(), p.getY(), p.getZ(), p.getHeading());
						} else {
							spawn(282999, p.getX(), p.getY(), p.getZ(), p.getHeading());
						}
					}
				}
			}
		}, 3000, 3000);
	}
	
	private boolean isPlayerInRange() {
		for (Player player : getKnownList().getKnownPlayers().values()) {
			if (isInRange(player, 30)) {
				return true;
			}
		}
		return false;
	}
	
	private void cancelTask() {
		if (task != null && !task.isDone())  {
			task.cancel(true);
		}
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		if (getNpcId() == 282996 || getNpcId() == 282997) {
			startTask();
		} else {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					SkillEngine.getInstance().getSkill(getOwner(), getNpcId() == 282998 ? 20536 : 20535, 60, getOwner()).useNoAnimationSkill();
				}
			}, 500);
			starLifeTask();
		}
	}
	
	private void starLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				despawn();
			}
		}, 4000);
	}
	
	private void despawn() {
		if (!isAlreadyDead()) {
			AI2Actions.deleteOwner(this);
		}
	}
	
	@Override
	protected void handleDespawned() {
		super.handleDespawned();
		cancelTask();
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		cancelTask();
	}
	
	@Override
	public AIAnswer ask(AIQuestion question) {
		switch (question) {
			case CAN_ATTACK_PLAYER:
				return AIAnswers.POSITIVE;
			default:
				return AIAnswers.NEGATIVE;
		}
	}
}
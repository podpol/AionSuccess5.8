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
package ai.instance.steelRake;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.manager.EmoteManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.actions.NpcActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("goldeneyemantutu")
public class GoldenEyeMantutuAI2 extends AggressiveNpcAI2
{
	private Future<?> hungerTask;
	private boolean canThink = true;
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	@Override
	protected void handleCustomEvent(int eventId, Object... args) {
		if (eventId == 1 && args != null) {
			canThink = false;
			getMoveController().abortMove();
			EmoteManager.emoteStopAttacking(getOwner());
			Npc npc = (Npc) args[0];
			getOwner().setTarget(npc);
			setStateIfNot(AIState.FOLLOWING);
			getMoveController().moveToTargetObject();
			getOwner().setState(1);
			PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
		}
	}
	
	@Override
	protected void handleMoveArrived() {
		super.handleMoveArrived();
		if (!canThink) {
			VisibleObject target = getTarget();
			getMoveController().abortMove();
			if (target != null && target.isSpawned() && target instanceof Npc) {
				Npc npc = (Npc) target;
				int npcId = npc.getNpcId();
				if (npcId == 281128 || npcId == 281129) {
					startFeedTime(npc);
				}
			}
		}
	}
	
	private void startFeedTime(final Npc npc) {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				if (!isAlreadyDead() && npc != null) {
					switch (npc.getNpcId()) {
						case 281128: //Feed Supply Device.
							getEffectController().removeEffect(20489);
							spawn(701386, 716.508f, 508.571f, 939.607f, (byte) 119);
						break;
						case 281129: //Water Supply Device.
							spawn(701387, 716.389f, 494.207f, 939.607f, (byte) 119);
							getEffectController().removeEffect(20490);
						break;
					}
					NpcActions.delete(npc);
					canThink = true;
					Creature creature = getAggroList().getMostHated();
					if (creature == null || creature.getLifeStats().isAlreadyDead() || !getOwner().canSee(creature)) {
						setStateIfNot(AIState.FIGHT);
						think();
					} else {
						getOwner().setTarget(creature);
						getOwner().getGameStats().renewLastAttackTime();
						getOwner().getGameStats().renewLastAttackedTime();
						getOwner().getGameStats().renewLastChangeTargetTime();
						getOwner().getGameStats().renewLastSkillTime();
						setStateIfNot(AIState.FIGHT);
						handleMoveValidate();
					}
				}
			}
		}, 6000);
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isHome.compareAndSet(true, false)) {
			doSchedule();
		}
	}
	
	@Override
	protected void handleDespawned() {
		cancelHungerTask();
		super.handleDespawned();
	}
	
	@Override
	protected void handleDied() {
		cancelHungerTask();
		Npc npc = getPosition().getWorldMapInstance().getNpc(215079);
		if (npc != null && !NpcActions.isAlreadyDead(npc)) {
			npc.getEffectController().removeEffect(18189);
		}
		super.handleDied();
	}
	
	@Override
	protected void handleBackHome() {
		cancelHungerTask();
		getEffectController().removeEffect(20489);
		getEffectController().removeEffect(20490);
		canThink = true;
		isHome.set(true);
		super.handleBackHome();
	}
	
	private void doSchedule() {
		hungerTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int rnd = Rnd.get(1, 2);
				int skill = 0;
				switch (rnd) {
					case 1:
						skill = 20489; //Hunger.
					break;
					case 2:
						skill = 20490; //Thirst.
					break;
				}
				SkillEngine.getInstance().getSkill(getOwner(), skill, 20, getOwner()).useNoAnimationSkill();
			}
		}, 10000, 30000);
	}
	
	private void cancelHungerTask() {
		if (hungerTask != null && !hungerTask.isDone()) {
			hungerTask.cancel(true);
		}
	}
}
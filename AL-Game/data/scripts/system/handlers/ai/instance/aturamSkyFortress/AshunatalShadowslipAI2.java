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
package ai.instance.aturamSkyFortress;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("ashunatal_shadowslip")
public class AshunatalShadowslipAI2 extends AggressiveNpcAI2
{
	private boolean isSummoned;
	private boolean canThink = true;
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isHome.compareAndSet(true, false)) {
			getPosition().getWorldMapInstance().getDoors().get(2).setOpen(true);
			getPosition().getWorldMapInstance().getDoors().get(17).setOpen(true);
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 80 && !isSummoned) {
			isSummoned = true;
			SkillEngine.getInstance().getSkill(getOwner(), 19428, 1, getOwner()).useNoAnimationSkill();
			doSchedule();
		}
	}
	
	@Override
	protected void handleBackHome() {
		isHome.set(true);
		isSummoned = false;
		super.handleBackHome();
		getPosition().getWorldMapInstance().getDoors().get(17).setOpen(true);
		getPosition().getWorldMapInstance().getDoors().get(2).setOpen(false);
		Npc npc = getPosition().getWorldMapInstance().getNpc(219186);
		if (npc != null) {
			npc.getController().onDelete();
		}
	}
	
	/**
	 * Security:
	 * if Ashunatal Die!!!!
	 */
	@Override
	protected void handleDied() {
		super.handleDied();
		getPosition().getWorldMapInstance().getDoors().get(17).setOpen(true);
	}
	
	private void despawn() {
		AI2Actions.deleteOwner(this);
	}
	
	private void doSchedule() {
		if (!isAlreadyDead()) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						//Ashunatal has retreated to another room. Hunt her down!
						NpcShoutsService.getInstance().sendMsg(getOwner(), 1401391, 0);
						SkillEngine.getInstance().getSkill(getOwner(), 19417, 49, getOwner()).useNoAnimationSkill();
						ThreadPoolManager.getInstance().schedule(new Runnable() {
							@Override
							public void run() {
								if (!isAlreadyDead()) {
									WorldPosition p = getPosition();
									spawn(219186, p.getX(), p.getY(), p.getZ(), p.getHeading());
									canThink = false;
									getSpawnTemplate().setWalkerId("3002400001");
									setStateIfNot(AIState.WALKING);
									think();
									getOwner().setState(1);
									PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
									ThreadPoolManager.getInstance().schedule(new Runnable() {
										@Override
										public void run() {
											if (!isAlreadyDead()) {
												despawn();
											}
										}
									}, 4000);
								}
							}
						}, 3000);
					}
				}
			}, 2000);
		}
	}
}
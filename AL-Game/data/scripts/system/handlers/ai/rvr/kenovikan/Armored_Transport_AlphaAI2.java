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
package ai.rvr.kenovikan;

import ai.GeneralNpcAI2;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Armored_Transport_Alpha")
public class Armored_Transport_AlphaAI2 extends GeneralNpcAI2
{
    private boolean canThink = true;
	private String walkerId = "220110001";
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	private void removeF6RewardTrans() {
   		getOwner().getEffectController().removeEffect(17774);
 	}
	
	private void F6RewardTrans() {
   		SkillEngine.getInstance().getSkill(getOwner(), 17774, 1, getOwner()).useNoAnimationSkill();
 	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			if (MathUtil.getDistance(getOwner(), player) <= 100) {
				if (startedEvent.compareAndSet(false, true)) {
					canThink = false;
					getSpawnTemplate().setWalkerId("220110001");
					WalkManager.startWalking(this);
					getOwner().setState(1);
					PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
				}
			}
		}
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 246463:
				    announceF6RaidSumAtta01Light();
				break;
			}
		}
	}
	
	private void announceF6RaidSumAtta01Light() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The enemy is retrieving our fragment.
					//Destroy the enemy's carrier and stop them from taking the fragment!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404264));
				}
			}
		});
	}
	private void announceF6RaidSumKill01DarkDie() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Armored Transport Alpha was destroyed, and some users were given a special effect.
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404267));
				}
			}
		});
	}
	
	@Override
	protected void handleSpawned() {
  		F6RewardTrans();
		super.handleSpawned();
	}
	
	@Override
	protected void handleBackHome() {
		F6RewardTrans();
		super.handleBackHome();
	}
	
	@Override
	protected void handleDied() {
		removeF6RewardTrans();
		announceF6RaidSumKill01DarkDie();
		super.handleDied();
	}
}
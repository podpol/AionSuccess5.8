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
package ai.worlds.tiamaranta_eye;

import ai.AggressiveNpcAI2;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("sunayaka")
public class SunayakaAI2 extends AggressiveNpcAI2
{
	private boolean canThink = true;
	private Future<?> sunayakaRageTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	private void simmeringRage() {
   		SkillEngine.getInstance().getSkill(getOwner(), 20651, 1, getOwner()).useNoAnimationSkill(); //Simmering Rage.
   		getOwner().getEffectController().removeEffect(8763);
 	}
	
 	private void rageOfTheDragonLords() {
   		SkillEngine.getInstance().getSkill(getOwner(), 8763, 1, getOwner()).useNoAnimationSkill(); //Rage Of The Dragon Lords
 	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 218553: //Governor Sunayaka.
				case 219311: //Berserker Sunayaka.
				//////////////////////////////////
				case 249144: //Governor Sunayaka.
				case 249145: //Berserker Sunayaka.
					//Berserker Sunayaka goes berserk 15 minutes after the battle starts.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1401459, 0);
					getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player player) {
							if (player.isOnline()) {
								PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 900)); //15 Minutes.
							}
						}
					});
					sunayakaRageTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							//Berserker Sunayaka has gone berserk.
							NpcShoutsService.getInstance().sendMsg(getOwner(), 1401460, 0);
							getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
								@Override
								public void visit(Player player) {
									simmeringRage();
									if (player.isOnline()) {
										PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
									}
								}
							});
						}
					}, 900000); //15Min...
				break;
			}
		}
	}
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	private void cancelSunayakaRageTask() {
		if (sunayakaRageTask != null && !sunayakaRageTask.isDone()) {
			sunayakaRageTask.cancel(true);
		}
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
  		rageOfTheDragonLords();
	}
	
	@Override
	protected void handleDespawned() {
		cancelSunayakaRageTask();
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
				}
			}
		});
		super.handleDespawned();
	}
	
	@Override
	protected void handleBackHome() {
		canThink = true;
		isAggred.set(false);
		cancelSunayakaRageTask();
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
				}
			}
		});
		super.handleBackHome();
	}
	
	@Override
	protected void handleDied() {
		cancelSunayakaRageTask();
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
				}
			}
		});
		super.handleDied();
	}
}
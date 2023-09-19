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
package com.aionemu.gameserver.controllers;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.ObserverType;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.actions.PlayerMode;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.skillengine.effect.AbnormalState;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.audit.AuditLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ATracer
 */
public class FlyController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(FlyController.class);

	private static final long FLY_REUSE_TIME = 10000;
	private Player player;
	private ActionObserver glideObserver = new ActionObserver(ObserverType.ABNORMALSETTED) {

		public void abnormalsetted(AbnormalState state) {
			if ((state.getId() & AbnormalState.CANT_MOVE_STATE.getId()) > 0 && !player.isInvulnerableWing())
				player.getFlyController().onStopGliding(true);
		}
	};

	public FlyController(Player player) {
		this.player = player;
	}

	/**
	 * 
	 */
	public void onStopGliding(boolean removeWings) {
		if (player.isInState(CreatureState.GLIDING)) {
			player.unsetState(CreatureState.GLIDING);

			if (player.isInState(CreatureState.FLYING)) {
				player.setFlyState(1);
			}
			else {
				player.setFlyState(0);
				player.getLifeStats().triggerFpRestore();
				if (removeWings)
					PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.LAND, 0, 0), true);
			}

			player.getObserveController().removeObserver(glideObserver);
			player.getGameStats().updateStatsAndSpeedVisually();
		}
	}

	/**
	 * Ends flying 1) by CM_EMOTION (pageDown or fly button press) 2) from server side during teleportation (abyss gates
	 * should not break flying) 3) when FP is decreased to 0
	 */
	public void endFly(boolean forceEndFly) {
		if (player.isInState(CreatureState.FLYING) || player.isInState(CreatureState.GLIDING)) {
			player.unsetState(CreatureState.FLYING);
			player.unsetState(CreatureState.GLIDING);
			player.unsetState(CreatureState.FLOATING_CORPSE);
			player.setFlyState(0);

			// this is probably needed to change back fly speed into speed.
			// TODO remove this and just send in update?
			PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_EMOTE2, 0, 0), true);
			if (forceEndFly) {
				PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.LAND, 0, 0), true);
			}

			player.getObserveController().removeObserver(glideObserver);
			player.getGameStats().updateStatsAndSpeedVisually();

			player.getLifeStats().triggerFpRestore();
		}
	}

	/**
	 * This method is called to start flying (called by CM_EMOTION when pageUp or pressed fly button)
	 */
	public void startFly() {
		if (player.getFlyReuseTime() > System.currentTimeMillis()) {
			AuditLogger.info(player,
				"No Flight Cooldown Hack. Reuse time: " + ((player.getFlyReuseTime() - System.currentTimeMillis()) / 1000));
			return;
		}
		player.setFlyReuseTime(System.currentTimeMillis() + FLY_REUSE_TIME);
		player.setState(CreatureState.FLYING);
		if (player.isInPlayerMode(PlayerMode.RIDE)) {
			player.setState(CreatureState.FLOATING_CORPSE);
		}
		player.setFlyState(1);
		player.getLifeStats().triggerFpReduce();
		// TODO remove it?
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_EMOTE2, 0, 0), true);
		player.getGameStats().updateStatsAndSpeedVisually();
	}

	/**
	 * Switching to glide mode (called by CM_MOVE with VALIDATE_GLIDE movement type) 1) from standing state 2) from flying
	 * state If from stand to glide - start fp reduce + emotions/stats if from fly to glide - only emotions/stats
	 */
	public boolean switchToGliding() {
		if (!player.isInState(CreatureState.GLIDING) && player.canPerformMove()) {
			if (player.getFlyReuseTime() > System.currentTimeMillis()) {
				return false;
			}
			player.setFlyReuseTime(System.currentTimeMillis() + FLY_REUSE_TIME);
			player.setState(CreatureState.GLIDING);
			if (player.getFlyState() == 0)
				player.getLifeStats().triggerFpReduce();
			player.setFlyState(2);

			player.getObserveController().addObserver(glideObserver);
			player.getGameStats().updateStatsAndSpeedVisually();
		}
		return true;
	}
}

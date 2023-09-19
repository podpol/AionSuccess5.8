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
package com.aionemu.gameserver.model.actions;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.player.InRoll;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.templates.ride.RideInfo;
import com.aionemu.gameserver.model.templates.windstreams.WindstreamPath;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Rinzler (Encom)
/****/

public class PlayerActions extends CreatureActions
{
	public static boolean isInPlayerMode(Player player, PlayerMode mode) {
		switch (mode) {
			case RIDE:
				return player.ride != null;
			case IN_ROLL:
				return player.inRoll != null;
			case WINDSTREAM:
				return player.windstreamPath != null;
		}
		return false;
	}
	
	public static void setPlayerMode(Player player, PlayerMode mode, Object obj) {
		switch (mode) {
			case RIDE:
				player.ride = (RideInfo) obj;
			break;
			case IN_ROLL:
				player.inRoll = (InRoll) obj;
			break;
			case WINDSTREAM:
				player.windstreamPath = (WindstreamPath) obj;
			break;	
		}
	}
	
	public static boolean unsetPlayerMode(Player player, PlayerMode mode) {
		switch (mode) {
			case RIDE:
				RideInfo ride = player.ride;
				if (ride == null) {
					return false;
				} if (player.isInSprintMode()) {
					player.getLifeStats().triggerFpRestore();
					player.setSprintMode(false);
				}
				player.unsetState(CreatureState.RESTING);
				player.unsetState(CreatureState.FLOATING_CORPSE);
				player.setState(CreatureState.ACTIVE);
				player.ride = null;
				PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_EMOTE2, 0, 0), true);
				PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.RIDE_END), true);
				player.getGameStats().updateStatsAndSpeedVisually();
				for (ActionObserver observer : player.getRideObservers()) {
					player.getObserveController().removeObserver(observer);
				}
				player.getRideObservers().clear();
			    return true;
			case IN_ROLL:
				if (player.inRoll == null) {
					return false;
				}
				player.inRoll = null;
				return true;
			case WINDSTREAM:
				if (player.windstreamPath == null) {
					return false;
				}
				player.windstreamPath = null;
				return true;
		}
		return false;
	}
}
/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen,      	    *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package playercommands;

import java.util.concurrent.TimeUnit;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.skillengine.effect.AbnormalState;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Nemiroff Date: 11.01.2010
 * @rework Eloann Date : 12.06.2013
 */
public class cmd_unstuck extends PlayerCommand {

	public cmd_unstuck() {
		super("unstuck");
	}

	@Override
	public void execute(final Player player, String... params) {
		if (player.getLifeStats().isAlreadyDead()) {
			PacketSendUtility.sendMessage(player, "You dont have execute this command. You die");
			return;
		}
		if (player.isInPrison()) {
			PacketSendUtility.sendMessage(player, "You can't use the unstuck command when you are in Prison");
			return;
		}

		PacketSendUtility.sendMessage(player, "You are now freeze for 10 secondes before unstuck.");
		player.getEffectController().setAbnormal(AbnormalState.PARALYZE.getId());
		player.getEffectController().updatePlayerEffectIcons();
		player.getEffectController().broadCastEffects();
		PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), 0, 0, 0, (int) TimeUnit.SECONDS.toMillis(10), 0));
		ThreadPoolManager.getInstance().schedule(new Runnable() {

			@Override
			public void run() {
				player.getEffectController().unsetAbnormal(AbnormalState.PARALYZE.getId());
				player.getEffectController().updatePlayerEffectIcons();
				player.getEffectController().broadCastEffects();
				player.getController().cancelUseItem();
				PacketSendUtility.broadcastPacketAndReceive(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), 0, 0, 0, 0, 1));
				TeleportService2.moveToBindLocation(player, true);
			}
		}, (int) TimeUnit.SECONDS.toMillis(10));
	}

	@Override
	public void onFail(Player player, String message) {
		// TODO Auto-generated method stub
	}
}

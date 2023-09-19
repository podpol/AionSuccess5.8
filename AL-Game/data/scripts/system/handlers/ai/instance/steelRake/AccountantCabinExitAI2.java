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

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("accountant_cabin_exit")
public class AccountantCabinExitAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 730766: //Accountant's Cabin Exit.
				switch (player.getWorldId()) {
					case 300100000: //Steel Rake 1.5
					    if (player.getCommonData().getRace() == Race.ASMODIANS) {
						    PacketSendUtility.sendMessage(player, "you enter <Pandaemonium>");
						    TeleportService2.teleportTo(player, 120010000, 950.000f, 1150.000f, 195.000f, (byte) 60, TeleportAnimation.BEAM_ANIMATION);
			            } else if (player.getCommonData().getRace() == Race.ELYOS) {
						    PacketSendUtility.sendMessage(player, "you enter <Sanctum>");
							TeleportService2.teleportTo(player, 110010000, 1867.000f, 2068.000f, 517.000f, (byte) 60, TeleportAnimation.BEAM_ANIMATION);
						}
			        break;
					case 300460000: //Steel Rake Cabin 3.0
					    if (player.getCommonData().getRace() == Race.ASMODIANS) {
						    PacketSendUtility.sendMessage(player, "you enter <Pandaemonium>");
						    TeleportService2.teleportTo(player, 120010000, 950.000f, 1150.000f, 195.000f, (byte) 60, TeleportAnimation.BEAM_ANIMATION);
			            } else if (player.getCommonData().getRace() == Race.ELYOS) {
						    PacketSendUtility.sendMessage(player, "you enter <Sanctum>");
							TeleportService2.teleportTo(player, 110010000, 1867.000f, 2068.000f, 517.000f, (byte) 60, TeleportAnimation.BEAM_ANIMATION);
						}
			        break;
				}
		    break;
		}
	}
}
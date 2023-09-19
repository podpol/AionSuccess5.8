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
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("groggetssafedoor")
public class GroggetsSafeDoorAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 730199: //Groggets Safe Door.
				switch (player.getWorldId()) {
                    case 300100000: //Steel Rake.
						if (player.getInventory().decreaseByItemId(185000046, 1)) { //Grogget's Safe Key.
						    PacketSendUtility.sendMessage(player, "you enter <Inside Steel Rake>");
							TeleportService2.teleportTo(player, 300100000, 702.11993f, 500.80948f, 939.60675f, (byte) 0);
						} else {
							PacketSendUtility.sendMessage(player, "you must have <Grogget's Safe Key> for use this teleporter");
						}
			        break;
				} switch (player.getWorldId()) {
                    case 300460000: //Steel Rake Cabin 3.0
					    PacketSendUtility.sendMessage(player, "you enter <Inside Steel Rake Cabin>");
						TeleportService2.teleportTo(player, 300460000, 702.11993f, 500.80948f, 939.60675f, (byte) 0);
			        break;
				}
		    break;
		}
	}
}
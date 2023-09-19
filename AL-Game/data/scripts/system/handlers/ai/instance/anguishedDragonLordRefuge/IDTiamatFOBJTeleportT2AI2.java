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
package ai.instance.anguishedDragonLordRefuge;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("blood_red_jewel")
public class IDTiamatFOBJTeleportT2AI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 730625: //Blood Red Jewel.
				switch (player.getWorldId()) {
					case 300520000: //Dragon Lord's Refuge 3.9
						PacketSendUtility.sendMessage(player, "you enter <Dragon Lord's Refuge 3.9>");
						TeleportService2.teleportTo(player, 300520000, 512.75183f, 515.7632f, 417.40436f, (byte) 0);
			        break;
					case 300630000: //[Anguished] Dragon Lord's Refuge 4.8
					    PacketSendUtility.sendMessage(player, "you enter <[Anguished] Dragon Lord's Refuge 4.8>");
						TeleportService2.teleportTo(player, 300630000, 512.75183f, 515.7632f, 417.40436f, (byte) 0);
			        break;
				}
		    break;
		}
	}
}
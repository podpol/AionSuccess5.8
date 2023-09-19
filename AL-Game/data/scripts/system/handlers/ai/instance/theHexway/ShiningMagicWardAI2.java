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
package ai.instance.theHexway;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("shiningmagicward")
public class ShiningMagicWardAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 700455: //Shining Magic Ward.
				switch (player.getWorldId()) {
                    case 300080000: //Left Wing Chamber.
					    if (player.getCommonData().getRace() == Race.ASMODIANS) {
						   PacketSendUtility.sendMessage(player, "you enter <Primum Landing>");
						   TeleportService2.teleportTo(player, 400010000, 1071.7615f, 2851.7764f, 1636.0677f, (byte) 38);
			            } else if (player.getCommonData().getRace() == Race.ELYOS) {
						   PacketSendUtility.sendMessage(player, "you enter <Terminon Landing>");
						   TeleportService2.teleportTo(player, 400010000, 2872.6626f, 1029.0958f, 1527.9968f, (byte) 103);
					    }
			        break;
				} switch (player.getWorldId()) {
                    case 300700000: //The Hexway 4.3.
					    PacketSendUtility.sendMessage(player, "you enter in <Silentera Canyon [Master Server]>");
						TeleportService2.teleportTo(player, 600110000, 528.7647f, 766.7518f, 299.61633f, (byte) 1);
			        break;
				}
		    break;
		}
	}
}
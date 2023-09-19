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
package ai.worlds.theobomos;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("dried_out_vine")
public class Dried_Out_VineAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		if (player.getLevel() >= 45) {
		    switch (getNpcId()) {
			    case 730169: //Dried-Out Vine.
				    TeleportService2.teleportTo(player, 210060000, 2456.042f, 2388.615f, 32.53795f, (byte) 31, TeleportAnimation.BEAM_ANIMATION);
				break;
				case 730170: //Dried-Out Vine.
				    TeleportService2.teleportTo(player, 210060000, 2841.572f, 2500.023f, 40.20959f, (byte) 39, TeleportAnimation.BEAM_ANIMATION);
				break;
				case 730171: //Dried-Out Vine.
				    TeleportService2.teleportTo(player, 210060000, 1971.371f, 2676.447f, 61.50000f, (byte) 49, TeleportAnimation.BEAM_ANIMATION);
				break;
				case 730172: //Dried-Out Vine.
				    TeleportService2.teleportTo(player, 210060000, 2254.681f, 2839.995f, 58.37074f, (byte) 46, TeleportAnimation.BEAM_ANIMATION);
				break;
				case 730173: //Dried-Out Vine.
				    TeleportService2.teleportTo(player, 210060000, 2674.758f, 2947.456f, 37.47572f, (byte) 44, TeleportAnimation.BEAM_ANIMATION);
				break;
			}
        } else {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
		}
	}
}
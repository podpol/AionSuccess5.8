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
package ai.portals;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("refuge_corridor")
public class Refuge_CorridorAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		if (player.getLevel() >= 65) {
		    PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
		} else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
			//Only players level 65 or over can enter.
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_Fortress_Entrance_In01);
        }
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (player.getPlayerGroup2() == null) {
			//Unavailable to use when you're alone.
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_Fortress_Entrance_In02);
			return true;
		} if (dialogId == 10000) {
			switch (getNpcId()) {
			    case 805613: //Krotan Refuge Corridor [Elyos]
			    case 805627: //Krotan Refuge Corridor [Asmodians]
				    TeleportService2.teleportTo(player, 400010000, 1425.239f, 1006.4445f, 2976.326f, (byte) 99);
			    break;
			    case 805614: //Miren Refuge Corridor [Elyos]
			    case 805628: //Miren Refuge Corridor [Asmodians]
				    TeleportService2.teleportTo(player, 400010000, 1164.388f, 2019.7821f, 2943.519f, (byte) 80);
			    break;
			    case 805615: //Kysis Refuge Corridor [Elyos]
			    case 805629: //Kysis Refuge Corridor [Asmodians]
				    TeleportService2.teleportTo(player, 400010000, 1740.073f, 1702.1339f, 3025.284f, (byte) 1);
			    break;
			}
        }
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
}
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
package ai.instance.engulfedOphidanBridge;

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

@AIName("archon_of_dusk_rearguard")
public class Archon_Of_Dusk_RearguardAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(164000279) != null) { //Advance Route Teleport Scroll.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            //You need an Advance Route Teleport Scroll.
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402004));
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
        }
    }
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(164000279, 1)) { //Advance Route Teleport Scroll.
		    TeleportService2.teleportTo(player, 301210000, instanceId, 692.0453f, 485.75558f, 599.91113f, (byte) 71);
		} if (dialogId == 10001 && player.getInventory().decreaseByItemId(164000279, 1)) { //Advance Route Teleport Scroll.
			TeleportService2.teleportTo(player, 301210000, instanceId, 535.967f, 458.2419f, 619.88727f, (byte) 85);
		} if (dialogId == 10002 && player.getInventory().decreaseByItemId(164000279, 1)) { //Advance Route Teleport Scroll.
		    TeleportService2.teleportTo(player, 301210000, instanceId, 597.6529f, 571.45654f, 590.91034f, (byte) 100);
		} if (dialogId == 10003 && player.getInventory().decreaseByItemId(164000279, 1)) { //Advance Route Teleport Scroll.
		    TeleportService2.teleportTo(player, 301210000, instanceId, 475.34265f, 522.1061f, 597.375f, (byte) 15);
		} else if (dialogId == 1352) {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1352));
        }
		return true;
	}
}
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
package ai.instance.seizedDanuarSanctuary;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("charnel")
public class TheCharnelEntranceAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(185000183) != null) { //The Charnels Key.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
        }
    }
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		switch (getNpcId()) {
		    case 701871: //The Charnel Entrance.
		        switch (player.getWorldId()) {
		            case 301140000: //Seized Danuar Sanctuary 4.8
				        if (dialogId == 10000 && player.getInventory().decreaseByItemId(185000183, 1)) { //The Charnels Key.
			                TeleportService2.teleportTo(player, 301140000, instanceId, 1005.93787f, 1366.8308f, 337.25f, (byte) 113);
					    }
				    break;
			    } switch (player.getWorldId()) {
				    case 301380000: //Danuar Sanctuary 4.8
					    if (dialogId == 10000 && player.getInventory().decreaseByItemId(185000183, 1)) { //The Charnels Key.
					        TeleportService2.teleportTo(player, 301380000, instanceId, 1005.93787f, 1366.8308f, 337.25f, (byte) 113);
						}
					break;
				}
			break;
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
}
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
package ai.instance.crucibleChallenge;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("arbiter")
public class ArbiterAi2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(186000124) != null) { //Worthiness Ticket.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1097));
        }
    }
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(186000124, 1)) {
			switch (getNpcId()) {
				case 205682:
					TeleportService2.teleportTo(player, 300320000, instanceId, 357.10208f, 1662.702f, 95.9803f, (byte) 60);
				break;
				case 205683:
					TeleportService2.teleportTo(player, 300320000, instanceId, 1796.5513f, 306.9967f, 469.25f, (byte) 60);
				break;
				case 205684:
					TeleportService2.teleportTo(player, 300320000, instanceId, 1324.433f, 1738.2279f, 316.476f, (byte) 70);
				break;
				case 205663:
					TeleportService2.teleportTo(player, 300320000, instanceId, 1270.8877f, 237.93307f, 405.38028f, (byte) 60);
				break;
				case 205686:
					TeleportService2.teleportTo(player, 300320000, instanceId, 357.98798f, 349.19116f, 96.09108f, (byte) 60);
				break;
				case 205687:
					TeleportService2.teleportTo(player, 300320000, instanceId, 1759.5004f, 1273.5414f, 389.11743f, (byte) 10);
				break;
				case 205685:
					TeleportService2.teleportTo(player, 300320000, instanceId, 1283.1246f, 791.6683f, 436.6403f, (byte) 60);
				break;
			}
		}
		return true;
	}
}
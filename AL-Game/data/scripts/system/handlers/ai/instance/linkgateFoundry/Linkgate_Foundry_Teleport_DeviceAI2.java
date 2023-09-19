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
package ai.instance.linkgateFoundry;

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

@AIName("linkgate_foundry_teleport_device")
public class Linkgate_Foundry_Teleport_DeviceAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getOwner().getObjectId(), 1011));
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		switch (getNpcId()) {
		    case 702591: //Linkgate Foundry Teleport Device.
		        switch (player.getWorldId()) {
		            case 301270000: //Linkgate Foundry 4.7
				        if (dialogId == 10000) {
							//The Secret Lab's location was revealed three times. Nothing remains in the lab and the reserachers have fled.
							PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_IDLDF4_Re_01_secret_room_03, 0);
							TeleportService2.teleportTo(player, 301270000, instanceId, 174.68114f, 258.55096f, 312.1969f, (byte) 93);
					    } else if (dialogId == 10001) {
							//The Secret Lab's location was revealed three times. Nothing remains in the lab and the reserachers have fled.
							PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_IDLDF4_Re_01_secret_room_03, 0);
							TeleportService2.teleportTo(player, 301270000, instanceId, 174.68114f, 258.55096f, 353.1969f, (byte) 93);
					    } else if (dialogId == 10002) {
							//The Secret Lab's location was revealed three times. Nothing remains in the lab and the reserachers have fled.
							PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_IDLDF4_Re_01_secret_room_03, 0);
							TeleportService2.teleportTo(player, 301270000, instanceId, 174.68114f, 258.55096f, 393.1969f, (byte) 93);
					    }
				    break;
			    }
			break;
		}
		return true;
	}
}
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
package ai.instance.fissureOfOblivion;

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

@AIName("Teleport_Stone")
public class Teleport_StoneAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		if (player.getLevel() >= 66) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getOwner().getObjectId(), 1011));
		} else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
        }
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		switch (getNpcId()) {
		    case 834188: //Orkia Aetheric Field Observatory Square Teleport Stone.
		        switch (player.getWorldId()) {
		            case 302100000: //Fissure Of Oblivion 5.1
				        if (dialogId == 104) { //Teleport to the Orkia Observatory.
							TeleportService2.teleportTo(player, 302100000, instanceId, 594.65173f, 561.58746f, 352.84213f, (byte) 9);
					    }
				    break;
					case 302110000: //IDTransform Event 5.6
				        if (dialogId == 104) { //Teleport to the Orkia Observatory.
							TeleportService2.teleportTo(player, 302110000, instanceId, 594.65173f, 561.58746f, 352.84213f, (byte) 9);
					    }
				    break;
			    }
			break;
			case 834189: //Fallen Orkia Fortress Teleport Stone.
		        switch (player.getWorldId()) {
		            case 302100000: //Fissure Of Oblivion 5.1
				        if (dialogId == 104) { //Teleport to the Orkia Ruins.
							TeleportService2.teleportTo(player, 302100000, instanceId, 522.81244f, 575.3527f, 322.02863f, (byte) 54);
					    }
				    break;
					case 302110000: //IDTransform Event 5.6
				        if (dialogId == 104) { //Teleport to the Orkia Ruins.
							TeleportService2.teleportTo(player, 302110000, instanceId, 522.81244f, 575.3527f, 322.02863f, (byte) 54);
					    }
				    break;
			    }
			break;
			case 834190: //Orkia Spire Teleport Stone.
		        switch (player.getWorldId()) {
		            case 302100000: //Fissure Of Oblivion 5.1
				        if (dialogId == 104) { //Teleport to the Orkia Spire.
							TeleportService2.teleportTo(player, 302100000, instanceId, 408.66364f, 513.476f, 342.3292f, (byte) 90);
					    }
				    break;
					case 302110000: //IDTransform Event 5.6
				        if (dialogId == 104) { //Teleport to the Orkia Spire.
							TeleportService2.teleportTo(player, 302110000, instanceId, 408.66364f, 513.476f, 342.3292f, (byte) 90);
					    }
				    break;
			    }
			break;
		}
		return true;
	}
}
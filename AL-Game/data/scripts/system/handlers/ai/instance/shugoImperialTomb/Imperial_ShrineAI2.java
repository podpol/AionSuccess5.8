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
package ai.instance.shugoImperialTomb;

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Imperial_Shrine")
public class Imperial_ShrineAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
        switch (getNpcId()) {
            case 831350: { //Imperial Shrine.
				super.handleDialogStart(player);
				break;
			} default: {
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
				break;
			}
		}
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		QuestEnv env = new QuestEnv(getOwner(), player, questId, dialogId);
		env.setExtendedRewardIndex(extendedRewardIndex);
		if (QuestEngine.getInstance().onDialog(env) && dialogId != 1011) {
			return true;
		} if (dialogId == 10000 && player.getInventory().decreaseByItemId(182006989, 1)) { //Emperor's Golden Tag.
		    TeleportService2.teleportTo(player, 300560000, instanceId, 354.1076f, 188.2339f, 304.3324f, (byte) 110);
		} if (dialogId == 10001 && player.getInventory().decreaseByItemId(182006990, 1)) { //Empress' Silver Tag.
		    TeleportService2.teleportTo(player, 300560000, instanceId, 354.89645f, 39.509903f, 358.38965f, (byte) 38);
		} if (dialogId == 10002 && player.getInventory().decreaseByItemId(182006991, 1)) { //Crown Prince's Brass Tag.
		    TeleportService2.teleportTo(player, 300560000, instanceId, 200.2623f, 61.517563f, 464.48865f, (byte) 11);
		} else if (dialogId == 1011 && questId != 0) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), dialogId, questId));
		}
		return true;
	}
}
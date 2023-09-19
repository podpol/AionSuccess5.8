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

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.autogroup.AutoGroupType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FIND_GROUP;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/** Helper themoose (Encom)
/****/

@AIName("walk_of_honor")
public class Walk_Of_HonorAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
        switch (getNpcId()) {
			case 731193: //Walk Of Honor
			case 731194: { //Walk Of Glory
				super.handleDialogStart(player);
				break;
			} default: {
				if (player.getLevel() >= 65) {
				    PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getOwner().getObjectId(), 10));
				} else {
				    PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
					//Only officers 1-star or higher can enter the fortress.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Telepoter_GAb1_User07);
				}
				break;
			}
		}
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 105) {
			switch (getNpcId()) {
			    case 731193: //Walk Of Honor
				case 731194: //Walk Of Glory
				    AutoGroupType agt = AutoGroupType.getAutoGroup(player.getLevel(), getNpcId());
					if (agt != null) {
					    PacketSendUtility.sendPacket(player, new SM_FIND_GROUP(0x1A, agt.getInstanceMapId()));
					}
				break;
			}
		} else if (dialogId == 10) {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
        }
        return true;
    }
}
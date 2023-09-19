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
package ai.instance.beshmundirTemple;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AI2Request;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.autogroup.AutoGroupType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.portal.PortalPath;
import com.aionemu.gameserver.model.templates.portal.PortalUse;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FIND_GROUP;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUESTION_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.PortalService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("beshmundirswalk")
public class BeshmundirsWalkAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
	}
	
	@Override
	public boolean onDialogSelect(Player player, final int dialogId, int questId, int extendedRewardIndex) {
		AI2Request request = new AI2Request() {
			@Override
			public void acceptRequest(Creature requester, Player responder) {
				moveToInstance(responder);
			}
		};
		switch (dialogId) {
			case 105:
				AutoGroupType agt = AutoGroupType.getAutoGroup(player.getLevel(), getNpcId());
				if (agt != null) {
					PacketSendUtility.sendPacket(player, new SM_FIND_GROUP(0x1A, agt.getInstanceMapId()));
				}
			break;
			case 65:
				if (!player.isInGroup2()) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1390256));
					return true;
				} if (player.getPlayerGroup2().isLeader(player)) {
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 4762));
				} else {
					if (!isAGroupMemberInInstance(player)) {
						PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400361));
						return true;
					}
					moveToInstance(player);
				}
			break;
			case 4763:
				AI2Actions.addRequest(this, player, SM_QUESTION_WINDOW.STR_INSTANCE_DUNGEON_WITH_DIFFICULTY_ENTER_CONFIRM, getObjectId(), request, new DescriptionId(1804103));
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 4762));
			break;
			case 4848:
				AI2Actions.addRequest(this, player, SM_QUESTION_WINDOW.STR_INSTANCE_DUNGEON_WITH_DIFFICULTY_ENTER_CONFIRM, getObjectId(), request, new DescriptionId(1804105));
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 4762));
			break;
		}
		return true;
	}
	
	private boolean isAGroupMemberInInstance(Player player) {
		if (player.isInGroup2()) {
			for (Player member : player.getPlayerGroup2().getMembers()) {
				if (member.getWorldId() == 300170000) { //Beshmundir Temple.
					return true;
				}
			}
		}
		return false;
	}
	
	private void moveToInstance(Player player) {
		PortalUse portalUse = DataManager.PORTAL2_DATA.getPortalUse(getNpcId());
		if (portalUse != null) {
			PortalPath portalPath = portalUse.getPortalPath(player.getRace());
			if (portalPath != null) {
				PortalService.port(portalPath, player, getObjectId());
			}
		}
	}
}
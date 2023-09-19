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
package ai.instance.cradleOfEternity;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Elyos_Elite_Combat_Mage")
public class Elyos_Elite_Combat_MageAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		if (player.isArchDaeva()) {
		    PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
		} else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
        }
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000) {
			switch (getNpcId()) {
				case 220575: //Elyos Elite Combat Mage.
					announce1StDefense();
				break;
				case 220577: //Elyos Elite Combat Mage.
					announce2NdDefense();
				break;
				case 220579: //Elyos Elite Combat Mage.
					announce3RdDefense();
				break;
				case 220581: //Elyos Elite Combat Mage.
					announce4ThDefense();
			    break;
			}
		}
		AI2Actions.deleteOwner(this);
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
	
	private void announce1StDefense() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//As the shields fell, the 1st Defense Line was breached and overrun.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_01, 0);
				//The enemies are coming. Kill them all.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_34, 5000);
			}
		});
	}
	private void announce2NdDefense() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//As the shields fell, the 2nd Defense Line was breached and overrun.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_02, 0);
				//The enemies are coming. Kill them all.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_34, 5000);
			}
		});
	}
	private void announce3RdDefense() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//As the shields fell, the 3rd Defense Line was breached and overrun.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_03, 0);
				//The enemies are coming. Kill them all.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_34, 5000);
			}
		});
	}
	private void announce4ThDefense() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//As the shields fell, the 4th Defense Line was breached and overrun.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_04, 0);
				//The enemies are coming. Kill them all.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_34, 5000);
			}
		});
	}
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
}
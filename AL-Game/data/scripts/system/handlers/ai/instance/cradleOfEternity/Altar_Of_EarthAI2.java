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

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Altar_Of_Earth")
public class Altar_Of_EarthAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		super.handleDialogStart(player);
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		if (!player.getInventory().decreaseByItemId(185000266, 1)) { //Earthen Malachite.
			//You don’t have a Malachite of Earth to place on the altar.
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403447));
			return;
		} switch (getNpcId()) {
			case 834006: //Altar Of Earth.
				//The Malachite of Earth emits a light and starts to float.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_37, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						despawnNpc(834006);
						AI2Actions.deleteOwner(Altar_Of_EarthAI2.this);
						spawn(834006, 1025.1476f, 774.97748f, 1033.6420f, (byte) 0, 291);
				    }
			    }, 5000);
			break;
			case 834019: //Altar Of Earth.
				//The Malachite of Earth emits a light and starts to float.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_37, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						despawnNpc(834019);
						AI2Actions.deleteOwner(Altar_Of_EarthAI2.this);
						spawn(834019, 1027.2802f, 771.84601f, 1033.6420f, (byte) 0, 340);
				    }
			    }, 5000);
			break;
			case 834020: //Altar Of Earth.
				//The Malachite of Earth emits a light and starts to float.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_37, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						despawnNpc(834020);
						AI2Actions.deleteOwner(Altar_Of_EarthAI2.this);
						spawn(834020, 1027.4769f, 777.98260f, 1033.6420f, (byte) 0, 299);
				    }
			    }, 5000);
			break;
			case 834021: //Altar Of Earth.
				//The Malachite of Earth emits a light and starts to float.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_37, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						despawnNpc(834021);
						AI2Actions.deleteOwner(Altar_Of_EarthAI2.this);
						spawn(834021, 1031.0382f, 776.67932f, 1033.6420f, (byte) 0, 387);
				    }
			    }, 5000);
			break;
			case 834022: //Altar Of Earth.
				//The Malachite of Earth emits a light and starts to float.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_IDEternity_02_SYSTEM_MSG_37, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						despawnNpc(834017);
						despawnNpc(834022);
						AI2Actions.deleteOwner(Altar_Of_EarthAI2.this);
						spawn(834022, 1030.9221f, 772.90582f, 1033.6420f, (byte) 0, 395);
						spawn(834091, 974.25085f, 775.06488f, 1027.0811f, (byte) 0, 322);
				    }
			    }, 5000);
			break;
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
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
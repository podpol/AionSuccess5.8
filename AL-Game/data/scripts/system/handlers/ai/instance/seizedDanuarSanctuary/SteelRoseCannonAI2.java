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

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.ChatType;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MESSAGE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("steelrosecannon2")
public class SteelRoseCannonAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		if (!player.getInventory().decreaseByItemId(186000254, 1)) {
			PacketSendUtility.broadcastPacket(player, new SM_MESSAGE(player,
			"You must have <Seal Breaking Magic Cannonball>", ChatType.BRIGHT_YELLOW_CENTER), true);
			return;
		}
		WorldPosition worldPosition = player.getPosition();
		if (worldPosition.isInstanceMap()) {
			//Seized Danuar Sanctuary 4.8
			if (worldPosition.getMapId() == 301140000) {
				//A heavy door has opened somewhere.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDLDF5_Under_02_Canon, 5000);
				SkillEngine.getInstance().getSkill(getOwner(), 21126, 60, getOwner()).useNoAnimationSkill(); //Destroy Seal.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
					public void run() {
					    despawnNpc(233142); //Unyielding Boulder.
					}
				}, 5000);
			}
			//Danuar Sanctuary 4.8
			else if (worldPosition.getMapId() == 301380000) {
				//A heavy door has opened somewhere.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDLDF5_Under_02_Canon, 5000);
				SkillEngine.getInstance().getSkill(getOwner(), 21126, 60, getOwner()).useNoAnimationSkill(); //Destroy Seal.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
					public void run() {
					    despawnNpc(233142); //Unyielding Boulder.
					}
				}, 5000);
			}
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
				AI2Actions.killSilently(this, npc);
			}
		}
	}
}
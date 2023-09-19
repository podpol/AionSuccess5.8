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
package ai.instance.dragonLordRefuge;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import javolution.util.FastMap;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/** Midified Ranastic (Encom)
/****/

@AIName("kahrun2")
public class KahrunAI2 extends NpcAI2
{
	private FastMap<Integer, VisibleObject> portal = new FastMap<Integer, VisibleObject>();
	
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		if (dialogId == 10000) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
			switch (getNpcId()) {
			    case 800429: //Kahrun (Reian Leader).
				    SpawnTemplate template = SpawnEngine.addNewSingleTimeSpawn(300520000, 730625, 503.219757f, 516.651733f, 242.604065f, (byte) 0); //Blood Red Jewel.
				    template.setEntityId(4);
				    portal.put(730625, SpawnEngine.spawnObject(template, instanceId));
				    AI2Actions.deleteOwner(KahrunAI2.this);
				break;
			}
		}
		return true;
	}
}
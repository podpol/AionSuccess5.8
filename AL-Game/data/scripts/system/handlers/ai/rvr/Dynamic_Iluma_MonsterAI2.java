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
package ai.rvr;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("dynamic_iluma_monster")
public class Dynamic_Iluma_MonsterAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		switch (Rnd.get(1, 2)) {
			case 1:
				spawnLF6EventDoor();
			break;
			case 2:
			break;
		}
		super.handleDied();
	}
	
	private void spawnLF6EventDoor() {
		spawn(241053, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Portal.
		switch (Rnd.get(1, 4)) {
			case 1:
			    spawn(240887, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Warrior.
			break;
			case 2:
				spawn(240888, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Mage.
			break;
			case 3:
				spawn(240889, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Scout.
			break;
			case 4:
				spawn(240890, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Marksman.
			break;
		}
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				despawnNpc(241053); //Portal.
			}
		}, 60000);
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
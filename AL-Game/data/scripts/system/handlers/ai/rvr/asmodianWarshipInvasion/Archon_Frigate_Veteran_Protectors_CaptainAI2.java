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
package ai.rvr.asmodianWarshipInvasion;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("DF6_Event_G1_S1_Kn_75_Ah")
public class Archon_Frigate_Veteran_Protectors_CaptainAI2 extends AggressiveNpcAI2
{
	@Override
	public void think() {
	}
	
	@Override
	protected void handleDied() {
		despawnNpc(240664);
		despawnNpc(240665);
		despawnNpc(240666);
		spawn(240667, 1409.8998f, 1369.7438f, 1336.7855f, (byte) 60); //Archon Frigate Special Grade Combat Captain.
		spawn(240668, 1407.2133f, 1371.8616f, 1336.7855f, (byte) 60); //Archon Frigate Special Grade Assault Leader.
		spawn(240668, 1412.3649f, 1367.3982f, 1336.7855f, (byte) 60); //Archon Frigate Special Grade Assault Leader.
		spawn(240668, 1412.2811f, 1372.0088f, 1336.7855f, (byte) 60); //Archon Frigate Special Grade Assault Leader.
		spawn(240668, 1407.1234f, 1367.6641f, 1336.7855f, (byte) 60); //Archon Frigate Special Grade Assault Leader.
		super.handleDied();
		AI2Actions.deleteOwner(this);
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
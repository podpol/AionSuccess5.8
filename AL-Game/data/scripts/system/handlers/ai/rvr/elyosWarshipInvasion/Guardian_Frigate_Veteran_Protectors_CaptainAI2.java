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
package ai.rvr.elyosWarshipInvasion;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("LF6_Event_G1_S1_Kn_75_Ah")
public class Guardian_Frigate_Veteran_Protectors_CaptainAI2 extends AggressiveNpcAI2
{
	@Override
	public void think() {
	}
	
	@Override
	protected void handleDied() {
		despawnNpc(240757);
		despawnNpc(240758);
		despawnNpc(240759);
		spawn(240760, 1391.9437f, 1615.8804f, 1010.55457f, (byte) 27); //Guardian Frigate Special Grade Combat Captain.
		spawn(240761, 1395.8307f, 1620.0297f, 1010.55457f, (byte) 15); //Guardian Frigate Special Grade Assault Leader.
		spawn(240761, 1387.7642f, 1611.8877f, 1010.55457f, (byte) 75); //Guardian Frigate Special Grade Assault Leader.
		spawn(240761, 1394.0009f, 1610.4152f, 1010.55457f, (byte) 98); //Guardian Frigate Special Grade Assault Leader.
		spawn(240761, 1389.7134f, 1621.1094f, 1010.55457f, (byte) 37); //Guardian Frigate Special Grade Assault Leader.
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
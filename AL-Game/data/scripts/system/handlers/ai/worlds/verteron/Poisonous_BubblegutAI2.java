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
package ai.worlds.verteron;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("poisonous_bubblegut")
public class Poisonous_BubblegutAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleSpawned() {
  		protectionFluid();
		super.handleSpawned();
	}
	
	private void protectionFluid() {
   		SkillEngine.getInstance().getSkill(getOwner(), 16447, 1, getOwner()).useNoAnimationSkill(); //Spout Sticky Protection Fluid.
 	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
		    case 210318: //Poisonous Bubblegut.
			    spawn(203195, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Kato.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
				    public void run() {
				        despawnNpc(203195); //Kato.
				    }
			    }, 60000);
			break;	
		}
		super.handleDied();
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
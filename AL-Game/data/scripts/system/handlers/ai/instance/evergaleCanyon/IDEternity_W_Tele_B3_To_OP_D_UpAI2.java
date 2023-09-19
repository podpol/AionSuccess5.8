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
package ai.instance.evergaleCanyon;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEternity_W_Tele_B3_To_OP_D_Up")
public class IDEternity_W_Tele_B3_To_OP_D_UpAI2 extends ActionItemNpcAI2
{
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        checkDistance(this, creature);
    }
	
	private void checkDistance(NpcAI2 ai, Creature creature) {
        if (creature instanceof Player && !creature.getLifeStats().isAlreadyDead()) {
			final Player player = (Player) creature;
        	if (MathUtil.isIn3dRange(getOwner(), creature, 10)) {
        		if (player.getCommonData().getRace() == Race.ELYOS) {
					IDEternity_W_Tele_B3_To_OP_L_Up();
				}
        	}
        }
    }
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 835290: //IDEternity_W_Tele_B3_To_OP_D_Up.
				TeleportService2.teleportTo(player, 302350000, 1067.8562f, 752.53455f, 336.36725f, (byte) 60);
			break;
        }
	}
	
	private void IDEternity_W_Tele_B3_To_OP_L_Up() {
		despawnNpc(835290);
		despawnNpc(835454);
		AI2Actions.deleteOwner(IDEternity_W_Tele_B3_To_OP_D_UpAI2.this);
		spawn(835278, 746.86560f, 850.73126f, 347.88959f, (byte) 0, 257);
		spawn(835453, 746.86560f, 850.73126f, 347.88959f, (byte) 0, 323);
    }
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
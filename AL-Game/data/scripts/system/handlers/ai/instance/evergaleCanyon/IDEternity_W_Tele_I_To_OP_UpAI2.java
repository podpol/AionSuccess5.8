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

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEternity_W_Tele_I_To_OP_Up")
public class IDEternity_W_Tele_I_To_OP_UpAI2 extends NpcAI2
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
					IDEternity_W_Tele_I_To_OP_L_Up();
					announceTele05E();
				} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
					IDEternity_W_Tele_I_To_OP_D_Up();
					announceTele06A();
				}
        	}
        }
    }
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		announceTele11();
	}
	
	private void IDEternity_W_Tele_I_To_OP_L_Up() {
		despawnNpc(835292);
		despawnNpc(835454);
		AI2Actions.deleteOwner(IDEternity_W_Tele_I_To_OP_UpAI2.this);
		spawn(835280, 1035.4257f, 1065.4717f, 350.2265f, (byte) 0, 56);
		spawn(835453, 1035.4257f, 1065.4717f, 350.2265f, (byte) 0, 300);
    }
	private void IDEternity_W_Tele_I_To_OP_D_Up() {
		despawnNpc(835280);
		despawnNpc(835453);
		AI2Actions.deleteOwner(IDEternity_W_Tele_I_To_OP_UpAI2.this);
		spawn(835292, 1035.4257f, 1065.4717f, 350.2265f, (byte) 0, 56);
		spawn(835454, 1035.4257f, 1065.4717f, 350.2265f, (byte) 0, 298);
    }
	
	private void announceTele11() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//I�?� 순간�?��?� 장치가 �?령 가능합니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_War_tele_11);
				}
			}
		});
	}
	private void announceTele05E() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//I�?� 순간�?��?� 장치를 천족�?� �?령해 �?�용 가능 합니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_War_tele_05);
				}
			}
		});
	}
	private void announceTele06A() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//I�?� 순간�?��?� 장치를 마족�?� �?령해 �?�용 가능 합니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_War_tele_06);
				}
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
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
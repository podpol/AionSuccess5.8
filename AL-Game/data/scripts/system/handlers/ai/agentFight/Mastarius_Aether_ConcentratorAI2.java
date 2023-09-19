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
package ai.agentFight;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("mastarius_aether_concentrator")
public class Mastarius_Aether_ConcentratorAI2 extends ActionItemNpcAI2
{
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				SkillEngine.getInstance().getSkill(getOwner(), 20125, 1, getOwner()).useNoAnimationSkill(); //Aether Concentrator Standby.
				SkillEngine.getInstance().getSkill(getOwner(), 22776, 1, getOwner()).useNoAnimationSkill();
				SkillEngine.getInstance().getSkill(getOwner(), 22781, 1, getOwner()).useNoAnimationSkill();
				SkillEngine.getInstance().getSkill(getOwner(), 22783, 1, getOwner()).useNoAnimationSkill();
			}
		}, 1000);
    }
	
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    //Mastarius's Aether Concentrator I
			case 296913:
				if (player.getInventory().decreaseByItemId(164000103, 1)) { //Blessing Of Concentration.
					announceMastariusI();
				    AI2Actions.targetCreature(Mastarius_Aether_ConcentratorAI2.this, getPosition().getWorldMapInstance().getNpc(235065)); //Empowered Mastarius.
				    AI2Actions.useSkill(Mastarius_Aether_ConcentratorAI2.this, 20107); //Defense Aether.
				} else {
					//You have failed to use the Empyrean Avatar. You will need to gather power and summon it again.
				    PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_DEATHBLOW_FAIL);
				}
		    break;
			//Mastarius's Aether Concentrator II
			case 296914:
			    if (player.getInventory().decreaseByItemId(164000103, 1)) { //Blessing Of Concentration.
					announceMastariusII();
				    AI2Actions.targetCreature(Mastarius_Aether_ConcentratorAI2.this, getPosition().getWorldMapInstance().getNpc(235065)); //Empowered Mastarius.
				    AI2Actions.useSkill(Mastarius_Aether_ConcentratorAI2.this, 20108); //Elemental Resistance Aether.
				} else {
					//You have failed to use the Empyrean Avatar. You will need to gather power and summon it again.
				    PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_DEATHBLOW_FAIL);
				}
			break;
			//Mastarius's Aether Concentrator III
			case 296915:
			    if (player.getInventory().decreaseByItemId(164000103, 1)) { //Blessing Of Concentration.
					announceMastariusII();
				    AI2Actions.targetCreature(Mastarius_Aether_ConcentratorAI2.this, getPosition().getWorldMapInstance().getNpc(235065)); //Empowered Mastarius.
				    AI2Actions.useSkill(Mastarius_Aether_ConcentratorAI2.this, 20109); //Power Aether.
				} else {
					//You have failed to use the Empyrean Avatar. You will need to gather power and summon it again.
				    PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_DEATHBLOW_FAIL);
				}
			break;
		}
		announceMastariusIII();
	}
	
	private void announceMastariusI() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The first Sphere of Destiny has been activated.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_BUFF_FIRST_OBJECT_ON_DF);
			}
		});
	}
	private void announceMastariusII() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The second Sphere of Destiny has been activated. Marchutan's Agent Mastarius prepares to cast the Empyrean Lord's blessing.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_BUFF_SECOND_OBJECT_ON_DF);
			}
		});
	}
	private void announceMastariusIII() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//You may use the Sphere of Destiny again.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GODELITE_BUFF_CAN_USE_OBJECT_DF, 120000);
			}
		});
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
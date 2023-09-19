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
package ai.instance.transidiumAnnex;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("hangar_controller")
public class Hangar_ControllerAI2 extends NpcAI2
{
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 297310: //Chariot Hangar I Controller.
				    announceGAB1SubTankA();
				break;
				case 297311: //Chariot Hangar II Controller.
				    announceGAB1SubTankB();
				break;
				case 297312: //Ignus Engine Hangar I Controller.
					announceGAB1SubTankC();
				break;
				case 297313: //Ignus Engine Hangar II Controller.
					announceGAB1SubTankD();
				break;
			}
		}
	}
	
	private void announceGAB1SubTankA() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Chariot Hangar I Controller is under attack.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_TANK_A_ATTACKED);
				}
			}
		});
	}
	private void announceGAB1SubTankB() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Chariot Hangar II Controller is under attack.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_TANK_B_ATTACKED);
				}
			}
		});
	}
	private void announceGAB1SubTankC() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Ignus Engine Hangar I Controller is under attack.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_TANK_C_ATTACKED);
				}
			}
		});
	}
	private void announceGAB1SubTankD() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Ignus Engine Hangar II Controller is under attack.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_TANK_D_ATTACKED);
				}
			}
		});
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
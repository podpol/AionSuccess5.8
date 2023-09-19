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

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("rift_generator")
public class Rift_GeneratorAI2 extends NpcAI2
{
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			announceRiftGeneratorUnderAttack();
		}
	}
	
	@Override
	protected void handleDied() {
        announceRiftGeneratorDie();
		super.handleDied();
	}
	
	private void announceRiftGeneratorUnderAttack() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Rift Generator is under attack! Once it is destroyed, the Dimensional Vortex will close.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CHAT_INVADEPORTL_KEEPER_SYSTEM_MSG01);
			}
		});
	}
	private void announceRiftGeneratorDie() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Rift Generator has been destroyed.
				//The Dimensional Vortex will close shortly, the infiltration alliance will be disbanded, and its members will be returned home.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CHAT_INVADEPORTL_KEEPER_SYSTEM_MSG03);
			}
		});
	}
}
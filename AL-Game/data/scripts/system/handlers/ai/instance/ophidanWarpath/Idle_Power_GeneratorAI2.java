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
package ai.instance.ophidanWarpath;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("idle_power_generator")
public class Idle_Power_GeneratorAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		switch (getNpcId()) {
			case 806391: //North Power Generator.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
					public void run() {
						announceWarNeu01();
						spawn(833935, 589.974180f, 407.85278f, 610.20313f, (byte) 0, 3);
					}
				}, 300000); //5 Minutes.
			break;
			case 806392: //South Power Generator.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
					public void run() {
						announceWarNeu01();
						spawn(833936, 605.049130f, 553.60150f, 591.49310f, (byte) 0, 42);
					}
				}, 300000); //5 Minutes.
			break;
		}
		super.handleSpawned();
	}
	
	private void announceWarNeu01() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDLDF5_Under_02_war_neu_01);
			}
		});
	}
}
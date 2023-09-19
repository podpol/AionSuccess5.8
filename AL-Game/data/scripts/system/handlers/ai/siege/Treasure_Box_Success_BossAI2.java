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
package ai.siege;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("treasure_box_success_boss")
public class Treasure_Box_Success_BossAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			//Siel's Western Fortress 5.3
			case 263001:
			case 263006:
			case 263011:
				treasureChest();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
			break;
			//Siel's Eastern Fortress 5.3
			case 263301:
			case 263306:
			case 263311:
				treasureChest();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
			break;
			//Sulfur Fortress 5.3
			case 264501:
			case 264506:
			case 264511:
				treasureChest();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
			break;
		}
		super.handleDied();
	}
	
	private void treasureChest() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//A treasure chest has appeared.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDAbRe_Core_NmdC_BoxSpawn);
			}
		});
	}
	
	private void spawnTreasureChest(int npcId) {
		rndSpawnInRange(npcId, Rnd.get(1, 4));
		rndSpawnInRange(npcId, Rnd.get(1, 4));
		rndSpawnInRange(npcId, Rnd.get(1, 4));
		rndSpawnInRange(npcId, Rnd.get(1, 4));
		rndSpawnInRange(npcId, Rnd.get(1, 4));
		rndSpawnInRange(npcId, Rnd.get(1, 4));
	}
	
	private Npc rndSpawnInRange(int npcId, float distance) {
		float direction = Rnd.get(0, 199) / 100f;
		float x1 = (float) (Math.cos(Math.PI * direction) * distance);
		float y1 = (float) (Math.sin(Math.PI * direction) * distance);
		WorldPosition p = getPosition();
		return (Npc) spawn(npcId, p.getX() + x1, p.getY() + y1, p.getZ(), (byte) 0);
	}
}
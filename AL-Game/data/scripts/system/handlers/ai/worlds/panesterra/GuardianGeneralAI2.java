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
package ai.worlds.panesterra;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("guardian_general")
public class GuardianGeneralAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 277400: //Mirage Dance Guardian General.
			case 277405: //Chaos Wind Guardian General.
				killedTheGuardianGeneral();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
					    spawn(802219, 1024.12f, 1078.747f, 1530.2688f, (byte) 90);
			        }
		        }, 480000);
			break;
			case 277415: //Mirage Dance Guardian General.
			case 277420: //Chaos Wind Guardian General.
			    killedTheGuardianGeneral();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
					    spawn(802221, 1024.12f, 1078.747f, 1530.2688f, (byte) 90);
			        }
		        }, 480000);
			break;
			case 277430: //Mirage Dance Guardian General.
			case 277435: //Chaos Wind Guardian General.
			    killedTheGuardianGeneral();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
					    spawn(802223, 1024.12f, 1078.747f, 1530.2688f, (byte) 90);
			        }
		        }, 480000);
			break;
			case 277445: //Mirage Dance Guardian General.
			case 277450: //Chaos Wind Guardian General.
			    killedTheGuardianGeneral();
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
			            spawnTreasureChest(701481);
			        }
		        }, 10000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
			        @Override
			        public void run() {
					    spawn(802225, 1024.12f, 1078.747f, 1530.2688f, (byte) 90);
			        }
		        }, 480000);
			break;
		}
		super.handleDied();
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
	
	private void killedTheGuardianGeneral() {
        World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//Loading the Advance Corridor Shield... Please wait.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_01, 0);
				//The entrance to the Transidium Annex will open in 8 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_02, 10000);
				//The entrance to the Transidium Annex will open in 6 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_03, 120000);
				//The entrance to the Transidium Annex will open in 4 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_04, 240000);
				//The entrance to the Transidium Annex will open in 2 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_05, 360000);
				//The entrance to the Transidium Annex will open in 1 minute.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_06, 420000);
				//The entrance to the Transidium Annex has opened.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_08, 480000);
			}
		});
    }
}
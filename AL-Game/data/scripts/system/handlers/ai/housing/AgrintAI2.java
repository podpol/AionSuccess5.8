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
package ai.housing;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("agrint")
public class AgrintAI2 extends AggressiveNpcAI2
{
	private boolean canThink = true;
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
	}
	
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		switch (getNpcId()) {
			case 218850: //Spring Agrint.
			    announceSpringAgrint();
			break;
			case 218851: //Summer Agrint.
			    announceSummerAgrint();
			break;
			case 218852: //Fall Agrint.
			    announceFallAgrint();
			break;
			case 218853: //Winter Agrint.
				announceWinterAgrint();
			break;
		}
    }
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			//AGRINT ORIEL.
			case 218850: //Spring Agrint.
				spawnUmbronite(218866); //Vernal Umbronite.
				spawnUmbronite(218867); //Sprout Umbronite.
			break;
			case 218851: //Summer Agrint.
				spawnUmbronite(218868); //Sweltering Umbronite.
				spawnUmbronite(218869); //Rain Umbronite.
			break;
			case 218852: //Fall Agrint.
				spawnUmbronite(218870); //Maple Umbronite.
				spawnUmbronite(218871); //Dusk Umbronite.
			break;
			case 218853: //Winter Agrint.
				spawnUmbronite(218872); //Ice Umbronite.
				spawnUmbronite(218873); //Snowflower Umbronite.
			break;
			//AGRINT PERNON.
			case 218862: //Spring Agrint.
				spawnUmbronite(218882); //Vernal Umbronite.
				spawnUmbronite(218883); //Sprout Umbronite.
			break;
			case 218863: //Summer Agrint.
				spawnUmbronite(218884); //Sweltering Umbronite.
				spawnUmbronite(218885); //Rain Umbronite.
			break;
			case 218864: //Fall Agrint.
				spawnUmbronite(218886); //Maple Umbronite.
				spawnUmbronite(218887); //Dusk Umbronite.
			break;
			case 218865: //Winter Agrint.
				spawnUmbronite(218888); //Ice Umbronite.
				spawnUmbronite(218889); //Snowflower Umbronite.
			break;
		}
		super.handleDied();
	}
	
	private void announceSpringAgrint() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HF_SpringAgrintAppear);
			}
		});
	}
	
	private void announceSummerAgrint() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HF_SummerAgrintAppear);
			}
		});
	}
	
	private void announceFallAgrint() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HF_FallAgrintAppear);
			}
		});
	}
	
	private void announceWinterAgrint() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HF_WinterAgrintAppear);
			}
		});
	}
	
	private void spawnUmbronite(int npcId) {
		rndSpawnInRange(npcId, Rnd.get(1, 5));
		rndSpawnInRange(npcId, Rnd.get(1, 5));
		rndSpawnInRange(npcId, Rnd.get(1, 5));
		rndSpawnInRange(npcId, Rnd.get(1, 5));
		rndSpawnInRange(npcId, Rnd.get(1, 5));
		rndSpawnInRange(npcId, Rnd.get(1, 5));
	}
	
	private Npc rndSpawnInRange(int npcId, float distance) {
		float direction = Rnd.get(0, 199) / 100f;
		float x1 = (float) (Math.cos(Math.PI * direction) * distance);
		float y1 = (float) (Math.sin(Math.PI * direction) * distance);
		WorldPosition p = getPosition();
		return (Npc) spawn(npcId, p.getX() + x1, p.getY() + y1, p.getZ(), (byte) 0);
	}
	
	@Override
	public int modifyOwnerDamage(int damage) {
		return 1;
	}
	
	@Override
	public int modifyDamage(int damage) {
		return 1;
	}
}
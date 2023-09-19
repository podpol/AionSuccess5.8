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
package ai.instance.contaminedUnderpath;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.world.WorldPosition;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("maad_s")
public class MAAD_SAI2 extends AggressiveNpcAI2
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
	protected void handleDied() {
		switch (getNpcId()) {
			case 245575: //MAAD-S.
			case 248525: //IDEvent_Def_ZombieKing_65.
				spawnMAD99SCore(246352);
			break;
		}
		super.handleDied();
	}
	
	private void spawnMAD99SCore(int npcId) {
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
}
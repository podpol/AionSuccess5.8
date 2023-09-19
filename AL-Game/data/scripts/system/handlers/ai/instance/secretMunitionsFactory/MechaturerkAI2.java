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
package ai.instance.secretMunitionsFactory;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.world.WorldPosition;

/****/
/** Author Rinzler, Ranastic (Encom)
/****/

@AIName("Mechaturerk")
public class MechaturerkAI2 extends AggressiveNpcAI2
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
		spawnMechaturerkCore(245185);
		spawn(703381, 138.86005f, 253.14404f, 191.8727f, (byte) 0); //Mechaturerk’s Footlocker.
		spawn(703382, 138.84244f, 249.96141f, 191.8727f, (byte) 0); //Mechaturerk’s Core.
		spawn(703383, 138.83214f, 246.4382f, 191.8727f, (byte) 0); //Destruction Golem’s Footlocker.
		spawn(833998, 152.87827f, 268.53104f, 191.8727f, (byte) 106); //Corridor To Atreia.
		spawn(834167, 149.93068f, 255.50876f, 191.8727f, (byte) 6); //Jay.
		super.handleDied();
	}
	
	private void spawnMechaturerkCore(int npcId) {
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
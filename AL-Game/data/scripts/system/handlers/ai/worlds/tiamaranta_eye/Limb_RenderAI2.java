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
package ai.worlds.tiamaranta_eye;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.model.gameobjects.Creature;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Limb_Render")
public class Limb_RenderAI2 extends NpcAI2
{
	int attackCount;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
    public void handleAttack(Creature creature) {
		if (isAggred.compareAndSet(false, true)) {
			//A Limb Render is under attack. Defeat the player attacking the crystal.
			NpcShoutsService.getInstance().sendMsg(getOwner(), 1401462);
		}
        attackCount++;
        if (attackCount == 195) {
            attackCount = 0;
			AI2Actions.useSkill(this, 20655); //Crystal Frgament.
			//A Limb Render has exploded.
            NpcShoutsService.getInstance().sendMsg(getOwner(), 1401463);
        }
		super.handleAttack(creature);
    }
	
	@Override
	protected void handleSpawned() {
  		switch (getNpcId()) {
			//Limb Render.
			case 283072:
			case 858016:
			    //A Limb Render has appeared. It explodes when destroyed, inflicting serious damage to those nearby.
				NpcShoutsService.getInstance().sendMsg(getOwner(), 1401461);
			break;
		}
		super.handleSpawned();
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
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
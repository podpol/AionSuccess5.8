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
package ai.worlds.cygnea;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("deadly_sunayaka")
public class Deadly_SunayakaAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			simmeringRage();
		}
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
  		rageOfTheDragonLords();
	}
	
 	private void simmeringRage() {
   		SkillEngine.getInstance().getSkill(getOwner(), 20651, 1, getOwner()).useNoAnimationSkill(); //Simmering Rage.
   		getOwner().getEffectController().removeEffect(8763);
 	}
	
 	private void rageOfTheDragonLords() {
   		SkillEngine.getInstance().getSkill(getOwner(), 8763, 1, getOwner()).useNoAnimationSkill(); //Rage of the Dragon Lords
 	}
}
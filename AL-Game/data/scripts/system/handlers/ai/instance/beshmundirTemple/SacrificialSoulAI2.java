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
package ai.instance.beshmundirTemple;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.model.actions.NpcActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("templeSoul")
public class SacrificialSoulAI2 extends AggressiveNpcAI2
{
	private Npc boss;
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		AI2Actions.useSkill(this, 18901); //Time Wrinkle.
		this.setStateIfNot(AIState.FOLLOWING);
		boss = getPosition().getWorldMapInstance().getNpc(216263); //Isbariya The Resolute.
		if (boss != null && !NpcActions.isAlreadyDead(boss)) {
			AI2Actions.targetCreature(this, boss);
			getMoveController().moveToTargetObject();
		}
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (creature.getEffectController().hasAbnormalEffect(18959)) { //Sixth Sense.
			getMoveController().abortMove();
			AI2Actions.deleteOwner(this);
		}
	}
	
	@Override
	protected void handleMoveArrived() {
		if (boss != null && !NpcActions.isAlreadyDead(boss)) {
			SkillEngine.getInstance().getSkill(getOwner(), 18960, 55, boss).useNoAnimationSkill(); //Call Of The Grave.
			AI2Actions.deleteOwner(this);
		}
	}
	
	@Override
	public boolean canThink() {
		return false;
	}
}
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
package ai.instance.tallocsHollow;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("kinquid_debuff")
public class KinquidDebuffAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleCreatureMoved(Creature creature) {
		super.handleCreatureMoved(creature);
		if (creature instanceof Npc && isInRange(creature, 10)) {
			Npc npc = (Npc) creature;
			if (npc.getNpcId() == 215467) { //Kindquid.
				SkillEngine.getInstance().getSkill(getOwner(), getNpcId() == 282008 ? 19235 : 19236, 46, getOwner()).useNoAnimationSkill();
			}
		}
	}
}
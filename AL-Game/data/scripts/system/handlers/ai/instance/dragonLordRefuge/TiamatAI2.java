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
package ai.instance.dragonLordRefuge;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("tiamat")
public class TiamatAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		SkillEngine.getInstance().getSkill(getOwner(), 20975, 1, getOwner()).useNoAnimationSkill(); //Fissure Incarnate.
		SkillEngine.getInstance().getSkill(getOwner(), 20976, 1, getOwner()).useNoAnimationSkill(); //Wrath Incarnate.
		SkillEngine.getInstance().getSkill(getOwner(), 20977, 1, getOwner()).useNoAnimationSkill(); //Gravity Incarnate.
		SkillEngine.getInstance().getSkill(getOwner(), 20978, 1, getOwner()).useNoAnimationSkill(); //Petrification Incarnate.
		SkillEngine.getInstance().getSkill(getOwner(), 20984, 1, getOwner()).useNoAnimationSkill(); //Unbreakable Wing.
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
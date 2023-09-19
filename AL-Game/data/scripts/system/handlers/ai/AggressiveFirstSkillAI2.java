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
package ai;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("aggressive_first_skill")
public class AggressiveFirstSkillAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleBackHome() {
		super.handleBackHome();
		if (getSkillList().getUseInSpawnedSkill() != null) {
			int skillId = getSkillList().getUseInSpawnedSkill().getSkillId();
			int skillLevel = getSkillList().getSkillLevel(skillId);
			SkillEngine.getInstance().getSkill(getOwner(), skillId, skillLevel, getOwner()).useSkill();
		}
	}
	
	@Override
	protected void handleRespawned() {
		super.handleRespawned();
		if (getSkillList().getUseInSpawnedSkill() != null) {
			int skillId = getSkillList().getUseInSpawnedSkill().getSkillId();
			int skillLevel = getSkillList().getSkillLevel(skillId);
			SkillEngine.getInstance().getSkill(getOwner(), skillId, skillLevel, getOwner()).useSkill();
		}
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		if (getSkillList().getUseInSpawnedSkill() != null) {
			int skillId = getSkillList().getUseInSpawnedSkill().getSkillId();
			int skillLevel = getSkillList().getSkillLevel(skillId);
			SkillEngine.getInstance().getSkill(getOwner(), skillId, skillLevel, getOwner()).useSkill();
		}
	}
}
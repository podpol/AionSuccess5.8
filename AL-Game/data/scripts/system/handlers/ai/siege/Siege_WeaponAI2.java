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
package ai.siege;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.AISummon;
import com.aionemu.gameserver.controllers.SiegeWeaponController;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.summons.SummonMode;
import com.aionemu.gameserver.model.templates.npcskill.NpcSkillTemplate;
import com.aionemu.gameserver.model.templates.npcskill.NpcSkillTemplates;
import com.aionemu.gameserver.services.summons.SummonsService;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("siege_weapon")
public class Siege_WeaponAI2 extends AISummon
{
	private long lastAttackTime;
	private int skill;
	private int skillLvl;
	private int duration;
	
	@Override
	protected void handleSpawned() {
		this.setStateIfNot(AIState.IDLE);
		SummonsService.doMode(SummonMode.GUARD, getOwner());
		NpcSkillTemplate skillTemplate = getNpcSkillTemplates().getNpcSkills().get(0);
		skill = skillTemplate.getSkillid();
		skillLvl = skillTemplate.getSkillLevel();
		duration = DataManager.SKILL_DATA.getSkillTemplate(this.skill).getDuration();
	}
	
	@Override
	protected void handleFollowMe(Creature creature) {
		this.setStateIfNot(AIState.FOLLOWING);
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
	}
	
	@Override
	protected void handleStopFollowMe(Creature creature) {
		this.setStateIfNot(AIState.IDLE);
		this.getOwner().getMoveController().abortMove();
	}
	
	@Override
	protected void handleTargetTooFar() {
		getOwner().getMoveController().moveToDestination();
	}
	
	@Override
	protected void handleMoveArrived() {
		this.getOwner().getController().onMove();
		this.getOwner().getMoveController().abortMove();
	}
	
	@Override
	protected void handleMoveValidate() {
		this.getOwner().getController().onMove();
		getMoveController().moveToTargetObject();
	}
	
	@Override
	protected SiegeWeaponController getController() {
		return (SiegeWeaponController) super.getController();
	}
	
	private NpcSkillTemplates getNpcSkillTemplates() {
		return getController().getNpcSkillTemplates();
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		if (creature == null) {
			return;
		}
		Race race = creature.getRace();
		Player master = getOwner().getMaster();
		if (master == null) {
			return;
		}
		Race masterRace = master.getRace();
		if (masterRace.equals(Race.ASMODIANS) && !race.equals(Race.PC_LIGHT_CASTLE_DOOR) && !race.equals(Race.DRAGON_CASTLE_DOOR)) {
			return;
		} else if (masterRace.equals(Race.ELYOS) && !race.equals(Race.PC_DARK_CASTLE_DOOR) && !race.equals(Race.DRAGON_CASTLE_DOOR)) {
			return;
		} if (!getOwner().getMode().equals(SummonMode.ATTACK)) {
			return;
		} if (System.currentTimeMillis() - lastAttackTime > duration + 2000) {
			lastAttackTime = System.currentTimeMillis();
			getOwner().getController().useSkill(skill, skillLvl);
		}
	}
}
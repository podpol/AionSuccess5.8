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
package ai.instance.sauroSupplyBase;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("chief_gunner_kurmata")
public class Chief_Gunner_KurmataAI2 extends AggressiveNpcAI2
{
	private int stage = 0;
	private boolean isStart = false;
	
	@Override
	protected void handleCreatureAggro(Creature creature) {
		super.handleCreatureAggro(creature);
		wakeUp();
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		beritraFavor();
	}
	
	private void beritraFavor() {
	    SkillEngine.getInstance().getSkill(getOwner(), 21194, 1, getOwner()).useNoAnimationSkill(); //Iron Guardian.
		SkillEngine.getInstance().getSkill(getOwner(), 21135, 1, getOwner()).useNoAnimationSkill(); //Beritra's Favor.
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
		wakeUp();
	}
	
	private void wakeUp() {
		isStart = true;
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 90 && stage < 1) {
			stage1();
			stage = 1;
		} if (hpPercentage <= 50 && stage < 2) {
			stage2();
			stage = 2;
		}
	}
	
	private void stage1() {
		int delay = 0;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
			SkillEngine.getInstance().getSkill(getOwner(), 20701, 60, getOwner()).useNoAnimationSkill(); //Blessing of Blood.
		}
	}
	
	private void stage2() {
		int delay = 20000;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
			SkillEngine.getInstance().getSkill(getOwner(), 20858, 60, getOwner()).useNoAnimationSkill(); //Thunder Crash Fallout.
			scheduleDelayStage2(delay);
		}
	}
	
	private void scheduleDelayStage2(int delay) {
		if (!isStart && !isAlreadyDead()) {
			return;
		} else {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					stage2();
				}
			}, delay);
		}
	}
	
	@Override
	protected void handleBackHome() {
        super.handleBackHome();
		isStart = false;
		stage = 0;
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		isStart = false;
		stage = 0;
	}
}
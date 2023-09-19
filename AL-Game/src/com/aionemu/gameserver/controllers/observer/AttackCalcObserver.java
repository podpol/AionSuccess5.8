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
package com.aionemu.gameserver.controllers.observer;

import com.aionemu.gameserver.controllers.attack.AttackResult;
import com.aionemu.gameserver.controllers.attack.AttackStatus;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.skillengine.model.Effect;

import java.util.List;

/**
 * @author ATracer
 */
public class AttackCalcObserver {

	/**
	 * @param status
	 * @return false
	 */
	public boolean checkStatus(AttackStatus status) {
		return false;
	}

	/**
	 * @param attackList
	 * @param effect 
	 * @return value
	 */
	public void checkShield(List<AttackResult> attackList, Effect effect, Creature attacker) {

	}

	/**
	 * @param status
	 * @return
	 */
	public boolean checkAttackerStatus(AttackStatus status) {
		return false;
	}
	
	/**
	 * @param status
	 * @param isSkill
	 * @return
	 */
	public AttackerCriticalStatus checkAttackerCriticalStatus(AttackStatus status, boolean isSkill) {
		return new AttackerCriticalStatus(false);
	}

	/**
	 * @param isSkill
	 * @return physical damage multiplier
	 */
	public float getBasePhysicalDamageMultiplier(boolean isSkill) {
		return 1f;
	}

	/**
	 * @return magic damage multiplier
	 */
	public float getBaseMagicalDamageMultiplier() {
		return 1f;
	}
}

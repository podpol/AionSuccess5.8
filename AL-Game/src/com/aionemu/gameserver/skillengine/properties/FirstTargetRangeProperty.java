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
package com.aionemu.gameserver.skillengine.properties;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.skillengine.properties.Properties.CastState;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.geo.GeoService;

/**
 * @author ATracer
 */
public class FirstTargetRangeProperty {

	/**
	 * @param skill
	 * @param properties
	 */
	public static boolean set(Skill skill, Properties properties, CastState castState) {
		float firstTargetRange = properties.getFirstTargetRange();
		if (!skill.isFirstTargetRangeCheck())
			return true;

		Creature effector = skill.getEffector();
		Creature firstTarget = skill.getFirstTarget();

		if (firstTarget == null)
			return false;

		// Add Weapon Range to distance
		if (properties.isAddWeaponRange()) {
			firstTargetRange += (float) skill.getEffector().getGameStats().getAttackRange().getCurrent() / 1000f;
		}

		// on end cast check add revision distance value
		if (!castState.isCastStart())
			firstTargetRange += properties.getRevisionDistance();

		if (firstTarget.getObjectId() == effector.getObjectId()) {
			return true;
		}

		if (!MathUtil.isInAttackRange(effector, firstTarget, firstTargetRange + 2)) {
			if (effector instanceof Player) {
				PacketSendUtility.sendPacket((Player) effector, SM_SYSTEM_MESSAGE.STR_ATTACK_TOO_FAR_FROM_TARGET);
			}
			return false;
		}

		// TODO check for all targets too
		// Summon Group Member exception
		if (skill.getSkillTemplate().getSkillId() != 3777) { //4.8
			if (!GeoService.getInstance().canSee(effector, firstTarget)) {
				if (effector instanceof Player) {
					PacketSendUtility.sendPacket((Player) effector, SM_SYSTEM_MESSAGE.STR_SKILL_OBSTACLE);
				}
				return false;
			}
		}
		return true;
	}
}
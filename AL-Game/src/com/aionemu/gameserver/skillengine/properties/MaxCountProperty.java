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
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author MrPoke
 */
public class MaxCountProperty {

	public static final boolean set(final Skill skill, Properties properties) {
		TargetRangeAttribute value = properties.getTargetType();
		int maxcount = properties.getTargetMaxCount();

		switch (value) {
			case AREA:
				int areaCounter = 0;
				final Creature firstTarget = skill.getFirstTarget();
				if (firstTarget == null) {
					return false;
				}
				SortedMap<Double, Creature> sortedMap = new TreeMap<Double, Creature>();
				for (Creature creature : skill.getEffectedList()) {
					sortedMap.put(MathUtil.getDistance(firstTarget, creature), creature);
				}
				skill.getEffectedList().clear();
				for (Creature creature : sortedMap.values()) {
					if (areaCounter >= maxcount)
						break;
					skill.getEffectedList().add(creature);
					areaCounter++;
				}
		}
		return true;
	}
}

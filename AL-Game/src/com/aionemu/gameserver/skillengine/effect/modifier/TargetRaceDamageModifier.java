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
package com.aionemu.gameserver.skillengine.effect.modifier;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer modified by Sippolo, kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TargetRaceDamageModifier")
public class TargetRaceDamageModifier extends ActionModifier {

	@XmlAttribute(name = "race")
	private Race skillTargetRace;

	@Override
	public int analyze(Effect effect) {
		Creature effected = effect.getEffected();

		int newValue = (value + effect.getSkillLevel() * delta);
		if (effected instanceof Player) {

			Player player = (Player) effected;
			switch (skillTargetRace) {
				case ASMODIANS:
					if (player.getRace() == Race.ASMODIANS)
						return newValue;
					break;
				case ELYOS:
					if (player.getRace() == Race.ELYOS)
						return newValue;
			}
		}
		else if (effected instanceof Npc) {
			Npc npc = (Npc) effected;
			if (npc.getObjectTemplate().getRace().toString().equals(skillTargetRace.toString()))
				return newValue;
			else
				return 0;
		}

		return 0;
	}

	@Override
	public boolean check(Effect effect) {
		Creature effected = effect.getEffected();
		if (effected instanceof Player) {

			Player player = (Player) effected;
			Race race = player.getRace();
			return race == Race.ASMODIANS && skillTargetRace == Race.ASMODIANS || race == Race.ELYOS && skillTargetRace == Race.ELYOS;
		}
		else if (effected instanceof Npc) {
			Npc npc = (Npc) effected;

			Race race = npc.getObjectTemplate().getRace();
			if (race == null)
				return false;

			return race.toString().equals(skillTargetRace.toString());
		}

		return false;
	}

}

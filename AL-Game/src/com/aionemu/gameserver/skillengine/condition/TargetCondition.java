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
package com.aionemu.gameserver.skillengine.condition;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.skillengine.properties.FirstTargetAttribute;
import com.aionemu.gameserver.skillengine.properties.TargetRangeAttribute;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TargetCondition")
public class TargetCondition extends Condition {

	@XmlAttribute(required = true)
	protected TargetAttribute value;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link TargetAttribute }
	 */
	public TargetAttribute getValue() {
		return value;
	}

	@Override
	public boolean validate(Skill skill) {
		if ((value == TargetAttribute.NONE) || (value == TargetAttribute.ALL))
			return true;
		if (skill.getSkillTemplate().getProperties().getTargetType().equals(TargetRangeAttribute.AREA))
			return true;
		if ((skill.getSkillTemplate().getProperties().getFirstTarget() != FirstTargetAttribute.TARGET) && (skill.getSkillTemplate().getProperties().getFirstTarget() != FirstTargetAttribute.TARGETORME)) {
			return true;
		}
		if ((skill.getSkillTemplate().getProperties().getFirstTarget() == FirstTargetAttribute.TARGETORME) && (skill.getEffector() == skill.getFirstTarget())) {
			return true;
		}
		boolean result = false;
		switch (value) {
			case NPC:
				result = skill.getFirstTarget() instanceof Npc;
				break;
			case PC:
				result = skill.getFirstTarget() instanceof Player;
		}

		if ((!result) && ((skill.getEffector() instanceof Player))) {
			PacketSendUtility.sendPacket((Player) skill.getEffector(), SM_SYSTEM_MESSAGE.STR_SKILL_TARGET_IS_NOT_VALID);
		}
		return result;
	}
}

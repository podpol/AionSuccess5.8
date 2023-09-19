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

import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.FlyingRestriction;
import com.aionemu.gameserver.skillengine.model.Skill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelfFlyingCondition")
public class SelfFlyingCondition extends Condition {

	@XmlAttribute(required = true)
	protected FlyingRestriction restriction;

	@Override
	public boolean validate(Skill env) {
		if (env.getEffector() == null)
			return false;

		switch (restriction) {
			case FLY:
				return env.getEffector().isFlying();
			case GROUND:
				return !env.getEffector().isFlying();
		}

		return true;
	}

	@Override
	public boolean validate(Effect effect) {
		if (effect.getEffector() == null)
			return false;

		switch (restriction) {
			case FLY:
				return effect.getEffector().isFlying();
			case GROUND:
				return !effect.getEffector().isFlying();
		}

		return true;
	}

}

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
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.controllers.attack.AttackUtil;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sippolo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoReduceSpellATKInstantEffect")
public class NoReduceSpellATKInstantEffect extends DamageEffect {

	@XmlAttribute
	protected boolean percent;

	@Override
	public void calculate(Effect effect) {
		if (!super.calculate(effect, null, null))
			return;

		int valueWithDelta = value + delta * effect.getSkillLevel();
		if (percent)
			valueWithDelta = (int) (valueWithDelta / 100f * effect.getEffected().getLifeStats().getMaxHp());
		int critAddDmg = this.critAddDmg2 + this.critAddDmg1 * effect.getSkillLevel();
		
		AttackUtil.calculateMagicalSkillResult(effect, valueWithDelta, null, getElement(), false, true, true, getMode(), this.critProbMod2, critAddDmg, shared, false);
	}

}
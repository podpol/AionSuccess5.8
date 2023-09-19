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

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.skillengine.action.DamageType;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.SkillTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarveSignetEffect")
public class CarveSignetEffect extends DamageEffect {

	@XmlAttribute(required = true)
	protected int signetlvlstart;
	@XmlAttribute(required = true)
	protected int signetlvl;
	@XmlAttribute(required = true)
	protected int signetid;
	@XmlAttribute(required = true)
	protected String signet;
	@XmlAttribute(required = true)
	protected int prob = 100;

	private int nextSignetLevel = 1;

	@Override
	public void applyEffect(Effect effect) {
		super.applyEffect(effect);

		if (Rnd.get(0, 100) > prob)
			return;

		Effect placedSignet = effect.getEffected().getEffectController().getAnormalEffect(signet);

		if (placedSignet != null)
			placedSignet.endEffect();

		SkillTemplate template = DataManager.SKILL_DATA.getSkillTemplate(signetid + nextSignetLevel - 1);
		Effect newEffect = new Effect(effect.getEffector(), effect.getEffected(), template, nextSignetLevel, 0);
		newEffect.initialize();
		newEffect.applyEffect();
	}

	@Override
	public void calculate(Effect effect) {
		if (!super.calculate(effect, DamageType.PHYSICAL))
			return;
		Effect placedSignet = effect.getEffected().getEffectController().getAnormalEffect(signet);
		nextSignetLevel = signetlvlstart > 0 ? signetlvlstart : 1;
		effect.setCarvedSignet(nextSignetLevel);
		if (placedSignet != null) {
			nextSignetLevel = placedSignet.getSkillId() - this.signetid + 2;
			if ((signetlvlstart > 0) && (nextSignetLevel < signetlvlstart))
				nextSignetLevel = signetlvlstart;

			effect.setCarvedSignet(nextSignetLevel);
			if (nextSignetLevel > signetlvl || nextSignetLevel > 5)
				nextSignetLevel--;
		}
	}
}

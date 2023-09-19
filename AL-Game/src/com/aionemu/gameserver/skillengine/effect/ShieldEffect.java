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

import com.aionemu.gameserver.controllers.observer.AttackCalcObserver;
import com.aionemu.gameserver.controllers.observer.AttackShieldObserver;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer modified by Wakizashi, Sippolo, kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShieldEffect")
public class ShieldEffect extends EffectTemplate {

	@XmlAttribute
	protected int hitdelta;
	@XmlAttribute
	protected int hitvalue;
	@XmlAttribute
	protected boolean percent;
	@XmlAttribute
	protected int radius = 0;
	@XmlAttribute
	protected int minradius = 0;
	@XmlAttribute
	protected Race condrace = null;

	@Override
	public void applyEffect(Effect effect) {
		// check for condition race, skillId: 10317,10318
		if (condrace != null && effect.getEffected().getRace() != condrace)
			return;

		effect.addToEffectedController();
	}

	@Override
	public void calculate(Effect effect) {
		effect.addSucessEffect(this);
	}

	@Override
	public void startEffect(final Effect effect) {
		int skillLvl = effect.getSkillLevel();
		int valueWithDelta = value + delta * skillLvl;
		int hitValueWithDelta = hitvalue + hitdelta * skillLvl;

		AttackShieldObserver asObserver = new AttackShieldObserver(hitValueWithDelta, valueWithDelta, percent, effect, hitType, getType(), hitTypeProb);

		effect.getEffected().getObserveController().addAttackCalcObserver(asObserver);
		effect.setAttackShieldObserver(asObserver, position);
		effect.getEffected().getEffectController().setUnderShield(true);
	}

	@Override
	public void endEffect(Effect effect) {
		AttackCalcObserver acObserver = effect.getAttackShieldObserver(position);
		if (acObserver != null)
			effect.getEffected().getObserveController().removeAttackCalcObserver(acObserver);
		effect.getEffected().getEffectController().setUnderShield(false);
	}

	/**
	 * shieldType 1:reflector 2: normal shield 8: protect
	 * 
	 * @return
	 */
	public int getType() {
		return 2;
	}

}

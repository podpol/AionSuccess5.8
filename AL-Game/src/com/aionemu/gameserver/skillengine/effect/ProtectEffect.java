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

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.AttackCalcObserver;
import com.aionemu.gameserver.controllers.observer.AttackShieldObserver;
import com.aionemu.gameserver.controllers.observer.ObserverType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sippolo modified by kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProtectEffect")
public class ProtectEffect extends ShieldEffect {

	@Override
	public void startEffect(final Effect effect) {

		AttackShieldObserver asObserver = new AttackShieldObserver(value, radius, percent, effect, this.hitType, this.getType(), this.hitTypeProb);

		effect.getEffected().getObserveController().addAttackCalcObserver(asObserver);
		effect.setAttackShieldObserver(asObserver, position);
		if ((effect.getEffector() instanceof Summon)) {
			ActionObserver summonRelease = new ActionObserver(ObserverType.SUMMONRELEASE) {

				public void summonrelease() {
					effect.endEffect();
				}
			};
			effect.getEffector().getObserveController().attach(summonRelease);
			effect.setActionObserver(summonRelease, position);
		}
		else {
			ActionObserver death = new ActionObserver(ObserverType.DEATH) {

				public void died(Creature creature) {
					effect.endEffect();
				}
			};
			effect.getEffector().getObserveController().attach(death);
			effect.setActionObserver(death, position);
		}
	}

	@Override
	public void endEffect(Effect effect) {
		AttackCalcObserver acObserver = effect.getAttackShieldObserver(position);
		if (acObserver != null)
			effect.getEffected().getObserveController().removeAttackCalcObserver(acObserver);
		ActionObserver aObserver = effect.getActionObserver(position);
		if (aObserver != null)
			effect.getEffector().getObserveController().removeObserver(aObserver);
	}

	/**
	 * shieldType 1:reflector 2: normal shield 8: protec
	 * 
	 * @return
	 */
	@Override
	public int getType() {
		return 8;
	}
}

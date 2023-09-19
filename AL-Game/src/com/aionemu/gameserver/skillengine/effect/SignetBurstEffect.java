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
import com.aionemu.gameserver.skillengine.action.DamageType;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer, kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignetBurstEffect")
public class SignetBurstEffect extends DamageEffect {

   @XmlAttribute
   protected int signetlvl;
   @XmlAttribute
   protected String signet;

   @Override
   public void calculate(Effect effect) {

	  Effect signetEffect = effect.getEffected().getEffectController().getAnormalEffect(signet);
	  if (!super.calculate(effect, DamageType.MAGICAL)) {
		 if (signetEffect != null)
			signetEffect.endEffect();
		 return;
	  }
	  int valueWithDelta = value + delta * effect.getSkillLevel();
	  int critAddDmg = this.critAddDmg2 + this.critAddDmg1 * effect.getSkillLevel();

	  if (signetEffect == null) {
		 valueWithDelta *= 0.05f;
		 AttackUtil.calculateMagicalSkillResult(effect, valueWithDelta, null, getElement(), true, true, false, getMode(), this.critProbMod2, critAddDmg, shared, false);
		 effect.setLaunchSubEffect(false);
	  }
	  else {

		 int level = signetEffect.getSkillLevel();
		 effect.setSignetBurstedCount(level);
		 switch (level) {
			case 1:
			   valueWithDelta *= 0.2f;
			   break;
			case 2:
			   valueWithDelta *= 0.5f;
			   break;
			case 3:
			   valueWithDelta *= 1.0f;
			   break;
			case 4:
			   valueWithDelta *= 1.2f;
			   break;
			case 5:
			   valueWithDelta *= 1.5f;
			   break;
		 }

		 /**
		  * custom bonuses for magical accurancy according to rune level and effector level follows same logic as damage
		  */
		 int accmod = 0;
		 int mAccurancy = effect.getEffector().getGameStats().getMAccuracy().getCurrent();
		 switch (level) {
			case 1:
			   accmod = (int) (-0.8f * mAccurancy);
			   break;
			case 2:
			   accmod = (int) (-0.5f * mAccurancy);
			   break;
			case 3:
			   accmod = 0;
			   break;
			case 4:
			   accmod = (int) (0.2f * mAccurancy);
			   break;
			case 5:
			   accmod = (int) (0.5f * mAccurancy);
			   break;
		 }
		 effect.setAccModBoost(accmod);

		 AttackUtil.calculateMagicalSkillResult(effect, valueWithDelta, null, getElement(), true, true, false, getMode(), this.critProbMod2, critAddDmg, shared, false);

		 if (signetEffect != null) {
			signetEffect.endEffect();
		 }
	  }
   }
}

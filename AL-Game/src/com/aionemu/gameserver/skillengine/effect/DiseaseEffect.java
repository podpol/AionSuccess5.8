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

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiseaseEffect")
public class DiseaseEffect extends EffectTemplate {

	@Override
	public void calculate(Effect effect) {
		super.calculate(effect, StatEnum.DISEASE_RESISTANCE, null);
	}

	// skillId 18386
	@Override
	public void applyEffect(Effect effect) {
		effect.addToEffectedController();
	}

	@Override
	public void startEffect(Effect effect) {
		Creature effected = effect.getEffected();
		effect.setAbnormal(AbnormalState.DISEASE.getId());
		effected.getEffectController().setAbnormal(AbnormalState.DISEASE.getId());
	}

	@Override
	public void endEffect(Effect effect) {
		if (effect.getEffected().getEffectController().isAbnormalSet(AbnormalState.DISEASE))
			effect.getEffected().getEffectController().unsetAbnormal(AbnormalState.DISEASE.getId());
	}

}

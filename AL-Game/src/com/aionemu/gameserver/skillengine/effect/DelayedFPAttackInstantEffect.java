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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sippolo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelayedFPAttackInstantEffect")
public class DelayedFPAttackInstantEffect extends EffectTemplate {

	@XmlAttribute
	protected int delay;
	@XmlAttribute
	protected boolean percent;

	@Override
	public void calculate(Effect effect) {
		if (!(effect.getEffected() instanceof Player))
			return;
		if (!super.calculate(effect, null, null))
			return;

		int maxFP = ((Player) effect.getEffected()).getLifeStats().getMaxFp();
		int newValue = (percent) ? (int) ((maxFP * value) / 100) : value;

		effect.setReserved2(newValue);
	}

	@Override
	public void applyEffect(final Effect effect) {
		final Player effected = (Player) effect.getEffected();
		final int newValue = effect.getReserved2();

		ThreadPoolManager.getInstance().schedule(new Runnable() {

			@Override
			public void run() {
				effected.getLifeStats().reduceFp(newValue);
			}
		}, delay);
	}
}

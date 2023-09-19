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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sippolo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DPTransferEffect")
public class DPTransferEffect extends EffectTemplate {

	@Override
	public void applyEffect(Effect effect) {
		((Player) effect.getEffected()).getCommonData().addDp(-effect.getReserved1());
		((Player) effect.getEffector()).getCommonData().addDp(effect.getReserved1());
	}

	@Override
	public void calculate(Effect effect) {
		if (!super.calculate(effect, null, null))
			return;
		effect.setReserved1(-getCurrentStatValue(effect));
	}

	private int getCurrentStatValue(Effect effect) {
		return ((Player) effect.getEffector()).getCommonData().getDp();
	}

	@SuppressWarnings("unused")
	private int getEffectedCurrentStatValue(Effect effect) {
		return ((Player) effect.getEffected()).getCommonData().getDp();
	}

	@SuppressWarnings("unused")
	private int getMaxStatValue(Effect effect) {
		return ((Player) effect.getEffected()).getGameStats().getMaxDp().getCurrent();
	}
}

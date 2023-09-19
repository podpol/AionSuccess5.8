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
package com.aionemu.gameserver.skillengine.periodicaction;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author antness
 */
public class MpUsePeriodicAction extends PeriodicAction {

	@XmlAttribute(name = "value")
	protected int value;

	@Override
	public void act(Effect effect) {
		Creature effected = effect.getEffected();
		int maxMp = effected.getGameStats().getMaxMp().getCurrent();
		int requiredMp = (int) (maxMp * (value / 100f));
		if (effected.getLifeStats().getCurrentMp() < requiredMp) {
			effect.endEffect();
			return;
		}
		effected.getLifeStats().reduceMp(requiredMp);
	}

}

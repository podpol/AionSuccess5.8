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
package com.aionemu.gameserver.model.stats.calc;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.stats.container.StatEnum;

/**
 * @author ATracer
 */
public class ReverseStat extends Stat2 {

	public ReverseStat(StatEnum stat, int base, Creature owner) {
		super(stat, base, owner);
	}

	public ReverseStat(StatEnum stat, int base, Creature owner, float bonusRate) {
		super(stat, base, owner, bonusRate);
	}

	@Override
	public void addToBase(int base) {
		this.base -= base;
		if (this.base < 0) {
			this.base = 0;
		}
	}

	@Override
	public void addToBonus(int bonus) {
		this.bonus -= bonusRate * bonus;
	}
	
	@Override
	public float calculatePercent(int delta) {
		float percent =  (100 - delta) / 100f;
		//TODO need double check here for negatives
		return percent < 0 ? 0 : percent;
	}

}

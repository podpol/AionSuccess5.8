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
package com.aionemu.gameserver.model.stats.calc.functions;

import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.model.stats.container.StatEnum;

/**
 * @author ATracer
 */
public class StatRateFunction extends StatFunction {

	public StatRateFunction() {
	}

	public StatRateFunction(StatEnum name, int value, boolean bonus) {
		super(name, value, bonus);
	}
	
	@Override
	public void apply(Stat2 stat) {
		if (isBonus()) {
			stat.addToBonus((int) (stat.getBase() * getValue() / 100f));
		}
		else {
			stat.setBase((int) (stat.getBase() * stat.calculatePercent(getValue())));
		}
	}

	@Override
	public final int getPriority() {
		return isBonus() ? 40 : 20;
	}
	
	@Override
	public String toString() {
		return "StatRateFunction [" + super.toString() + "]";
	}

}

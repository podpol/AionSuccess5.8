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
package com.aionemu.gameserver.services.player.CreativityPanel.stats;

import java.util.ArrayList;
import java.util.List;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction;
import com.aionemu.gameserver.model.stats.container.StatEnum;

public class Agility implements StatOwner {

	private List<IStatFunction> agility = new ArrayList<IStatFunction>();

	public void onChange(Player player, int point) {
		if (point >= 1) {
			agility.clear();
			player.getGameStats().endEffect(this);
			agility.add(new StatAddFunction(StatEnum.CONCENTRATION, (int) (0.56f * point), true));
			agility.add(new StatAddFunction(StatEnum.PARRY, (int) (5.6f * point), true));
			agility.add(new StatAddFunction(StatEnum.EVASION, (int) (3.7f * point), true));
			agility.add(new StatAddFunction(StatEnum.PHYSICAL_CRITICAL_RESIST, (int) (2.4f * point), true));
			player.getGameStats().addEffect(this, agility);
		}
		else if (point == 0) {
			agility.clear();
			agility.add(new StatAddFunction(StatEnum.CONCENTRATION, (int) (0.56f * point), false));
			agility.add(new StatAddFunction(StatEnum.PARRY, (int) (5.6f * point), false));
			agility.add(new StatAddFunction(StatEnum.EVASION, (int) (3.7f * point), false));
			agility.add(new StatAddFunction(StatEnum.PHYSICAL_CRITICAL_RESIST, (int) (2.4f * point), false));
			player.getGameStats().endEffect(this);
		}
	}

	public static Agility getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	private static class NewSingletonHolder {

		private static final Agility INSTANCE = new Agility();
	}
}

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

public class Knowledge implements StatOwner {

	private List<IStatFunction> knowledge = new ArrayList<IStatFunction>();

	public void onChange(Player player, int point) {
		if (point >= 1) {
			knowledge.clear();
			player.getGameStats().endEffect(this);
			knowledge.add(new StatAddFunction(StatEnum.BOOST_MAGICAL_SKILL, (int) (6.0f * point), true));
			knowledge.add(new StatAddFunction(StatEnum.MAGICAL_CRITICAL, (int) (2.25f * point), true));
			knowledge.add(new StatAddFunction(StatEnum.MAGIC_SKILL_BOOST_RESIST, (int) (3.0f * point), true));
			player.getGameStats().addEffect(this, knowledge);
		}
		else if (point == 0) {
			knowledge.clear();
			knowledge.add(new StatAddFunction(StatEnum.BOOST_MAGICAL_SKILL, (int) (6.0f * point), false));
			knowledge.add(new StatAddFunction(StatEnum.MAGICAL_CRITICAL, (int) (2.25f * point), false));
			knowledge.add(new StatAddFunction(StatEnum.MAGIC_SKILL_BOOST_RESIST, (int) (3.0f * point), false));
			player.getGameStats().endEffect(this);
		}
	}

	public static Knowledge getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	private static class NewSingletonHolder {

		private static final Knowledge INSTANCE = new Knowledge();
	}
}

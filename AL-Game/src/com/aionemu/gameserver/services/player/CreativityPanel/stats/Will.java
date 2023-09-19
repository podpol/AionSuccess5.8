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

public class Will implements StatOwner {

	private List<IStatFunction> will = new ArrayList<IStatFunction>();

	public void onChange(Player player, int point) {
		if (point >= 1) {
			will.clear();
			player.getGameStats().endEffect(this);
			will.add(new StatAddFunction(StatEnum.MAXMP, (int) (24.5f * point), true));
			will.add(new StatAddFunction(StatEnum.MAGICAL_RESIST, (int) (3.35f * point), true));
			will.add(new StatAddFunction(StatEnum.MAGICAL_CRITICAL_RESIST, (int) (2.25f * point), true));
			will.add(new StatAddFunction(StatEnum.REGEN_MP, (int) (0.2f * point), true));
			player.getGameStats().addEffect(this, will);
		}
		else if (point == 0) {
			will.clear();
			will.add(new StatAddFunction(StatEnum.MAXMP, (int) (24.5f * point), false));
			will.add(new StatAddFunction(StatEnum.MAGICAL_RESIST, (int) (3.35f * point), false));
			will.add(new StatAddFunction(StatEnum.MAGICAL_CRITICAL_RESIST, (int) (2.25f * point), false));
			will.add(new StatAddFunction(StatEnum.REGEN_MP, (int) (0.2f * point), false));
			player.getGameStats().endEffect(this);
		}
	}

	public static Will getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	private static class NewSingletonHolder {

		private static final Will INSTANCE = new Will();
	}
}

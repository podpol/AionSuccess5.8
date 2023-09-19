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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction;
import com.aionemu.gameserver.model.stats.container.StatEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

public class Accuracy implements StatOwner
{
	private List<IStatFunction> accuracy = new ArrayList<IStatFunction>();
	
	public void onChange(Player player, int point) {
        if (point >= 1) {
            accuracy.clear();
            player.getGameStats().endEffect(this);
            accuracy.add(new StatAddFunction(StatEnum.ACCURACY, (int) (7.84f * point), true));
			accuracy.add(new StatAddFunction(StatEnum.PHYSICAL_ACCURACY, (int) (3.84f * point), true));
			accuracy.add(new StatAddFunction(StatEnum.MAGICAL_ACCURACY, (int) (6.00f * point), true));
            player.getGameStats().addEffect(this, accuracy);
        } else if (point == 0) {
            accuracy.clear();
			accuracy.add(new StatAddFunction(StatEnum.ACCURACY, (int) (7.84f * point), false));
			accuracy.add(new StatAddFunction(StatEnum.PHYSICAL_ACCURACY, (int) (3.84f * point), false));
			accuracy.add(new StatAddFunction(StatEnum.MAGICAL_ACCURACY, (int) (6.00f * point), false));
            player.getGameStats().endEffect(this);
        }
    }
	
	public static Accuracy getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
	
    private static class NewSingletonHolder {
        private static final Accuracy INSTANCE = new Accuracy();
    }
}
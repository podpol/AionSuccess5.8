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
package com.aionemu.gameserver.services.conquerors;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatRateFunction;
import com.aionemu.gameserver.model.templates.serial_killer.RankPenaltyAttr;
import com.aionemu.gameserver.model.templates.serial_killer.RankRestriction;
import com.aionemu.gameserver.skillengine.change.Func;

import java.util.ArrayList;
import java.util.List;

public class ConquerorBuffs implements StatOwner
{
	private List<IStatFunction> functions = new ArrayList<IStatFunction>();
	private RankRestriction rankRestriction;
	
	public void applyEffect(Player player, int rank) {
		if (rank == 0) {
			return;
		}
		rankRestriction = DataManager.SERIAL_KILLER_DATA.getRankRestriction(rank);
		if (hasDebuff()) {
			endEffect(player);
		} for (RankPenaltyAttr rankPenaltyAttr : rankRestriction.getPenaltyAttr()) {
			if (rankPenaltyAttr.getFunc().equals(Func.PERCENT)) {
				functions.add(new StatRateFunction(rankPenaltyAttr.getStat(), rankPenaltyAttr.getValue(), true));
			} else {
				functions.add(new StatAddFunction(rankPenaltyAttr.getStat(), rankPenaltyAttr.getValue(), true));
			}
		}
		player.getGameStats().addEffect(this, functions);
	}
	
	public boolean hasDebuff() {
		return !functions.isEmpty();
	}
	
	public void endEffect(Player player) {
		functions.clear();
		player.getGameStats().endEffect(this);
	}
}
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
package com.aionemu.gameserver.services.protectors;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatRateFunction;
import com.aionemu.gameserver.model.templates.serial_guard.GuardRankPenaltyAttr;
import com.aionemu.gameserver.model.templates.serial_guard.GuardRankRestriction;
import com.aionemu.gameserver.model.templates.serial_guard.GuardTypePenaltyAttr;
import com.aionemu.gameserver.model.templates.serial_guard.GuardTypeRestriction;
import com.aionemu.gameserver.skillengine.change.Func;

import java.util.ArrayList;
import java.util.List;

public class ProtectorBuffs implements StatOwner
{
	private GuardRankRestriction guardRankRestriction;
	private GuardTypeRestriction guardTypeRestriction;
	private List<IStatFunction> functions = new ArrayList<IStatFunction>();
	
	public void applyRankEffect(Player player, int rank) {
		if (rank == 0) {
			return;
		}
		guardRankRestriction = DataManager.SERIAL_GUARD_DATA.getGuardRankRestriction(rank);
		if (hasDebuff()) {
			endEffect(player);
		} for (GuardRankPenaltyAttr guardrankPenaltyAttr : guardRankRestriction.getGuardPenaltyAttr()) {
			if (guardrankPenaltyAttr.getFunc().equals(Func.PERCENT)) {
				functions.add(new StatRateFunction(guardrankPenaltyAttr.getStat(), guardrankPenaltyAttr.getValue(), true));
			} else {
				functions.add(new StatAddFunction(guardrankPenaltyAttr.getStat(), guardrankPenaltyAttr.getValue(), true));
			}
		}
		player.getGameStats().addEffect(this, functions);
	}
	
	public void applyTypeEffect(Player player, int type) {
		if (type == 0) {
			return;
		}
		guardTypeRestriction = DataManager.SERIAL_GUARD_DATA.getGuardTypeRestriction(type);
		if (hasDebuff()) {
			endEffect(player);
		} for (GuardTypePenaltyAttr guardtypePenaltyAttr : guardTypeRestriction.getGuardPenaltyAttr()) {
			if (guardtypePenaltyAttr.getFunc().equals(Func.PERCENT)) {
				functions.add(new StatRateFunction(guardtypePenaltyAttr.getStat(), guardtypePenaltyAttr.getValue(), true));
			} else {
				functions.add(new StatAddFunction(guardtypePenaltyAttr.getStat(), guardtypePenaltyAttr.getValue(), true));
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
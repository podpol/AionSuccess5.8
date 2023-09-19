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
package com.aionemu.gameserver.model.bonus_service;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatRateFunction;
import com.aionemu.gameserver.model.templates.bonus_service.PlayersBonusPenaltyAttr;
import com.aionemu.gameserver.model.templates.bonus_service.PlayersBonusServiceAttr;
import com.aionemu.gameserver.skillengine.change.Func;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

public class PlayersBonus implements StatOwner
{
	private static final Logger log = LoggerFactory.getLogger(PlayersBonus.class);
	private List<IStatFunction> functions = new ArrayList<IStatFunction>();
	private PlayersBonusServiceAttr playersServiceBonusattr;
	
	public PlayersBonus(int buffId) {
		playersServiceBonusattr = DataManager.PLAYERS_BONUS_DATA.getInstanceBonusattr(buffId);
	}
	
	public void applyEffect(Player player, int buffId) {
		if (playersServiceBonusattr == null) {
			return;
		} for (PlayersBonusPenaltyAttr playersBonusPenaltyAttr: playersServiceBonusattr.getPenaltyAttr()) {
			if (playersBonusPenaltyAttr.getFunc().equals(Func.PERCENT)) {
				functions.add(new StatRateFunction(playersBonusPenaltyAttr.getStat(), playersBonusPenaltyAttr.getValue(), true));
			} else {
				functions.add(new StatAddFunction(playersBonusPenaltyAttr.getStat(), playersBonusPenaltyAttr.getValue(), true));
			}
		}
		player.getGameStats().addEffect(this, functions);
	}
	
	public void endEffect(Player player, int buffId) {
		functions.clear();
		player.setPlayersBonusId(1);
		player.getGameStats().endEffect(this);
	}
}
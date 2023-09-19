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
import com.aionemu.gameserver.model.templates.bonus_service.BonusPenaltyAttr;
import com.aionemu.gameserver.model.templates.bonus_service.BonusServiceAttr;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ICON_INFO;
import com.aionemu.gameserver.skillengine.change.Func;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

public class ServiceBuff implements StatOwner
{
	private static final Logger log = LoggerFactory.getLogger(ServiceBuff.class);
	private List<IStatFunction> functions = new ArrayList<IStatFunction>();
	private BonusServiceAttr serviceBonusAttr;
	
	public ServiceBuff(int buffId) {
		serviceBonusAttr = DataManager.SERVICE_BUFF_DATA.getInstanceBonusattr(buffId);
	}
	
	public void applyEffect(Player player, int buffId) {
		if (serviceBonusAttr == null) {
			return;
		} for (BonusPenaltyAttr bonusPenaltyAttr: serviceBonusAttr.getPenaltyAttr()) {
			if (bonusPenaltyAttr.getFunc().equals(Func.PERCENT)) {
				functions.add(new StatRateFunction(bonusPenaltyAttr.getStat(), bonusPenaltyAttr.getValue(), true));
			} else {
				functions.add(new StatAddFunction(bonusPenaltyAttr.getStat(), bonusPenaltyAttr.getValue(), true));
			}
		}
		player.setBonus(true);
		player.getGameStats().addEffect(this, functions);
		PacketSendUtility.sendPacket(player, new SM_ICON_INFO(buffId, true));
	}
	
	public void endEffect(Player player, int buffId) {
		functions.clear();
		player.setBonus(false);
		player.getGameStats().endEffect(this);
		PacketSendUtility.sendPacket(player, new SM_ICON_INFO(buffId, false));
	}
}
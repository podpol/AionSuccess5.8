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
package com.aionemu.gameserver.utils.audit;

import com.aionemu.gameserver.configs.main.PunishmentConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.BannedMacManager;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUIT_RESPONSE;
import com.aionemu.gameserver.network.loginserver.LoginServer;
import com.aionemu.gameserver.services.PunishmentService;

/**
 * @author synchro2
 */
public class AutoBan {

	protected static void punishment(Player player, String message) {

		String reason = "AUTO " +message;
		String address = player.getClientConnection().getMacAddress();
		String accountIp = player.getClientConnection().getIP();
		int accountId = player.getClientConnection().getAccount().getId();
		int playerId = player.getObjectId();
		int time = PunishmentConfig.PUNISHMENT_TIME;
		int minInDay = 1440;
		int dayCount = (int)(Math.floor((double)(time/minInDay)));

		switch (PunishmentConfig.PUNISHMENT_TYPE) {
			case 1:
				player.getClientConnection().close(new SM_QUIT_RESPONSE(), false);
				break;
			case 2:
				PunishmentService.banChar(playerId, dayCount, reason);
				break;
			case 3:
				LoginServer.getInstance().sendBanPacket((byte)1, accountId, accountIp, time, 0);
				break;
			case 4:
				LoginServer.getInstance().sendBanPacket((byte)2, accountId, accountIp, time, 0);
				break;
			case 5:
				player.getClientConnection().closeNow();
				BannedMacManager.getInstance().banAddress(address, System.currentTimeMillis() + time * 60000, reason);
				break;
		}
	}
}
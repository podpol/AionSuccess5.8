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
package com.aionemu.gameserver.services.player.CreativityPanel;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_CREATIVITY_POINTS_APPLY;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Agility;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Health;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Knowledge;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Power;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Precision;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Will;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class CreativityStatsService {

	public void onEssenceApply(Player player, int type, int size, int id, int point) {
		if (player.isArchDaeva()) {
			player.getCP().addPoint(player, id, point);
			switch (id) {
				case 1:
					player.setCPSlot1(point);
					Power.getInstance().onChange(player, point);
					break;
				case 2:
					player.setCPSlot2(point);
					Health.getInstance().onChange(player, point);
					break;
				case 3:
					player.setCPSlot3(point);
					Agility.getInstance().onChange(player, point);
					break;
				case 4:
					player.setCPSlot4(point);
					Precision.getInstance().onChange(player, point);
					break;
				case 5:
					player.setCPSlot5(point);
					Knowledge.getInstance().onChange(player, point);
					break;
				case 6:
					player.setCPSlot6(point);
					Will.getInstance().onChange(player, point);
					break;
			}
			PacketSendUtility.sendPacket(player, new SM_CREATIVITY_POINTS_APPLY(type, size, id, point));
		}
	}

	public static CreativityStatsService getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	private static class NewSingletonHolder {

		private static final CreativityStatsService INSTANCE = new CreativityStatsService();
	}
}

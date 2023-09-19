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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team.legion.LegionHistory;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LEGION_TABS;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author Simple, xTz
 */
public class CM_LEGION_TABS extends AionClientPacket {

	private static final Logger log = LoggerFactory.getLogger(CM_LEGION_TABS.class);

	private int page;
	private int tab;

	public CM_LEGION_TABS(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		page = readD();
		tab = readC();
	}

	@Override
	protected void runImpl() {
		Player activePlayer = getConnection().getActivePlayer();
		
		if(activePlayer.getLegion() != null) {
			
			/**
			 * Max page is 16 for legion history
			 */
			if (page > 16)
				return;

			switch (tab) {
				/**
				 * History Tab
				 */
				case 0: // legion history
				case 2: // legion WH history
					Collection<LegionHistory> history = activePlayer.getLegion().getLegionHistoryByTabId(tab);
					/**
					 * If history size is less than page*8 return
					 */
					if (history.size() < page * 8)
						return;
					if (!history.isEmpty())
						PacketSendUtility.sendPacket(activePlayer, new SM_LEGION_TABS(history, page, tab));
					break;
				/**
				 * Reward Tab
				 */
				case 1:
					//TODO Reward Tab Page
					break;
			}
		}
		else
			log.warn("Player "+activePlayer.getName()+" was requested null legion");
	}
}

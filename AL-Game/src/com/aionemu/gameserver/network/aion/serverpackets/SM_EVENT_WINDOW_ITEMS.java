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
package com.aionemu.gameserver.network.aion.serverpackets;

import java.sql.Timestamp;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerEventsWindowDAO;
import com.aionemu.gameserver.model.templates.event.EventsWindow;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class SM_EVENT_WINDOW_ITEMS extends AionServerPacket {

	private static final Logger log = LoggerFactory.getLogger(SM_EVENT_WINDOW_ITEMS.class);
	private Collection<EventsWindow> active_events_packet;

	public SM_EVENT_WINDOW_ITEMS(Collection<EventsWindow> active_events_packet) {
		this.active_events_packet = active_events_packet;
	}

	@Override
	protected void writeImpl(AionConnection aionConnection) {
		int playerAccountId = aionConnection.getActivePlayer().getPlayerAccount().getId();
		PlayerEventsWindowDAO playerEventsWindowDAO = DAOManager.getDAO(PlayerEventsWindowDAO.class);
		writeC(1); // Do not Change !!!
		writeH(active_events_packet.size());
		for (EventsWindow eventsWindow : active_events_packet) {
			int dbRecivedCount = playerEventsWindowDAO.getRewardRecivedCount(playerAccountId, eventsWindow.getId());
			int elapsed = playerEventsWindowDAO.getElapsed(playerAccountId, eventsWindow.getId());
			int displayTime = (eventsWindow.getRemainingTime() - elapsed);
			log.info("event id " + eventsWindow.getId() + " remain " + eventsWindow.getRemainingTime() + " start-time " + new Timestamp(eventsWindow.getPeriodStart().getMillis()).getTime() / 1000 + " end-time " + new Timestamp(eventsWindow.getPeriodEnd().getMillis()).getTime() / 1000 + " total size " + active_events_packet.size());
			writeD(eventsWindow.getId()); // Id
			writeD(dbRecivedCount); // reward recived count
			writeD(displayTime * 60); //Displayed Remaining Time
			writeD(0); // Do not Change !!!
			writeD(eventsWindow.getMaxCountOfDay());// This is Max Count of Day
			writeD((int) (System.currentTimeMillis() / 1000)); // PlayerLoginTime
			writeC(1); // Do not Change !!!
			writeD(5); // Do not Change !!!
			writeD(1); // Do not Change !!!
			writeC(-104); // Do not Change !!!
			writeC(98); // Do not Change !!!
			writeC(21); // Do not Change !!!
			writeC(0); // Do not Change !!!
			writeD(displayTime * 60); // Remaining Time
			writeD(eventsWindow.getItemId());  // ItemId
			writeQ(eventsWindow.getCount()); // ItemCount
			writeD(eventsWindow.getMaxCountOfDay()); // This is Max Count of Day
			writeD((int) (eventsWindow.getPeriodStart().getMillis() / 1000)); // Period Start TimeStamp
			writeD(0);
			writeD((int) (eventsWindow.getPeriodEnd().getMillis() / 1000)); // Period End TimeSTamp
			writeD(0);
			writeD(0);//Does something
			writeD(0); // If player has this Item already in inventory it's ItemId
			writeD(1090157056); // Do not Change !!!
			writeD(eventsWindow.getMinLevel()); // StartLevel
			writeD(eventsWindow.getMaxLevel()); // EndLevel
			writeD(-1);// Do not Change !!!
			writeB(new byte[84]); // Do not Change !!!
			writeD(-1);// Do not Change !!!
			writeB(new byte[16]);
			writeD(2147483647);// Do not Change !!!
			writeB(new byte[7]);// Do not Change !!!
			writeD(-1);// Do not Change !!!
			writeD(0);// Do not Change !!!
		}
	}
}
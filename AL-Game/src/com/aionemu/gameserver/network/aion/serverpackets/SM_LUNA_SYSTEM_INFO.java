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

import java.util.Calendar;
import java.util.List;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Made by Ghostfur (Aion-Unique)
 */
public class SM_LUNA_SYSTEM_INFO extends AionServerPacket {

	private int actionId;
	@SuppressWarnings("unused")
	private long points;
	@SuppressWarnings("unused")
	private int keys;
	private int costId;
	@SuppressWarnings("unused")
	private int entryCount;
	private int tableId;
	@SuppressWarnings("unused")
	private List<Integer> idList;
	private List<Integer> randomDailyCraft;
	@SuppressWarnings("unused")
	private List<Integer> lists;
	private int startTime = ((int) (Calendar.getInstance().getTimeInMillis() / 1000));
	private int endTime = (startTime + 86400);

	public SM_LUNA_SYSTEM_INFO(int actionId) {
		this.actionId = actionId;
	}

	public SM_LUNA_SYSTEM_INFO(int actionId, long points) {
		this.actionId = actionId;
		this.points = points;
	}

	public SM_LUNA_SYSTEM_INFO(int actionId, int keys) {
		this.actionId = actionId;
		this.keys = keys;
	}

	public SM_LUNA_SYSTEM_INFO(int actionId, int tableId, List<Integer> lists) {
		this.actionId = actionId;
		this.tableId = tableId;
		switch (tableId) {
			case 0:
				this.idList = lists;
				break;
			case 1:
				this.randomDailyCraft = lists;
				break;
		}
	}

	public SM_LUNA_SYSTEM_INFO(int actionId, int tableId, int costId) {
		this.actionId = actionId;
		this.tableId = tableId;
		this.costId = costId;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(actionId);// actionid
		switch (actionId) {
			case 0:// luna point handler id
				writeQ(con.getAccount().getLuna());
				break;
			case 1:// taki advanture update NOTE also send after free box craft (1,1,1,1)
				writeH(tableId);// size? subaction
				writeD(costId);
				if (costId == 79) {//First run on Dice game is 78 after that its 79 + last D packet get's +1 for every try
					writeD(con.getActivePlayer().getLunaDiceGameTry());
				} else {
					writeD(1);
				}
				break;
			case 2:
				writeC(tableId);// tabId
				switch (tableId) {
					case 0: // NormalCraft (Disabled from NC / GF)
//						writeD(startTime);// Start time
//						writeD(0);
//						writeD(endTime);// End time
//						writeD(0);
//						writeH(idList.size());// size
//						for (int i = 0; i < idList.size(); i++) {
//							writeD(idList.get(i));// luna recipe id
//						}
						writeD(startTime);// Start time
						writeD(0);
						writeD(-1);
						writeD(2147483647);// End Time
						writeH(0);// size
						break;
					case 1: // DailyCraft
						writeD(startTime);
						writeD(0); // test
						writeD(endTime);
						writeD(0);
						writeH(randomDailyCraft.size());// size
						for (Integer daily : randomDailyCraft) {
							writeD(daily);
							System.out.println("Daily: " + daily);
						}
						break;
				}
				break;
			case 4:// munirunerk's keys
				writeD(con.getActivePlayer().getMuniKeys());
				break;
			case 5:// luna consume point spent
				writeD(con.getActivePlayer().getLunaConsumePoint());
				break;
			case 6:// update taki's mission?
				break;
			case 7:
				writeC(0);
				writeH(100);
				break;
			case 8:// Updated for 5.8 on 16.05.2018
				writeH(tableId <= 5 ? tableId : (tableId - 1));
				writeD(10 + tableId);
				writeC((tableId + 10) == 16 ? 1 : 0);
				break;
			case 9:
				writeH(-1);
				writeC(0);
		}
	}
}
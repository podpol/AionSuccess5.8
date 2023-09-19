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
package com.aionemu.gameserver.model.team.legion;

import java.sql.Timestamp;


/**
 * @author Simple, xTz
 */
public class LegionHistory {

	private LegionHistoryType legionHistoryType;
	private String name = "";
	private Timestamp time;
	private int tabId;
	private String description = "";

	public LegionHistory(LegionHistoryType legionHistoryType, String name, Timestamp time, int tabId, String description) {
		this.legionHistoryType = legionHistoryType;
		this.name = name;
		this.time = time;
		this.tabId = tabId;
		this.description = description;
	}

	public LegionHistoryType getLegionHistoryType() {
		return legionHistoryType;
	}

	public String getName() {
		return name;
	}

	public Timestamp getTime() {
		return time;
	}

	public int getTabId() {
		return tabId;
	}

	public String getDescription() {
		return description;
	}
}

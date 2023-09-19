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
package com.aionemu.gameserver.model.account;

/**
 * @author nrg
 */
public class CharacterBanInfo {

	private int playerId;
	private long start;
	private long end;
	private String reason;
	
	public CharacterBanInfo(int playerId, long start, long duration, String reason) {
		this.playerId = playerId;
		this.start = start;
		this.end = duration + start;
		this.reason = (reason.equals("") ? "You are suspected to have violated the server's rules" : reason);
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}
	
	/**
	 * @return the end
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
}

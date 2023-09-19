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

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class SM_ATREIAN_PASSPORT extends AionServerPacket {

	private int month;
	private int year;
	private int passportId;
	private int countCollected;
	private int lastStampRecived;
	private boolean hasCollected;

	public SM_ATREIAN_PASSPORT(int passportId, int countCollected, int lastStampRecived, boolean hasCollected, int month, int year) {
		this.month = month;
		this.year = year;
		this.passportId = passportId;
		this.countCollected = countCollected;
		this.lastStampRecived = lastStampRecived;
		this.hasCollected = hasCollected;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeH(year);
		writeH(month);
		writeH(8);// can be variable
		//TODO
		writeH(2); // TODO PassportCount
		//TODO
		writeD(passportId);
		writeD(lastStampRecived);
		writeD(countCollected);
		writeC(hasCollected ? 0 : 1);
		//TODO Aniversity (9 0 0 1 = Get Aniversity)
		writeD(9);
		writeD(0);
		writeD(0);
		writeC(0);
	}
}
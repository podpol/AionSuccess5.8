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
 * @author Cura
 */
public class SM_CAPTCHA extends AionServerPacket {

	private int type;
	private int count;
	private int size;
	private byte[] data;
	private boolean isCorrect;
	private int banTime;

	/**
	 * @param count
	 * @param data
	 */
	public SM_CAPTCHA(int count, byte[] data) {
		this.type = 1;
		this.count = count;
		this.size = data.length;
		this.data = data;
	}

	/**
	 * @param isCorrect
	 */
	public SM_CAPTCHA(boolean isCorrect, int banTime) {
		this.type = 3;
		this.isCorrect = isCorrect;
		this.banTime = banTime;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(type);

		switch (type) {
			case 0x01:
				writeC(count);
				writeD(size);
				writeB(data);
				break;
			case 0x03:
				writeH(isCorrect ? 1 : 0);

				// time setting can't be extracted (retail server default value:3000 sec)
				writeD(banTime);
				break;
		}
	}
}

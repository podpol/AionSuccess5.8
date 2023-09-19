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
 * @author cura
 */
public class SM_PLAYER_MOVE extends AionServerPacket {

	private float x;
	private float y;
	private float z;
	private byte heading;

	public SM_PLAYER_MOVE(float x, float y, float z, byte heading) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.heading = heading;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeF(x);
		writeF(y);
		writeF(z);
		writeC(heading);
	}
}

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
 * @author Ranastic
 */

public class SM_BUTLER_SALUTE extends AionServerPacket
{
	private int playerObjId;
	private int isInside;
	private int unk1;
	private int unk2;
	private int unk3;
	private int unk4;
	
	public SM_BUTLER_SALUTE(int unk1, int unk2, int unk3, int unk4, int playerObjId, int isInside) {
		this.unk1 = unk1;
		this.unk2 = unk2;
		this.unk3 = unk3;
		this.unk4 = unk4;
		this.playerObjId = playerObjId;
		this.isInside = isInside;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(unk1);
		writeC(unk2);
		writeD(unk3);
		writeC(unk4);
		writeD(playerObjId);
		writeC(isInside);
	}
}
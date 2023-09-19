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
 * @author ATracer
 */
public class SM_USE_OBJECT extends AionServerPacket {

	private int playerObjId;
	private int targetObjId;
	private int time;
	private int actionType;

	public SM_USE_OBJECT(int playerObjId, int targetObjId, int time, int actionType) {
		super();
		this.playerObjId = playerObjId;
		this.targetObjId = targetObjId;
		this.time = time;
		this.actionType = actionType;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(playerObjId);
		writeD(targetObjId);
		writeD(time);
		writeC(actionType);
	}
}

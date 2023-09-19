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

import com.aionemu.gameserver.GameServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Falke_34
 * @Rework Xnemonix
 */
public class SM_CREATIVITY_POINTS_APPLY extends AionServerPacket {

	Logger log = LoggerFactory.getLogger(SM_CREATIVITY_POINTS_APPLY.class);

	private int type;
	private int size;
	private int id;
	private int slotPoint;

	public SM_CREATIVITY_POINTS_APPLY(int type, int size) {
		this.type = type;
		this.size = size;
	}

	public SM_CREATIVITY_POINTS_APPLY(int type) {
		this.type = type;
	}

	public SM_CREATIVITY_POINTS_APPLY(int type, int id, int slotPoint) {
		this.id = id;
		this.slotPoint = slotPoint;
	}

	public SM_CREATIVITY_POINTS_APPLY(int type, int size, int id, int slotPoint) {
		this.type = type;
		this.size = size;
		this.id = id;
		this.slotPoint = slotPoint;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeH(0x01);
		writeH(0x01); //No Loop should only return 1
		switch (type) {
			case 0:
				writeD(id);
				writeH(slotPoint);
				break;
			case 1:
				writeD(id);
				writeH(slotPoint);
				break;
		}
	}
}

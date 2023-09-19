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


import com.aionemu.gameserver.controllers.RVController;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import javolution.util.FastMap;

/**
 * @author Sweetkr
 */
public class SM_RIFT_ANNOUNCE extends AionServerPacket {

	private int actionId;
	private RVController rift;
	private FastMap<Integer, Integer> rifts;
	private int objectId;
	private int gelkmaros, inggison;

	/**
	 * Rift announce packet
	 *
	 * @param player
	 */
	public SM_RIFT_ANNOUNCE(FastMap<Integer, Integer> rifts) {
		this.actionId = 0;
		this.rifts = rifts;
	}

	public SM_RIFT_ANNOUNCE(boolean gelkmaros, boolean inggison) {
		this.gelkmaros = gelkmaros ? 1 : 0;
		this.inggison = inggison ? 1 : 0;
		this.actionId = 1;
	}

	/**
	 * Rift announce packet
	 *
	 * @param player
	 */
	public SM_RIFT_ANNOUNCE(RVController rift, boolean isMaster) {
		this.rift = rift;
		this.actionId = isMaster ? 2 : 3;
	}

	/**
	 * Rift despawn
	 *
	 * @param objectId
	 */
	public SM_RIFT_ANNOUNCE(int objectId) {
		this.objectId = objectId;
		this.actionId = 5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		switch (actionId) {
			case 0:
				writeH(0x57);
				writeC(actionId);
				for (int value : rifts.values()) {
					writeD(value);
				}
			break;
			case 1:
				writeH(0x09);
				writeC(actionId);
				writeD(gelkmaros);
				writeD(inggison);
			break;
			case 2:
				writeH(0x39);
				writeC(actionId);
				writeD(rift.getOwner().getObjectId());
				writeD(rift.getMaxEntries());
				writeD(rift.getRemainTime());
				writeD(rift.getMinLevel());
				writeD(rift.getMaxLevel());
				writeF(rift.getOwner().getX());
				writeF(rift.getOwner().getY());
				writeF(rift.getOwner().getZ());
				writeC(rift.isVortex() ? 1 : 0);
				writeC(rift.isMaster() ? 1 : 0);
				writeD(rift.getOwner().getWorldId());
				writeD(rift.getAbyssPoint());
			break;
			case 3:
				writeH(0x15);
                writeC(actionId);
                writeD(rift.getOwner().getObjectId());
                writeD(rift.getUsedEntries());
                writeD(rift.getRemainTime());
                writeC(rift.isVortex() ? 1 : 0);
                writeC(rift.isMaster() ? 1 : 0);
				writeD(rift.getAbyssPoint());
			break;
			case 4:
				writeH(0x07);
				writeC(actionId);
				writeD(objectId);
				writeC(rift.isVortex() ? 1 : 0);
                writeC(rift.isMaster() ? 1 : 0);
			break;
			case 5:
                writeH(0x05);
                writeC(actionId);
                writeD(0x00);
            break;
		}
	}
}
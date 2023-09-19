package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.town.Town;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.Map;

public class SM_TOWNS_LIST extends AionServerPacket
{
	private Map<Integer, Town> towns;
	
	public SM_TOWNS_LIST(Map<Integer, Town> towns) {
		this.towns = towns;
	}
	
	protected void writeImpl(AionConnection con) {
		writeH(towns.size());
		for (Town town : towns.values()) {
			writeD(town.getId());
			writeD(town.getLevel());
			writeD((int) (town.getLevelUpDate().getTime() / 1000));
		}
	}
}
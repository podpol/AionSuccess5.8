package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.HouseObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import javolution.util.FastList;

public class SM_HOUSE_OBJECTS extends AionServerPacket
{
	Player player;
	
	public SM_HOUSE_OBJECTS(Player player) {
		this.player = player;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		FastList<HouseObject<?>> objects = player.getHouseRegistry().getSpawnedObjects();
		writeH(objects.size());
		for (HouseObject<?> obj : objects) {
			writeD(obj.getObjectTemplate().getTemplateId());
			writeF(obj.getX());
			writeF(obj.getY());
			writeF(obj.getZ());
		}
	}
}
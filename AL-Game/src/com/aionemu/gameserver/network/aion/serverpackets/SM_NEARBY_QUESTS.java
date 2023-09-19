package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.HashMap;
import java.util.Map.Entry;

public class SM_NEARBY_QUESTS extends AionServerPacket
{
	private HashMap<Integer, Integer> nearbyQuestList;
	
	public SM_NEARBY_QUESTS(HashMap<Integer, Integer> nearbyQuestList) {
        this.nearbyQuestList = nearbyQuestList;
    }
	
	@Override
    protected void writeImpl(AionConnection con) {
        if (nearbyQuestList == null || con.getActivePlayer() == null) {
            return;
        }
        writeC(0);
        writeH(-nearbyQuestList.size() & 0xFFFF);
        for (Entry<Integer, Integer> nearbyQuest : nearbyQuestList.entrySet()) {
            if (nearbyQuest.getValue() > 0) {
                writeH(nearbyQuest.getKey());
                writeH(0x02);
            } else {
                writeD(nearbyQuest.getKey());
            }
        }
    }
}
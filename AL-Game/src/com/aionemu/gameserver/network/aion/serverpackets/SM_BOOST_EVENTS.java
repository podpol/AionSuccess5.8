package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.templates.event.BoostEvents;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.Map;

/**
 * Created by wanke on 01/02/2017.
 */

public class SM_BOOST_EVENTS extends AionServerPacket
{
    private Map<Integer, BoostEvents> boostEvents;

    private int buffId;
    private int buffValue;
    long eventStartTime;
    long eventEndTime;
	
    public SM_BOOST_EVENTS(int buffId, int buffValue, long eventStartTime, long eventEndTime) {
        this.buffId = buffId;
        this.buffValue = buffValue;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeH(9); //buff Count dont tuch
        writeC(buffId); //buff Id
        writeC(1); //enabledCount
        writeD((int) eventStartTime); //start
        writeD(0);
        writeD((int) eventEndTime); //end
        writeD(0);
        writeD(buffValue); //boost value
        writeQ(-1);
        writeD(0);
        writeD(0);
    }
}
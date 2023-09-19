package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by Ace on 14/02/2017.
 */

public class SM_FLAG_INFO extends AionServerPacket
{
    int count;
    private Creature _npc;
    private NpcTemplate npcTemplate;
    private int npcId;
	
    public SM_FLAG_INFO(int count, Npc npc) {
        this.count = count;
        this._npc = npc;
        npcId = npc.getNpcId();
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeH(count);
        if (_npc != null) {
            writeD(npcId);
            writeD(_npc.getObjectId());
            writeD(_npc.getLifeStats().getCurrentHp());
            writeD(_npc.getLifeStats().getMaxHp());
            writeF(_npc.getX());
            writeF(_npc.getY());
            writeF(_npc.getZ());
        } else {
            writeD(0);
            writeD(0);
            writeD(0);
            writeD(0);
            writeF(0);
            writeF(0);
            writeF(0);
        }
    }
}
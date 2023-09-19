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
import javolution.util.FastList;

/**
 * @author Rinzler
 */ 

public class SM_DISPUTE_LAND extends AionServerPacket
{
	boolean active;
	FastList<Integer> worlds;
    
	public SM_DISPUTE_LAND(FastList<Integer> worlds, boolean active) {
        this.worlds = worlds;
        this.active = active;
    }
	
	@Override
    protected void writeImpl(AionConnection con) {
        writeH(worlds.size());
        for (int world: worlds) {
            writeD(active ? 0x02 : 0x01);
            writeD(world);
            writeQ(0x00);
            writeQ(0x00);
            writeQ(0x00);
            writeQ(0x00);
        }
    }
}
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

public class SM_A_STATION_MOVE extends AionServerPacket
{
    private int currentServerId;
    private int newServerId;
    private int mapId;
	
    public SM_A_STATION_MOVE(int currentServer, int newServerId, int mapId) {
        this.currentServerId = currentServer;
        this.newServerId = newServerId;
        this.mapId = mapId;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        writeD(newServerId);
        writeD(currentServerId);
        writeC(0);
        writeD(mapId);
    }
}
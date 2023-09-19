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

import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ranastic
 */

public class SM_A_STATION extends AionServerPacket
{
    private boolean isFirst = false;
    private int currentServer = 0;
    private int newServerId = 0;
	
    public SM_A_STATION(int currentServer, int newServerId, boolean first) {
        this.currentServer = currentServer;
        this.newServerId = newServerId;
        this.isFirst = first;
    }
	
    @Override
    protected void writeImpl(AionConnection con) {
        Player player = con.getActivePlayer();
        writeD(newServerId);
        writeD(currentServer);
        writeD(player.getObjectId());
        if (isFirst) {
            writeD(NetworkConfig.GAMESERVER_ID);
        } else {
            writeD(newServerId);
        }
        writeD(0);
        writeD(0);
    }
}
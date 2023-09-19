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
package com.aionemu.gameserver.network.chatserver.serverpackets;

import com.aionemu.gameserver.configs.network.IPConfig;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.network.chatserver.ChatServerConnection;
import com.aionemu.gameserver.network.chatserver.CsServerPacket;

public class SM_CS_AUTH extends CsServerPacket
{
    public SM_CS_AUTH() {
        super(0x00);
    }
	
    @Override
    protected void writeImpl(ChatServerConnection con) {
        writeC(NetworkConfig.GAMESERVER_ID);
        writeC(IPConfig.getDefaultAddress().length);
        writeB(IPConfig.getDefaultAddress());
        writeS(NetworkConfig.CHAT_PASSWORD);
    }
}
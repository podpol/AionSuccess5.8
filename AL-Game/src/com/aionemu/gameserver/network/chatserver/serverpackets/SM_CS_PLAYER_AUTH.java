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

import com.aionemu.gameserver.network.chatserver.ChatServerConnection;
import com.aionemu.gameserver.network.chatserver.CsServerPacket;

public class SM_CS_PLAYER_AUTH extends CsServerPacket
{
    private int playerId;
    private String playerLogin;
    private String nick;
	
    public SM_CS_PLAYER_AUTH(int playerId, String playerLogin, String nick) {
        super(0x01);
        this.playerId = playerId;
        this.playerLogin = playerLogin;
        this.nick = nick;
    }
	
    @Override
    protected void writeImpl(ChatServerConnection con) {
        writeD(playerId);
        writeS(playerLogin);
        writeS(nick);
    }
}
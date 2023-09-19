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
package com.aionemu.gameserver.network.chatserver.clientpackets;

import com.aionemu.gameserver.network.chatserver.CsClientPacket;
import com.aionemu.gameserver.services.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_CS_PLAYER_AUTH_RESPONSE extends CsClientPacket
{
    protected static final Logger log = LoggerFactory.getLogger(CM_CS_PLAYER_AUTH_RESPONSE.class);
    private int playerId;
    private byte[] token;
	
    public CM_CS_PLAYER_AUTH_RESPONSE(int opcode) {
        super(opcode);
    }
	
    @Override
    protected void readImpl() {
        playerId = readD();
        int tokenLenght = readC();
        token = readB(tokenLenght);
    }
	
    @Override
    protected void runImpl() {
        ChatService.playerAuthed(playerId, token);
    }
}
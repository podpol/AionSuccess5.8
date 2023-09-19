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

import com.aionemu.commons.utils.ExitCode;
import com.aionemu.gameserver.network.chatserver.ChatServerConnection.State;
import com.aionemu.gameserver.network.chatserver.CsClientPacket;
import com.aionemu.gameserver.network.chatserver.serverpackets.SM_CS_AUTH;
import com.aionemu.gameserver.services.ChatService;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_CS_AUTH_RESPONSE extends CsClientPacket
{
    protected static final Logger log = LoggerFactory.getLogger(CM_CS_AUTH_RESPONSE.class);
    private int response;
    private byte[] ip;
    private int port;
	
    public CM_CS_AUTH_RESPONSE(int opcode) {
        super(opcode);
    }
	
    @Override
    protected void readImpl() {
        response = readC();
        ip = readB(4);
        port = readH();
    }
	
    @Override
    protected void runImpl() {
        switch (response) {
            case 0: //Authed
                log.info("GameServer authed successfully IP : " + (ip[0] & 0xFF) + "." + (ip[1] & 0xFF) + "." + (ip[2] & 0xFF) + "." + (ip[3] & 0xFF) + " Port: " + port);
                getConnection().setState(State.AUTHED);
                ChatService.setIp(ip);
                ChatService.setPort(port);
            break;
            case 1: //Not Authed
                log.error("GameServer is not authenticated at ChatServer side");
                System.exit(ExitCode.CODE_ERROR);
            break;
            case 2: //Already Registered
                log.info("GameServer is already registered at ChatServer side! trying again...");
                ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                        CM_CS_AUTH_RESPONSE.this.getConnection().sendPacket(new SM_CS_AUTH());
                    }
                }, 10000);
            break;
        }
    }
}
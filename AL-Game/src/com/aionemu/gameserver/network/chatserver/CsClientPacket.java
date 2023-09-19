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
package com.aionemu.gameserver.network.chatserver;

import com.aionemu.commons.network.packet.BaseClientPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CsClientPacket extends BaseClientPacket<ChatServerConnection> implements Cloneable
{
    private static final Logger log = LoggerFactory.getLogger(CsClientPacket.class);
	
    protected CsClientPacket(int opcode) {
        super(opcode);
    }
	
    @Override
    public final void run() {
        try {
            runImpl();
        } catch (Throwable e) {
            log.warn("error handling ls (" + getConnection().getIP() + ") message " + this, e);
        }
    }
	
    protected void sendPacket(CsServerPacket msg) {
        getConnection().sendPacket(msg);
    }
	
    public CsClientPacket clonePacket() {
        try {
            return (CsClientPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
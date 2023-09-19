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

import com.aionemu.commons.network.AConnection;
import com.aionemu.commons.network.Dispatcher;
import com.aionemu.gameserver.network.chatserver.serverpackets.SM_CS_AUTH;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Deque;

public class ChatServerConnection extends AConnection
{
    private static final Logger log = LoggerFactory.getLogger(ChatServerConnection.class);
    public static enum State {
        CONNECTED,
        AUTHED
    }
	
    private final Deque<CsServerPacket> sendMsgQueue = new ArrayDeque<CsServerPacket>();
    private State state;
    private ChatServer chatServer;
    private CsPacketHandler csPacketHandler;
	
    public ChatServerConnection(SocketChannel sc, Dispatcher d, CsPacketHandler csPacketHandler) throws IOException {
        super(sc, d, 8192 * 2, 8192 * 2);
        this.chatServer = ChatServer.getInstance();
        this.csPacketHandler = csPacketHandler;
        state = State.CONNECTED;
        log.info("Connected to ChatServer!");
    }
	
    @Override
    protected void initialized() {
        this.sendPacket(new SM_CS_AUTH());
    }
	
    @Override
    public boolean processData(ByteBuffer data) {
        CsClientPacket pck = csPacketHandler.handle(data, this);
        if (pck != null && pck.read()) {
            ThreadPoolManager.getInstance().executeLsPacket(pck);
        }
        return true;
    }
	
    @Override
    protected final boolean writeData(ByteBuffer data) {
        synchronized (guard) {
            CsServerPacket packet = sendMsgQueue.pollFirst();
            if (packet == null) {
                return false;
            }
            packet.write(this, data);
            return true;
        }
    }
	
    @Override
    protected final long getDisconnectionDelay() {
        return 0;
    }
	
    @Override
    protected final void onDisconnect() {
        chatServer.chatServerDown();
    }
	
    @Override
    protected final void onServerClose() {
        close(/* packet, */true);
    }
	
    public final void sendPacket(CsServerPacket bp) {
        synchronized (guard) {
            if (isWriteDisabled()) {
                return;
            }
            sendMsgQueue.addLast(bp);
            enableWriteInterest();
        }
    }
	
    public final void close(CsServerPacket closePacket, boolean forced) {
        synchronized (guard) {
            if (isWriteDisabled()) {
                return;
            }
            log.info("sending packet: " + closePacket + " and closing connection after that.");
            pendingClose = true;
            isForcedClosing = forced;
            sendMsgQueue.clear();
            sendMsgQueue.addLast(closePacket);
            enableWriteInterest();
        }
    }
	
    public State getState() {
        return state;
    }
	
    public void setState(State state) {
        this.state = state;
    }
	
    @Override
    public String toString() {
        return "ChatServer " + getIP();
    }
}
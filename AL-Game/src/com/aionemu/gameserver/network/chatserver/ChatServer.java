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

import com.aionemu.commons.network.Dispatcher;
import com.aionemu.commons.network.NioServer;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.chatserver.serverpackets.SM_CS_PLAYER_AUTH;
import com.aionemu.gameserver.network.chatserver.serverpackets.SM_CS_PLAYER_LOGOUT;
import com.aionemu.gameserver.network.factories.CsPacketHandlerFactory;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ChatServer
{
    private static final Logger log = LoggerFactory.getLogger(ChatServer.class);
    private ChatServerConnection chatServer;
    private NioServer nioServer;
    private boolean serverShutdown = false;
	
    public static final ChatServer getInstance() {
        return SingletonHolder.instance;
    }
	
    private ChatServer() {
    }
	
    public void setNioServer(NioServer nioServer) {
        this.nioServer = nioServer;
    }
	
    public ChatServerConnection connect() {
        SocketChannel sc;
        for (; ; ) {
            chatServer = null;
            log.info("Connecting to ChatServer: " + NetworkConfig.CHAT_ADDRESS);
            try {
                sc = SocketChannel.open(NetworkConfig.CHAT_ADDRESS);
                sc.configureBlocking(false);
                Dispatcher d = nioServer.getReadWriteDispatcher();
                CsPacketHandlerFactory csPacketHandlerFactory = new CsPacketHandlerFactory();
                chatServer = new ChatServerConnection(sc, d, csPacketHandlerFactory.getPacketHandler());
                d.register(sc, SelectionKey.OP_READ, chatServer);
                chatServer.initialized();
                return chatServer;
            } catch (Exception e) {
                log.info("Cant connect to ChatServer: " + e.getMessage());
            }
            try {
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
            }
        }
    }
	
    public void chatServerDown() {
        log.warn("Connection with ChatServer lost...");
        chatServer = null;
        if (!serverShutdown) {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    connect();
                }
            }, 5000);
        }
    }
	
    public void sendPlayerLoginRequst(Player player) {
        if (chatServer != null) {
            chatServer.sendPacket(new SM_CS_PLAYER_AUTH(player.getObjectId(), player.getAcountName(), player.getName()));
        }
    }
	
    public void sendPlayerLogout(Player player) {
        if (chatServer != null) {
            chatServer.sendPacket(new SM_CS_PLAYER_LOGOUT(player.getObjectId()));
        }
    }
	
    @SuppressWarnings("synthetic-access")
    private static class SingletonHolder {
        protected static final ChatServer instance = new ChatServer();
    }
}
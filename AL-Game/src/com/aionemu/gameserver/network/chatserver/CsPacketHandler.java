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

import com.aionemu.gameserver.network.chatserver.ChatServerConnection.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class CsPacketHandler
{
    private static final Logger log = LoggerFactory.getLogger(CsPacketHandler.class);
    private Map<State, Map<Integer, CsClientPacket>> packetPrototypes = new HashMap<State, Map<Integer, CsClientPacket>>();
	
    public CsClientPacket handle(ByteBuffer data, ChatServerConnection client) {
        State state = client.getState();
        int id = data.get() & 0xff;
        return getPacket(state, id, data, client);
    }
	
    public void addPacketPrototype(CsClientPacket packetPrototype, State... states) {
        for (State state : states) {
            Map<Integer, CsClientPacket> pm = packetPrototypes.get(state);
            if (pm == null) {
                pm = new HashMap<Integer, CsClientPacket>();
                packetPrototypes.put(state, pm);
            }
            pm.put(packetPrototype.getOpcode(), packetPrototype);
        }
    }
	
    private CsClientPacket getPacket(State state, int id, ByteBuffer buf, ChatServerConnection con) {
        CsClientPacket prototype = null;
        Map<Integer, CsClientPacket> pm = packetPrototypes.get(state);
        if (pm != null) {
            prototype = pm.get(id);
        } if (prototype == null) {
            unknownPacket(state, id);
            return null;
        }
        CsClientPacket res = prototype.clonePacket();
        res.setBuffer(buf);
        res.setConnection(con);
        return res;
    }
	
    private void unknownPacket(State state, int id) {
        log.warn(String.format("Unknown packet recived from Chat Server: 0x%02X state=%s", id, state.toString()));
    }
}
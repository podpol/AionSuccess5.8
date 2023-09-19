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
package com.aionemu.gameserver.network.aion;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.configs.administration.DeveloperConfig;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.Util;

public class AionPacketHandler {

	/**
	 * logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(AionPacketHandler.class);
	private Map<Integer, AionClientPacket> packetsPrototypes = new HashMap<Integer, AionClientPacket>();

	/**
	 * Reads one packet from given ByteBuffer
	 *
	 * @param data
	 * @param client
	 * @return AionClientPacket object from binary data
	 */
	public AionClientPacket handle(ByteBuffer data, AionConnection client) {
		State state = client.getState();
		int id = data.getShort() & 0xffff;
		/* Second opcodec. */
		data.position(data.position() + 3);

		return getPacket(state, id, data, client);
	}

	public void addPacketPrototype(AionClientPacket packetPrototype) {
		packetsPrototypes.put(packetPrototype.getOpcode(), packetPrototype);
	}

	private AionClientPacket getPacket(State state, int id, ByteBuffer buf, AionConnection con) {
		AionClientPacket prototype = packetsPrototypes.get(id);

		if (prototype == null) {
			unknownPacket(state, id, buf);
			return null;
		}

		/**
		 * Display Packets Name + Hex-Bytes in Chat Window
		 */
		Player player = con.getActivePlayer();

		if (con.getState().equals(State.IN_GAME) && player != null && player.getAccessLevel() >= DeveloperConfig.SHOW_PACKETS_INCHAT_ACCESSLEVEL) {
			if (isPacketFilterd(DeveloperConfig.FILTERED_PACKETS_INCHAT, prototype.getPacketName())) {
				if (DeveloperConfig.SHOW_PACKET_BYTES_INCHAT) {
					String PckName = String.format("0x%04X : %s", id, prototype.getPacketName());
					PacketSendUtility.sendMessage(player, "********************************************");
					PacketSendUtility.sendMessage(player, PckName);
					PacketSendUtility.sendMessage(player, Util.toHexStream(getByteBuffer(buf, DeveloperConfig.TOTAL_PACKET_BYTES_INCHAT)));
					buf.position(5);

				}
				else if (DeveloperConfig.SHOW_PACKET_NAMES_INCHAT) {
					String PckName = String.format("0x%04X : %s", id, prototype.getPacketName());
					PacketSendUtility.sendMessage(player, PckName);
				}
			}
		}
		AionClientPacket res = prototype.clonePacket();
		res.setBuffer(buf);
		res.setConnection(con);

		if (con.getState().equals(State.IN_GAME) && con.getActivePlayer().getPlayerAccount().getMembership() == 10) {
			PacketSendUtility.sendMessage(con.getActivePlayer(), "0x" + Integer.toHexString(res.getOpcode()).toUpperCase() + " : " + res.getPacketName());
		}
		return res;
	}

	private boolean isPacketFilterd(String filterlist, String PacketName) {

		// If FilterList was empty, all packets will be shown.
		if (filterlist == null || filterlist.equalsIgnoreCase("*"))
			return true;

		String[] Parts = null;
		Parts = filterlist.trim().split(",");

		for (String p : Parts) {
			if (p.trim().equalsIgnoreCase(PacketName)) {
				return true;
			}
		}
		return false;
	}

	private ByteBuffer getByteBuffer(ByteBuffer buf, int count) {

		count = (count <= buf.capacity()) ? count : buf.capacity();
		ByteBuffer tmpBuffer = buf.asReadOnlyBuffer();
		tmpBuffer.position(5);
		tmpBuffer.limit(count);

		// Create an empty ByteBuffer with a Requested Capacity.
		ByteBuffer PckBuffer = ByteBuffer.allocate(count);
		try {
			do {
				PckBuffer.put(tmpBuffer.get());
			}
			while (tmpBuffer.remaining() > 0);
		}
		catch (Exception e) {
			// e.printStackTrace();
		}
		PckBuffer.position(0);
		return PckBuffer;
	}

	/**
	 * Logs unknown packet.
	 *
	 * @param state
	 * @param id
	 * @param data
	 */
	private void unknownPacket(State state, int id, ByteBuffer data) {
		if (NetworkConfig.DISPLAY_UNKNOWNPACKETS) {
			log.warn(String.format("Unknown packet received from Aion client: 0x%04X, state=%s %n%s", id, state.toString(), Util.toHex(data)));
		}
	}
}

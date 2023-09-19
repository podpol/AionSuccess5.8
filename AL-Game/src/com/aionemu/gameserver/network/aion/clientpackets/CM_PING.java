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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.configs.main.SecurityConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PONG;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.audit.AuditLogger;

/**
 * @author -Nemesiss- modified by Undertrey
 */
public class CM_PING extends AionClientPacket {

	/**
	 * Constructs new instance of <tt>CM_PING </tt> packet
	 * 
	 * @param opcode
	 */
	public CM_PING(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl() {
		readH(); // unk
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		long lastMS = getConnection().getLastPingTimeMS();

		if (lastMS > 0 && player != null) {
			long pingInterval = System.currentTimeMillis() - lastMS;
			// PingInterval should be 3min (180000ms)
			if (pingInterval < SecurityConfig.PING_INTERVAL * 1000) {// client timer cheat
				AuditLogger.info(player, "Possible client timer cheat kicking player: " + pingInterval + ", ip="
					+ getConnection().getIP());
				if (SecurityConfig.SECURITY_ENABLE) {
					PacketSendUtility.sendMessage(player, "You have been triggered Speed Hack detection so you're disconnected.");
					getConnection().closeNow();
				}
			}
		}
		getConnection().setLastPingTimeMS(System.currentTimeMillis());
		sendPacket(new SM_PONG());
	}
}

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

import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOTSPOT_TELEPORT;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.HotspotTeleportService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Ranastic
 */

public class CM_HOTSPOT_TELEPORT extends AionClientPacket
{
	private int action;
	private int teleportId;
	private int price;
	@SuppressWarnings("unused")
	private int unk;
	
	public CM_HOTSPOT_TELEPORT(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		action = readC();
		if (action == 1) {
			teleportId = readD();
			price = readD();
			unk = readD();
		} else if (action == 2) {
		}
	}
	
	@Override
	protected void runImpl() {
		final Player player = getConnection().getActivePlayer();
		if (player.getInventory().getKinah() < price) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOTSPOT_NOT_ENOUGH_COST);
			return;
		} if (action == 1) {
			HotspotTeleportService.getInstance().doTeleport(player, teleportId, price);
		} else if (action == 2) {
        	player.getController().cancelTask(TaskId.HOTSPOT_TELEPORT);
        	PacketSendUtility.broadcastPacketAndReceive(player, new SM_HOTSPOT_TELEPORT(2, player.getObjectId()));
		}
	}
}
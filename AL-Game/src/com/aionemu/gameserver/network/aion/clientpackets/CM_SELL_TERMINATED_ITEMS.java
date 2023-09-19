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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.items.storage.Storage;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.item.ItemPacketService.ItemDeleteType;

/**
 * @author Ranastic
 */

public class CM_SELL_TERMINATED_ITEMS extends AionClientPacket
{
	private int size;
	private int itemObjId;
	private static final Logger log = LoggerFactory.getLogger(CM_SELL_TERMINATED_ITEMS.class);
	
	public CM_SELL_TERMINATED_ITEMS(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		}
		Storage inventory = player.getInventory();
		size = readH();
		for (int i = 0; i < size; i++) {
			itemObjId = readD();
			Item item = inventory.getItemByObjId(itemObjId);
			inventory.delete(item, ItemDeleteType.DISCARD);
		}
		inventory.increaseKinah(size * 5000);
	}
	
	@Override
	protected void runImpl() {
	}
}
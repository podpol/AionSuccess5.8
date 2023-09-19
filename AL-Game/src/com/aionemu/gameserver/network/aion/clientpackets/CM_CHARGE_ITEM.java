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

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.item.ItemChargeService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ATracer
 */
public class CM_CHARGE_ITEM extends AionClientPacket {

	private int targetNpcObjectId;
	private int chargeLevel;
	private Collection<Integer> itemIds;

	public CM_CHARGE_ITEM(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		targetNpcObjectId = readD();
		chargeLevel = readC();
		int itemsSize = readH();
		itemIds = new ArrayList<Integer>();
		for (int i = 0; i < itemsSize; i++) {
			itemIds.add(readD());
		}

	}

	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (!player.isTargeting(targetNpcObjectId)) {
			return; // TODO audit?
		}

		for (int itemObjId : itemIds) {
			Item item = player.getInventory().getItemByObjId(itemObjId);
			if (item != null) {
				int itemChargeLevel = item.getChargeLevelMax();
				int possibleChargeLevel = Math.min(itemChargeLevel, chargeLevel);
				if (possibleChargeLevel > 0) {
					if (ItemChargeService.processPayment(player, item, possibleChargeLevel))
						ItemChargeService.chargeItem(player, item, possibleChargeLevel);
				}
			}
		}
	}

}

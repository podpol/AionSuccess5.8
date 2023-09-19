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
import com.aionemu.gameserver.model.items.storage.Storage;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.services.item.ItemUpgradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ranastic (Encom)
 */

public class CM_PURIFICATION_ITEM extends AionClientPacket
{
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CM_PURIFICATION_ITEM.class);
	int playerObjectId;
	int upgradedItemObjectId;
	int resultItemId;
	int requireItemObjectId1;
	int requireItemObjectId2;
	int requireItemObjectId3;
	int requireItemObjectId4;
	int requireItemObjectId5;
	Item baseItem;
	
	public CM_PURIFICATION_ITEM(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		Player player = getConnection().getActivePlayer();
		playerObjectId = readD();
		upgradedItemObjectId = readD();
		resultItemId = readD();
		requireItemObjectId1 = readD();
		requireItemObjectId2 = readD();
		requireItemObjectId3 = readD();
		requireItemObjectId4 = readD();
		requireItemObjectId5 = readD();
		Storage inventory = player.getInventory();
		baseItem = inventory.getItemByObjId(upgradedItemObjectId);
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		} if (!ItemUpgradeService.checkItemUpgrade(player, baseItem, resultItemId)) {
			return;
		}
		Item resultItem = ItemService.newItem(resultItemId, 1, null, 0, 0, 0);
		ItemService.makeUpgradeItem(baseItem, resultItem);
		if (!ItemUpgradeService.decreaseMaterial(player, baseItem, resultItemId)) {
			return;
		}
		ItemService.addItem(player, resultItem);
	}
}
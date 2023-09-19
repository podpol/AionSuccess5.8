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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.network.aion.iteminfo.ItemInfoBlob;

import java.util.List;

/**
 * @author Avol, xTz
 */
public class SM_VIEW_PLAYER_DETAILS extends AionServerPacket {

	private List<Item> items;
	private int itemSize;
	private int targetObjId;
	private Player player;

	public SM_VIEW_PLAYER_DETAILS(List<Item> items, Player player) {
		this.player = player;
		this.targetObjId = player.getObjectId();
		this.items = items;
		this.itemSize = items.size();
	}

	@Override
	protected void writeImpl(AionConnection con) {

		writeD(targetObjId);
		writeC(11);
		writeH(itemSize);
		for (Item item : items) {
			writeItemInfo(item);
		}
	}

	private void writeItemInfo(Item item) {
		ItemTemplate template = item.getItemTemplate();

		writeD(0);
		writeD(template.getTemplateId());
		writeNameId(template.getNameId());

		ItemInfoBlob itemInfoBlob = ItemInfoBlob.getFullBlob(player, item);
		itemInfoBlob.writeMe(getBuf());
	}
}

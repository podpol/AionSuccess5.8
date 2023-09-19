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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ghostfur (Aion-Unique)
 */
public class SM_MEGAPHONE_MESSAGE extends AionServerPacket
{
	private Player player;
	private String message;
	private int itemId;
	private boolean isAll;
	
	public SM_MEGAPHONE_MESSAGE(Player player, String message, int itemId, boolean isAll) {
		this.player = player;
		this.message = message;
		this.itemId = itemId;
		this.isAll = isAll;
	}
	
	@Override
	protected void writeImpl(AionConnection client) {
		writeS(player.getName());
		writeS(message);
		writeD(itemId);
		writeC(this.isAll ? this.player.getRace().getRaceId() : 255);
	}
}
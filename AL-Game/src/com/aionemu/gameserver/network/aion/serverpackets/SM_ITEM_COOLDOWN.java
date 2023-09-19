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

import com.aionemu.gameserver.model.items.ItemCooldown;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.Map;

/**
 * @author ATracer
 */
public class SM_ITEM_COOLDOWN extends AionServerPacket {

	private Map<Integer, ItemCooldown> cooldowns;

	public SM_ITEM_COOLDOWN(Map<Integer, ItemCooldown> cooldowns) {
		this.cooldowns = cooldowns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeH(cooldowns.size());
		long currentTime = System.currentTimeMillis();
		for (Map.Entry<Integer, ItemCooldown> entry : cooldowns.entrySet()) {
			writeH(entry.getKey());
			int left = (int) ((entry.getValue().getReuseTime() - currentTime) / 1000);
			writeD(left > 0 ? left : 0);
			writeD(entry.getValue().getUseDelay());
		}
	}
}

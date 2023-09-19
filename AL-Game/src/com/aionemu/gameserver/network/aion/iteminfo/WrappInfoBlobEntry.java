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
package com.aionemu.gameserver.network.aion.iteminfo;

import com.aionemu.gameserver.network.aion.iteminfo.ItemInfoBlob.ItemBlobType;

import java.nio.ByteBuffer;

/**
 * @author Ranastic
 */

public class WrappInfoBlobEntry extends ItemBlobEntry
{
	WrappInfoBlobEntry() {
		super(ItemBlobType.WRAPP_INFO);
	}
	
	@Override
	public void writeThisBlob(ByteBuffer buf) {
		if (ownerItem.getItemTemplate().getWrappableCount() > 0 && ownerItem.isPacked()) {
			writeC(buf, ownerItem.getWrappableCount());
		} else if (!ownerItem.isPacked()) {
			writeC(buf, ownerItem.getWrappableCount() * -1);
		} else {
			writeC(buf, 0);
		}
	}
	
	@Override
	public int getSize() {
		return 1;
	}
}
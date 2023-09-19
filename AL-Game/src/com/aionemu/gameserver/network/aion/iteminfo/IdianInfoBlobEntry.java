package com.aionemu.gameserver.network.aion.iteminfo;

import com.aionemu.gameserver.model.items.IdianStone;
import com.aionemu.gameserver.network.aion.iteminfo.ItemInfoBlob.ItemBlobType;

import java.nio.ByteBuffer;

/**
 * @author Ranastic
 */

public class IdianInfoBlobEntry extends ItemBlobEntry
{
	IdianInfoBlobEntry() {
		super(ItemBlobType.IDIAN_INFO);
	}
	
	@Override
	public void writeThisBlob(ByteBuffer buf) {
		IdianStone stone = ownerItem.getIdianStone();
		writeD(buf, stone == null ? 0 : stone.getPolishCharge());
	}
	
	@Override
	public int getSize() {
		return 4;
	}
}
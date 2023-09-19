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

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ranastic
 */

public class SM_COALESCENCE_RESULT extends AionServerPacket
{
	private int itemTemplateId;
	private int itemObjId;
	private int bonusTemplateId;
	private long bonusCount;
	private boolean isBonus; 
	
	public SM_COALESCENCE_RESULT(int itemTemplateId, int itemObjId, int bonusTemplateId, long bonusCount, boolean isBonus) {
		this.itemTemplateId = itemTemplateId;
		this.itemObjId = itemObjId;
		this.bonusTemplateId = bonusTemplateId;
		this.bonusCount = bonusCount;
		this.isBonus = isBonus;
	}
	
	@Override
	protected void writeImpl(AionConnection client) {
		writeD(itemTemplateId);
		writeD(itemObjId);
		writeD(bonusTemplateId);
		writeD(isBonus ? 1 : 0);
		writeQ(bonusCount);
	}
}
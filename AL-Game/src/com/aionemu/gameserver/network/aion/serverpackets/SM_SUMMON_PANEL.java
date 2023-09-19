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


import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author ATracer, xTz
 */
public class SM_SUMMON_PANEL extends AionServerPacket {

	private Summon summon;

	public SM_SUMMON_PANEL(Summon summon) {
		this.summon = summon;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(summon.getObjectId());
		writeH(summon.getLevel());
		writeD(0);// unk
		writeD(0);// unk
		writeD(summon.getLifeStats().getCurrentHp());
		writeD(summon.getGameStats().getMaxHp().getCurrent());
		writeD(summon.getGameStats().getMainHandPAttack().getCurrent());
		writeH(summon.getGameStats().getPDef().getCurrent());
		writeH(0);
		writeH(summon.getGameStats().getMResist().getCurrent());
		writeH(0);// unk
		writeH(0);// unk
		writeD(summon.getLiveTime());
	}

}

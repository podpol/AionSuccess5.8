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
import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author ATracer
 */
public class SM_SUMMON_UPDATE extends AionServerPacket {

	private Summon summon;

	public SM_SUMMON_UPDATE(Summon summon) {
		this.summon = summon;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(summon.getLevel());
		writeH(summon.getMode().getId());
		writeD(0);// unk
		writeD(0);// unk
		writeD(summon.getLifeStats().getCurrentHp());

		Stat2 maxHp = summon.getGameStats().getMaxHp();
		writeD(maxHp.getCurrent());

		Stat2 mainHandPAttack = summon.getGameStats().getMainHandPAttack();
		writeD(mainHandPAttack.getCurrent());

		Stat2 pDef = summon.getGameStats().getPDef();
		writeD(pDef.getCurrent());

		Stat2 mResist = summon.getGameStats().getMResist();
		writeH(mResist.getCurrent());

		Stat2 mDef = summon.getGameStats().getMDef();
		writeD(mDef.getCurrent());
		Stat2 accuracy = summon.getGameStats().getMainHandPAccuracy();
		writeH(accuracy.getCurrent());

		Stat2 mainHandPCritical = summon.getGameStats().getMainHandPCritical();
		writeH(mainHandPCritical.getCurrent());

		Stat2 mBoost = summon.getGameStats().getMBoost();
		writeH(mBoost.getCurrent());
		Stat2 suppression = summon.getGameStats().getMBResist();
		writeH(suppression.getCurrent());

		Stat2 mAccuracy = summon.getGameStats().getMAccuracy();
		writeH(mAccuracy.getCurrent());

		Stat2 mCritical = summon.getGameStats().getMCritical();
		writeH(mCritical.getCurrent());

		Stat2 parry = summon.getGameStats().getParry();
		writeH(parry.getCurrent());

		Stat2 evasion = summon.getGameStats().getEvasion();
		writeH(evasion.getCurrent());

		writeD(maxHp.getBase());
		writeD(mainHandPAttack.getBase());
		writeD(pDef.getBase());
		writeH(mResist.getBase());
		writeD(mDef.getBase());
		writeH(accuracy.getBase());
		writeH(mainHandPCritical.getBase());
		writeH(mBoost.getBase());
		writeH(suppression.getBase());
		writeH(mAccuracy.getBase());
		writeH(mCritical.getBase());
		writeH(parry.getBase());
		writeH(evasion.getBase());
	}

}

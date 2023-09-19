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

import com.aionemu.gameserver.model.siege.FortressLocation;
import com.aionemu.gameserver.model.siege.Influence;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.SiegeService;

import java.util.Map;

public class SM_FORTRESS_STATUS extends AionServerPacket
{
	@Override
	protected void writeImpl(AionConnection con) {
		Map<Integer, FortressLocation> fortresses = SiegeService.getInstance().getFortresses();
		Influence inf = Influence.getInstance();
		writeC(1);
		writeD(SiegeService.getInstance().getSecondsBeforeHourEnd());
		writeF(inf.getGlobalElyosInfluence());
		writeF(inf.getGlobalAsmodiansInfluence());
		writeF(inf.getGlobalBalaursInfluence());
		writeH(6);
		//========[ABYSS]========
		writeD(400010000);
		writeF(inf.getAbyssElyosInfluence());
		writeF(inf.getAbyssAsmodiansInfluence());
		writeF(inf.getAbyssBalaursInfluence());
		//========[BELUS]========
		writeD(400020000);
		writeF(inf.getBelusElyosInfluence());
		writeF(inf.getBelusAsmodiansInfluence());
		writeF(inf.getBelusBalaursInfluence());
		//========[ASPIDA]=======
		writeD(400040000);
		writeF(inf.getAspidaElyosInfluence());
		writeF(inf.getAspidaAsmodiansInfluence());
		writeF(inf.getAspidaBalaursInfluence());
		//=======[ATANATOS]======
		writeD(400050000);
		writeF(inf.getAtanatosElyosInfluence());
		writeF(inf.getAtanatosAsmodiansInfluence());
		writeF(inf.getAtanatosBalaursInfluence());
		//=======[DISILLON]======
		writeD(400060000);
		writeF(inf.getDisillonElyosInfluence());
		writeF(inf.getDisillonAsmodiansInfluence());
		writeF(inf.getDisillonBalaursInfluence());
		//======[KALDOR]=========
		writeD(600090000);
        writeF(inf.getKaldorElyosInfluence());
        writeF(inf.getKaldorAsmodiansInfluence());
        writeF(inf.getKaldorBalaursInfluence());
		writeH(fortresses.size());
		for (FortressLocation fortress : fortresses.values()) {
			writeD(fortress.getLocationId());
			writeC(fortress.getNextState());
		}
	}
}
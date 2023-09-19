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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.model.atreian_bestiary.PlayerABEntry;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * 
 * @author Ranastic
 */

public class SM_ATREIAN_BESTIARY_LIST extends AionServerPacket
{
	PlayerABEntry[] allAB;
	@SuppressWarnings("unused")
	private Player player;
	
	public SM_ATREIAN_BESTIARY_LIST(Player player) {
		this.player = player;
		this.allAB = player.getAtreianBestiary().getAllAB();
	}
	
	@Override
    protected void writeImpl(AionConnection con) {
		writeH(allAB.length);
		for (PlayerABEntry entry : allAB) {
			writeD(entry.getId()); //id
	        writeD(entry.getKillCount()); //current kill
	        writeC(entry.claimRewardLevel()); //claim Reward
	        writeC(entry.getLevel()); //current level
		}
    }
}
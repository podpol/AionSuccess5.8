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
 * @author Ranastic
 */

public class SM_HOTSPOT_TELEPORT extends AionServerPacket
{
	int playerObjId;
	int action;
	int teleportId;
	int cooldown;
	
	public SM_HOTSPOT_TELEPORT(int action, int playerObjId) {
		this.action = action;
		this.playerObjId = playerObjId;
	}
	
	public SM_HOTSPOT_TELEPORT(int action, int playerObjId, int teleportId) {
		this.action = action;
		this.playerObjId = playerObjId;
		this.teleportId = teleportId;
	}
	
	public SM_HOTSPOT_TELEPORT(Player player, int action, int teleportId, int cooldown) {
        this.playerObjId = player.getObjectId();
        this.teleportId = teleportId;
        this.action = action;
        this.cooldown = cooldown;
    }
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeC(action);
		switch (action) {
		    case 0:
			    writeD(playerObjId);
		    break;
		    case 1:
			    writeD(playerObjId);
			    writeD(teleportId);
		    break;
		    case 2:
			    writeD(playerObjId);
		    break;
		    case 3:
			    writeD(playerObjId);
			    writeD(teleportId);
			    writeD(cooldown);
		    break;
		}
	}
}
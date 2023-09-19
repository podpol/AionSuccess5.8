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

import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Simple
 */
public class SM_LEGION_ADD_MEMBER extends AionServerPacket {

	private Player player;
	private boolean isMember;
	private int msgId;
	private String text;

	public SM_LEGION_ADD_MEMBER(Player player, boolean isMember, int msgId, String text) {
		this.player = player;
		this.isMember = isMember;
		this.msgId = msgId;
		this.text = text;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(player.getObjectId());
		writeS(player.getName());
		writeC(player.getLegionMember().getRank().getRankId());
		writeC(isMember ? 0x01 : 0x00);// is New Member?
		writeC(player.getCommonData().getPlayerClass().getClassId());
		writeC(player.getLevel());
		writeD(player.getPosition().getMapId());
		writeD(NetworkConfig.GAMESERVER_ID);
        writeC(0);
        writeC(0);
        writeC(0);
        writeC(0);
        writeC(0);
		writeD(msgId);
		writeS(text);
	}
}

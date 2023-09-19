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
import com.aionemu.gameserver.model.team.legion.LegionMemberEx;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Simple
 */
public class SM_LEGION_UPDATE_MEMBER extends AionServerPacket {

	private static final byte OFFLINE = 0x00;
	private static final byte ONLINE = 0x01;
	private Player player;
	private LegionMemberEx LM;
	private int msgId;
	private String text;
	private byte isOnline;

	public SM_LEGION_UPDATE_MEMBER(Player player, int msgId, String text) {
		this.player = player;
		this.msgId = msgId;
		this.text = text;
		this.isOnline = player.isOnline() ? ONLINE : OFFLINE;
	}

	public SM_LEGION_UPDATE_MEMBER(LegionMemberEx LM, int msgId, String text) {
		this.LM = LM;
		this.msgId = msgId;
		this.text = text;
		this.isOnline = LM.isOnline() ? ONLINE : OFFLINE;
	}

	public SM_LEGION_UPDATE_MEMBER(Player player) {
		this.player = player;
		this.isOnline = OFFLINE;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		if (player != null) {
			writeD(player.getObjectId());
			writeC(player.getLegionMember().getRank().getRankId());
			writeC(player.getCommonData().getPlayerClass().getClassId());
			writeC(player.getLevel());
			writeD(player.getPosition().getMapId());
			writeC(isOnline);
			writeD(player.isOnline() ? 0 : player.getLastOnline());
			writeD(NetworkConfig.GAMESERVER_ID);
			writeD(msgId);
			writeS(text);
		}
		else if (LM != null) {
			writeD(LM.getObjectId());
			writeC(LM.getRank().getRankId());
			writeC(LM.getPlayerClass().getClassId());
			writeC(LM.getLevel());
			writeD(LM.getWorldId());
			writeC(isOnline);
			writeD(LM.isOnline() ? 0 : LM.getLastOnline());
			writeD(NetworkConfig.GAMESERVER_ID);
			writeD(msgId);
			writeS(text);
		}
	}
}

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

import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.model.ChatType;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_MESSAGE extends AionServerPacket
{
	private Player player;
	private int senderObjectId;
	private String message;
	private String senderName;
	private Race race;
	private ChatType chatType;
	private float x;
	private float y;
	private float z;

	public SM_MESSAGE(Player player, String message, ChatType chatType) {
		this.player = player;
		this.senderObjectId = player.getObjectId();
		this.senderName = player.getName();
		this.message = message;
		this.race = player.getRace();
		this.chatType = chatType;
		this.x = player.getX();
		this.y = player.getY();
		this.z = player.getZ();
	}

	public SM_MESSAGE(int senderObjectId, String senderName, String message, ChatType chatType) {
		this.senderObjectId = senderObjectId;
		this.senderName = senderName;
		this.message = message;
		this.chatType = chatType;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		boolean canRead = true;
		if (race != null) {
			canRead = chatType.isSysMsg() || CustomConfig.SPEAKING_BETWEEN_FACTIONS || player.getAccessLevel() > 0 || (con.getActivePlayer() != null && con.getActivePlayer().getAccessLevel() > 0);
		}
		writeC(chatType.toInteger());
		writeC(canRead ? 0 : race.getRaceId() + 1);
		writeD(senderObjectId);
		switch (chatType) {
			case NORMAL:
			case GOLDEN_YELLOW:
			case WHITE:
			case YELLOW:
			case BRIGHT_YELLOW:
			case WHITE_CENTER:
			case YELLOW_CENTER:
			case BRIGHT_YELLOW_CENTER:
				writeH(0x00);
				writeS(message);
				break;
			case SHOUT:
				writeS(senderName);
				writeS(message);
				writeF(x);
				writeF(y);
				writeF(z);
				break;
			case ALLIANCE:
			case GROUP:
			case GROUP_LEADER:
			case LEGION:
			case WHISPER:
			case LEAGUE:
			case LEAGUE_ALERT:
			case CH1:
			case CH2:
			case CH3:
			case CH4:
			case CH5:
			case CH6:
			case CH7:
			case CH8:
			case CH9:
			case CH10:
			case COMMAND:
			case ANNOUNCE:
				writeS(senderName);
				writeS(message);
				break;
		}
	}
}
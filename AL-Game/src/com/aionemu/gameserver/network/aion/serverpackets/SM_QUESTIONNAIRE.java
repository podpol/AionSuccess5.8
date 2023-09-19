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
 * Sends Survey HTML data to the client. This packet can be splitted over max 255 packets The max length of the HTML may
 * therefore be 255 * 65525 byte
 * 
 * @author lhw and Kaipo
 */
public class SM_QUESTIONNAIRE extends AionServerPacket {

	private int messageId;
	private byte chunk;
	private byte count;
	private String html;

	public SM_QUESTIONNAIRE(int messageId, byte chunk, byte count, String html) {
		this.messageId = messageId;
		this.chunk = chunk;
		this.count = count;
		this.html = html;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(messageId);
		writeC(chunk);
		writeC(count);
		writeH(html.length() * 2);
		writeS(html);
	}
}

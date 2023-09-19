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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.LetterType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.mail.MailService;

/**
 * @author Aion Gates, xTz
 */

public class CM_SEND_MAIL extends AionClientPacket
{
	private String recipientName;
	private String title;
	private String message;
	private int itemObjId;
	private int itemCount;
	private int kinahCount;
	private int idLetterType;
	
	public CM_SEND_MAIL(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		recipientName = readS();
		title = readS();
		message = readS();
		itemObjId = readD();
		itemCount = readD();
		readD();
		kinahCount = readD();
		readD();
		idLetterType = readC();
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (!player.isTrading() && kinahCount < 1000000000 && kinahCount > -1 && itemCount > -2) {
			MailService.getInstance().sendMail(player, recipientName, title, message, itemObjId, itemCount, kinahCount, 0, LetterType.getLetterTypeById(idLetterType));
		}
	}
}
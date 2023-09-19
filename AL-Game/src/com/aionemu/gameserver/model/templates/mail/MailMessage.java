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
package com.aionemu.gameserver.model.templates.mail;

/**
 * @author kosyachok
 */
public enum MailMessage {
	MAIL_SEND_SECCESS(0),
	NO_SUCH_CHARACTER_NAME(1),
	RECIPIENT_MAILBOX_FULL(2),
	MAIL_IS_ONE_RACE_ONLY(3),
	YOU_ARE_IN_RECIPIENT_IGNORE_LIST(4),
	RECIPIENT_IGNORING_MAIL_FROM_PLAYERS_LOWER_206_LVL(5), // WTF??
	MAILSPAM_WAIT_FOR_SOME_TIME(6);

	private int id;

	private MailMessage(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}

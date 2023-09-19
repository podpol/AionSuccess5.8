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
package com.aionemu.gameserver.dao;

import com.aionemu.gameserver.model.gameobjects.Letter;
import com.aionemu.gameserver.model.gameobjects.player.Mailbox;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;

import java.sql.Timestamp;

/**
 * @author kosyachok
 */
public abstract class MailDAO implements IDFactoryAwareDAO {

	@Override
	public String getClassName() {
		return MailDAO.class.getName();
	}

	public abstract boolean storeLetter(Timestamp time, Letter letter);

	public abstract Mailbox loadPlayerMailbox(Player player);

	public abstract void storeMailbox(Player player);

	public abstract boolean deleteLetter(int letterId);

	public abstract void updateOfflineMailCounter(PlayerCommonData recipientCommonData);

	public abstract boolean haveUnread(int playerId);
}

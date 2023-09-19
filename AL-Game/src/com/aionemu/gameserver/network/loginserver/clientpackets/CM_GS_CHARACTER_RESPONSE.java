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
package com.aionemu.gameserver.network.loginserver.clientpackets;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.network.loginserver.LsClientPacket;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_GS_CHARACTER;

/**
 * @author cura
 */
public class CM_GS_CHARACTER_RESPONSE extends LsClientPacket {

	public CM_GS_CHARACTER_RESPONSE(int opCode) {
		super(opCode);
	}

	private int accountId;

	@Override
	public void readImpl() {
		accountId = readD();
	}

	@Override
	public void runImpl() {
		int characterCount = DAOManager.getDAO(PlayerDAO.class).getCharacterCountOnAccount(accountId);
		sendPacket(new SM_GS_CHARACTER(accountId, characterCount));
	}
}

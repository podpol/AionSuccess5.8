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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceService;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.restrictions.RestrictionsManager;

/**
 * @author Lyahim, Simple, xTz
 */
public class CM_GROUP_DISTRIBUTION extends AionClientPacket {

	private long amount;
	private int partyType;

	public CM_GROUP_DISTRIBUTION(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		amount = readQ();
		partyType = readC();
	}

	@Override
	protected void runImpl() {
		if (amount < 2)
			return;

		Player player = getConnection().getActivePlayer();

		if (!RestrictionsManager.canTrade(player))
			return;

		switch (partyType) {
			case 1:
				if (player.isInAlliance2()) {
					PlayerAllianceService.distributeKinahInGroup(player, amount);
				}
				else {
					PlayerGroupService.distributeKinah(player, amount);
				}
				break;
			case 2:
				PlayerAllianceService.distributeKinah(player, amount);
				break;
		}
	}
}

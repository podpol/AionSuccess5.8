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

import com.aionemu.gameserver.model.gameobjects.player.DeniedStatus;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceService;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.model.team2.league.LeagueService;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.ChatUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.Util;
import com.aionemu.gameserver.world.World;

/**
 * @author Lyahim, ATracer Modified by Simple
 */
public class CM_TEAM_INVITE extends AionClientPacket {

	private String name;
	private int inviteType;

	public CM_TEAM_INVITE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl() {
		inviteType = readC();
		name = readS();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl() {

		name = ChatUtil.getRealAdminName(name);
		name = name.replace("\uE024", "");
		name = name.replace("\uE023", "");
		final String playerName = Util.convertName(name);

		final Player inviter = getConnection().getActivePlayer();
		if (inviter.getLifeStats().isAlreadyDead()) {
			// You cannot issue an invitation while you are dead.
			PacketSendUtility.sendPacket(inviter, new SM_SYSTEM_MESSAGE(1300163));
			return;
		}

		final Player invited = World.getInstance().findPlayer(playerName);
		if (invited != null) {
			if (invited.getPlayerSettings().isInDeniedStatus(DeniedStatus.GROUP)) {
				sendPacket(SM_SYSTEM_MESSAGE.STR_MSG_REJECTED_INVITE_PARTY(invited.getName()));
				return;
			}
			switch (inviteType) {
				case 0:
					PlayerGroupService.inviteToGroup(inviter, invited);
					break;
				case 12: // 2.5
					PlayerAllianceService.inviteToAlliance(inviter, invited);
					break;
				case 28:
					LeagueService.inviteToLeague(inviter, invited);
					break;
				default:
					PacketSendUtility.sendMessage(inviter, "You used an unknown invite type: " + inviteType);
					break;
			}
		}
		else
			inviter.getClientConnection().sendPacket(SM_SYSTEM_MESSAGE.STR_NO_SUCH_USER(name));
	}
}

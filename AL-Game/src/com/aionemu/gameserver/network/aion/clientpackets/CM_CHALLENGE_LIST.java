package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.challenge.ChallengeType;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.ChallengeTaskService;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class CM_CHALLENGE_LIST extends AionClientPacket
{
	public CM_CHALLENGE_LIST(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	int action;
	int taskOwner;
	int ownerType;
	int playerId;
	int dateSince;
	
	@Override
	protected void readImpl() {
		action = readC();
		taskOwner = readD();
		ownerType = readC();
		playerId = readD();
		dateSince = readD();
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (ownerType == 1) {
			if (player.getLegion() == null) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_GUILD_LEAVE_I_AM_NOT_BELONG_TO_GUILD);
				return;
			}
			ChallengeTaskService.getInstance().showTaskList(player, ChallengeType.LEGION, taskOwner);
		} else {
			ChallengeTaskService.getInstance().showTaskList(player, ChallengeType.TOWN, taskOwner);
		}
	}
}
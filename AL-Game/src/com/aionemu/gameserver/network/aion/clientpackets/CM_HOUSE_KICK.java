package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.utils.audit.AuditLogger;

public class CM_HOUSE_KICK extends AionClientPacket
{
	int option;
	
	public CM_HOUSE_KICK(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		option = readC();
		readH();
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null)
			return;
		House house = player.getActiveHouse();
		if (house == null) {
			AuditLogger.info(player, "Trying to kick players from house, but haven't own house");
			return;
		} if (option == 1)
			house.getController().kickVisitors(player, false, false);
		else if (option == 2)
			house.getController().kickVisitors(player, true, false);
	}
}
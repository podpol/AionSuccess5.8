package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.HouseObject;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.world.World;

public class CM_USE_HOUSE_OBJECT extends AionClientPacket {

	int itemObjectId;

	public CM_USE_HOUSE_OBJECT(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	protected void readImpl() {
		itemObjectId = readD();
	}

	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null)
			return;
		VisibleObject visObject = World.getInstance().findVisibleObject(itemObjectId);
		if (visObject == null)
			return;
		if (visObject instanceof HouseObject)
			((HouseObject<?>) visObject).getController().onDialogRequest(player);
	}
}

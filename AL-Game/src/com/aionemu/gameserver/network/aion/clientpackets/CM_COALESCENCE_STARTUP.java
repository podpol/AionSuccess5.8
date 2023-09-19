package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_COALESCENCE_STARTUP;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * 
 * @author Ranastic
 *
 */
public class CM_COALESCENCE_STARTUP extends AionClientPacket
{

	public CM_COALESCENCE_STARTUP(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		//null
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null || !player.isSpawned()) {
			return;
		}
		PacketSendUtility.sendPacket(player, new SM_COALESCENCE_STARTUP(0));
	}
}

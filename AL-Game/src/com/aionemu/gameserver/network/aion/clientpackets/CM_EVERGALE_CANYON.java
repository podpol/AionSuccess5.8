package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EVERGALE_CANYON;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ranastic
 */

public class CM_EVERGALE_CANYON extends AionClientPacket 
{
	Logger log = LoggerFactory.getLogger(CM_EVERGALE_CANYON.class);

	public int action;
	
	public CM_EVERGALE_CANYON(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		action = readH();
		switch (action){
			case 0:
				readD();
				readD();
				readD();
				readD();
			break;
		}
	}
	
	@Override
	protected void runImpl() {
		switch (action){
			case 0 :
				PacketSendUtility.sendPacket(getConnection().getActivePlayer(), new SM_EVERGALE_CANYON(0));
			break;
		}
	}
}
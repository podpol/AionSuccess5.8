package com.aionemu.gameserver.network.aion.clientpackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;

/**
 * 
 * @author Ranastic
 *
 */
public class CM_HOT_SPECTATE extends AionClientPacket
{
	private static final Logger log = LoggerFactory.getLogger(CM_HOT_SPECTATE.class);
	
	public CM_HOT_SPECTATE(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    int unkC1;
    int unkC2;
    int unkD;
	
    @Override
    protected void readImpl() {
    	this.unkC1 = readC();
    	this.unkC2 = readC();
        this.unkD = readD();
    }
	
    @Override
    protected void runImpl() {
    	Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		}
		log.info("c:"+unkC1+" c:"+unkC2+" d:"+unkD);
    }
}

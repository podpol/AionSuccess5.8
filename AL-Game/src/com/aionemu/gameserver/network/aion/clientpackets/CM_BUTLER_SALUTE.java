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

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_BUTLER_SALUTE;

/**
 * @author Ranastic
 */

public class CM_BUTLER_SALUTE extends AionClientPacket
{
	private int playerObjId;
	private int isInside;
	private int unk1;
	private int unk2;
	private int unk3;
	private int unk4;
	
	public CM_BUTLER_SALUTE(int opcode, State state, State... states) {
        super(opcode, state, states);
    }
	
    @Override
    protected void readImpl() {
    	unk1 = readD();
    	unk2 = readC();
    	unk3 = readD();
    	unk4 = readC();
    	playerObjId = readD();
    	isInside = readC();
    }
	
    @Override
    protected void runImpl() {
    	sendPacket(new SM_BUTLER_SALUTE(unk1, unk2, unk3, unk4, playerObjId, isInside));
    }
}
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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_ACCOUNT_PROPERTIES extends AionServerPacket
{
	public SM_ACCOUNT_PROPERTIES() {
	}
	
	private boolean isGM;
    private int accountType;
    private int purchaseType;
    private int time;
	
	public SM_ACCOUNT_PROPERTIES(boolean isGM) {
        this.isGM = isGM;
    }
	
    public SM_ACCOUNT_PROPERTIES(boolean isGM, int accountType, int purchaseType, int time) {
		this.isGM = isGM;
        this.accountType = accountType;
        this.purchaseType = purchaseType;
        this.time = time;
    }
	
	@Override
    protected void writeImpl(AionConnection con) {
        writeH(this.isGM ? 3 : 0);
        writeH(0);
        writeD(0);
        writeD(0);
        writeD(this.isGM ? 32768 : 0);
        writeD(0);
        writeC(0);
        writeD(31);
        writeD(0);
        writeD(purchaseType); //Purchase Type.
        writeD(accountType); //Account Type.
        writeD(0);
        writeD(0);
        writeD(0);
        writeD(time);
        writeB(new byte[32]);
    }
}
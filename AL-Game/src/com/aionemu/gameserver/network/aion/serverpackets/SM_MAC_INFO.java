package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * 
 * @author Ranastic
 *
 */
public class SM_MAC_INFO extends AionServerPacket {

	private String macAddress;
	private String hardName;
	private int localIP;
	
	public SM_MAC_INFO(String macAddress, String hardName, int localIP) {
		this.macAddress = macAddress;
		this.hardName = hardName;
		this.localIP = localIP;
	}
	
    @Override
    protected void writeImpl(AionConnection con) {
    	writeS(macAddress);
    	writeS(hardName);
        writeD(localIP);
    }
}

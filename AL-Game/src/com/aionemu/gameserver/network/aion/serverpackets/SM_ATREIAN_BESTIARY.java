package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * 
 * @author Ranastic
 *
 */
public class SM_ATREIAN_BESTIARY extends AionServerPacket {

	private int id;
	private int kill;
	private int isRewardable;
	private byte level;
	
	public SM_ATREIAN_BESTIARY(int id, int kill, byte level, int isRewardable) {
        this.id = id;
        this.kill = kill;
        this.isRewardable = isRewardable;
        this.level = level;
    }
	
	@Override
    protected void writeImpl(AionConnection con) {
        writeD(id);
        writeD(kill);
        writeC(isRewardable);
        writeC(level);
    }
}

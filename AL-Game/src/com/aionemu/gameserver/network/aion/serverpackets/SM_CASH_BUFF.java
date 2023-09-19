package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_CASH_BUFF extends AionServerPacket
{
	int type;
	
	public SM_CASH_BUFF(int type){
		this.type = type;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeC(type); //버프 시작
		switch (type) {
			case 1:
				writeH(0);
			break;
			case 2:
				writeH(1); //buff number
				writeC(2); //버프 시작
				writeH(3000); //버프 아이디
				writeH(0); // 0x00
				writeD(388306); //temps
			break;
		}
	}
}
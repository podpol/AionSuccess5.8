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

import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.team.legion.LegionMemberEx;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.HousingService;

import java.util.List;

public class SM_LEGION_MEMBERLIST extends AionServerPacket
{
	private static final int OFFLINE = 0x00, ONLINE = 0x01;
	private boolean isFirst;
	private boolean result;
	private List<LegionMemberEx> legionMembers;
	
	public SM_LEGION_MEMBERLIST(List<LegionMemberEx> legionMembers, boolean result, boolean isFirst) {
		this.legionMembers = legionMembers;
		this.result = result;
		this.isFirst = isFirst;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		int size = legionMembers.size();
		writeC(isFirst ? 1 : 0);
		writeH(result ? size : -size);
		for (LegionMemberEx legionMember : legionMembers) {
			writeD(legionMember.getObjectId());
			writeS(legionMember.getName());
			writeC(legionMember.getPlayerClass().getClassId());
			writeD(legionMember.getLevel());
			writeC(legionMember.getRank().getRankId());
			writeD(legionMember.getWorldId());
			writeC(legionMember.isOnline() ? ONLINE : OFFLINE);
			writeS(legionMember.getSelfIntro());
			writeS(legionMember.getNickname());
			writeD(legionMember.getLastOnline());
			int address = HousingService.getInstance().getPlayerAddress(legionMember.getObjectId());
			if (address > 0) {
				House house = HousingService.getInstance().getPlayerStudio(legionMember.getObjectId());
				if (house == null) {
					house = HousingService.getInstance().getHouseByAddress(address);
				}
				writeD(address);
				writeD(house.getDoorState().getPacketValue());
			} else {
				writeD(0);
				writeD(0);
			}
			writeD(NetworkConfig.GAMESERVER_ID);
		}
	}
}
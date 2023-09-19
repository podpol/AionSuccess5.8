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


import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Simple
 */
public class SM_LEGION_EDIT extends AionServerPacket {

	private int type;
	private Legion legion;
	private int unixTime;
	private String announcement;

	public SM_LEGION_EDIT(int type) {
		this.type = type;
	}

	public SM_LEGION_EDIT(int type, Legion legion) {
		this.type = type;
		this.legion = legion;
	}

	public SM_LEGION_EDIT(int type, int unixTime) {
		this.type = type;
		this.unixTime = unixTime;
	}

	public SM_LEGION_EDIT(int type, int unixTime, String announcement) {
		this.type = type;
		this.announcement = announcement;
		this.unixTime = unixTime;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(type);
		switch (type) {
			/** Change Legion Level **/
			case 0x00:
				writeC(legion.getLegionLevel());
				break;
			/** Change Legion Rank **/
			case 0x01:
				writeD(legion.getLegionRank());
				break;
			/** Change Legion Permissions **/
			case 0x02:
				writeH(legion.getDeputyPermission());
				writeH(legion.getCenturionPermission());
				writeH(legion.getLegionaryPermission());
				writeH(legion.getVolunteerPermission());
				break;
			/** Change Legion Contributions **/
			case 0x03:
				writeQ(legion.getContributionPoints()); // get Contributions
				break;
			case 0x04:
				writeQ(legion.getLegionWarehouse().getKinah());
				break;
			/** Change Legion Announcement **/
			case 0x05:
				writeS(announcement);
				writeD(unixTime);
				break;
			/** Disband Legion **/
			case 0x06:
				writeD(unixTime);
				break;
			/** Recover Legion **/
			case 0x07:
				break;
			/** Refresh Legion Announcement? **/
			case 0x08:
				break;
			/** Stonespear Reach **/		
			case 0x10:
			    break;
			case 0x0C:
                writeS(legion.getLegionDescription());
                break;
            case 0x0D:
            	writeC(legion.getLegionJoinType());
            	break;
            case 0x0E:
            	writeH(legion.getMinLevel());
            	break;
		}
	}
}
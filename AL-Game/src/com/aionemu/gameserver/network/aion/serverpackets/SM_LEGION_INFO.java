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

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author Simple
 */
public class SM_LEGION_INFO extends AionServerPacket {

	/** Legion information **/
	private Legion legion;

	/**
	 * This constructor will handle legion info
	 * 
	 * @param legion
	 */
	public SM_LEGION_INFO(Legion legion) {
		this.legion = legion;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeS(legion.getLegionName());
		writeC(legion.getLegionLevel());
		writeD(legion.getLegionRank());
		writeH(legion.getDeputyPermission());
		writeH(legion.getCenturionPermission());
		writeH(legion.getLegionaryPermission());
		writeH(legion.getVolunteerPermission());
		writeQ(legion.getContributionPoints());
		writeB(new byte[24]);
        writeS(legion.getLegionDescription());
        writeC(legion.getLegionJoinType());
        writeH(legion.getMinLevel());
		/** Get Announcements List From DB By Legion **/
		Map<Timestamp, String> announcementList = legion.getAnnouncementList().descendingMap();

		/** Show max 7 announcements **/
		int i = 0;
		for (Timestamp unixTime : announcementList.keySet()) {
			writeS(announcementList.get(unixTime));
			writeD((int) (unixTime.getTime() / 1000));
			i++;
			if (i >= 7)
				break;
		}
	}
}

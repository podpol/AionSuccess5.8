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

import java.util.List;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.model.stats.container.PlayerLifeStats;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceMember;
import com.aionemu.gameserver.model.team2.common.legacy.PlayerAllianceEvent;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.world.WorldPosition;

/**
 * @author Sarynth (Thx Rhys2002 for Packets)
 */
public class SM_ALLIANCE_MEMBER_INFO extends AionServerPacket {

	private Player player;
	private PlayerAllianceEvent event;
	private final int allianceId;
	private final int objectId;

	public SM_ALLIANCE_MEMBER_INFO(PlayerAllianceMember member, PlayerAllianceEvent event) {
		this.player = member.getObject();
		this.event = event;
		this.allianceId = member.getAllianceId();
		this.objectId = member.getObjectId();
	}

	@Override
	protected void writeImpl(AionConnection con) {
		PlayerCommonData pcd = player.getCommonData();
		WorldPosition wp = player.getPosition();

		/**
		 * Required so that when member is disconnected, and his playerAllianceGroup slot is changed, he will continue to appear as disconnected to the alliance.
		 */
		if (event == PlayerAllianceEvent.ENTER && !player.isOnline()) {
			event = PlayerAllianceEvent.ENTER_OFFLINE;
		}

		writeD(allianceId);
		writeD(objectId);
		if (player.isOnline()) {
			PlayerLifeStats pls = player.getLifeStats();
			writeD(pls.getMaxHp());
			writeD(pls.getCurrentHp());
			writeD(pls.getMaxMp());
			writeD(pls.getCurrentMp());
			writeD(pls.getMaxFp());
			writeD(pls.getCurrentFp());
		}
		else {
			writeD(0);
			writeD(0);
			writeD(0);
			writeD(0);
			writeD(0);
			writeD(0);
		}

		writeD(0);// unk 3.5
		writeD(wp.getMapId());
		writeD(wp.getMapId()); // TODO Looks like some ObjId and not mapId
		writeF(wp.getX());
		writeF(wp.getY());
		writeF(wp.getZ());
		writeC(pcd.getPlayerClass().getClassId());
		writeC(pcd.getGender().getGenderId());
		writeC(pcd.getLevel());
		writeC(this.event.getId());
		writeH(0); // channel 0x01?
		writeH(0);
		switch (this.event) {
			case LEAVE:
			case LEAVE_TIMEOUT:
			case BANNED:
			case MOVEMENT:
			case DISCONNECTED:
				break;

			case JOIN:
			case ENTER:
			case ENTER_OFFLINE:
			case UPDATE:
			case RECONNECT:
			case APPOINT_VICE_CAPTAIN: // Unused maybe...
			case DEMOTE_VICE_CAPTAIN:
			case APPOINT_CAPTAIN:
				writeS(pcd.getName());
				writeD(0); // TODO some values 4096 or 256
				writeD(0); // unk
				if (player.isOnline()) {
					List<Effect> abnormalEffects = player.getEffectController().getAbnormalEffects();
					writeC(127);
					writeH(abnormalEffects.size());
					if (abnormalEffects.size() > 0) {
						for (Effect effect : abnormalEffects) {
							writeD(effect.getEffectorId());
							writeH(effect.getSkillId());
							writeC(effect.getSkillLevel());
							writeC(effect.getTargetSlot());
							writeD(effect.getRemainingTime());
							writeH(0);
						}
					}
					writeB(new byte[32]);
				}
				else {
					List<Effect> abnormalEffects = player.getEffectController().getAbnormalEffects();
					writeC(0);
					writeH(abnormalEffects.size());
					if (abnormalEffects.size() > 0) {
						for (Effect effect : abnormalEffects) {
							writeD(effect.getEffectorId());
							writeH(effect.getSkillId());
							writeC(effect.getSkillLevel());
							writeC(effect.getTargetSlot());
							writeD(effect.getRemainingTime());
							writeH(0);
						}
					}
					writeB(new byte[32]);
				}
				break;
			case MEMBER_GROUP_CHANGE:
				writeS(pcd.getName());
				break;
			default:
				break;
		}
	}
}

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

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PortalCooldownList;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.aionemu.gameserver.model.templates.InstanceCooltime;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import javolution.util.FastMap;

public class SM_INSTANCE_INFO extends AionServerPacket
{
	private Player player;
	private boolean isAnswer;
	private int cooldownId;
	private int worldId;
	private TemporaryPlayerTeam<?> playerTeam;

	public SM_INSTANCE_INFO(Player player, boolean isAnswer, TemporaryPlayerTeam<?> playerTeam) {
		this.player = player;
		this.isAnswer = isAnswer;
		this.playerTeam = playerTeam;
		this.worldId = 0;
		this.cooldownId = 0;
	}

	public SM_INSTANCE_INFO(Player player, int instanceId) {
		this.player = player;
		this.isAnswer = false;
		this.playerTeam = null;
		this.worldId = instanceId;
		this.cooldownId = DataManager.INSTANCE_COOLTIME_DATA.getInstanceCooltimeByWorldId(instanceId) != null ? DataManager.INSTANCE_COOLTIME_DATA.getInstanceCooltimeByWorldId(instanceId).getId() : 0;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		boolean hasTeam = playerTeam != null;
		writeC(!isAnswer ? 0x2 : hasTeam ? 0x1 : 0x0);
		writeC(cooldownId);
		writeD(0x00);
		writeH(0x01);
		if (cooldownId == 0) {
			writeD(player.getObjectId());
			writeH(DataManager.INSTANCE_COOLTIME_DATA.size());
			PortalCooldownList cooldownList = player.getPortalCooldownList();
			for (FastMap.Entry<Integer, InstanceCooltime> e = DataManager.INSTANCE_COOLTIME_DATA.getAllInstances().head(), end = DataManager.INSTANCE_COOLTIME_DATA.getAllInstances().tail(); (e = e.getNext()) != end; ) {
				writeD(e.getValue().getId());
				writeD(0x00);
				if (cooldownList.getPortalCooldown(e.getValue().getWorldId()) == 0) {
					writeD(0x00);
				} else {
					writeD((int) (cooldownList.getPortalCooldown(e.getValue().getWorldId()) - System.currentTimeMillis()) / 1000);
				}
				writeD(DataManager.INSTANCE_COOLTIME_DATA.getInstanceEntranceCountByWorldId(e.getKey()));
				writeD(cooldownList.getPortalCooldownItem(e.getValue().getWorldId()) != null ? cooldownList.getPortalCooldownItem(e.getValue().getWorldId()).getEntryCount() * -1 : 0);
				writeD(0x00);
				writeD(0x00);
				writeD(0x01);
				writeC(0x01);
			}
			writeS(player.getName());
		} else {
			writeD(player.getObjectId());
			writeH(0x01);
			writeD(cooldownId);
			writeD(0x00);
			long time = player.getPortalCooldownList().getPortalCooldown(worldId);
			writeD((time == 0 ? 0 : ((int) (time - System.currentTimeMillis()) / 1000)));
			writeD(DataManager.INSTANCE_COOLTIME_DATA.getInstanceEntranceCountByWorldId(worldId));
			writeD(player.getPortalCooldownList().getPortalCooldownItem(worldId) != null ? player.getPortalCooldownList().getPortalCooldownItem(worldId).getEntryCount() * -1 : 0);
			writeD(0x00);
			writeD(0x00);
			writeD(0x01);
			writeC(0x01);
			writeS(player.getName());
		}
	}
}
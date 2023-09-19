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
package com.aionemu.gameserver.services.siegeservice;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.configs.main.SiegeConfig;
import com.aionemu.gameserver.dao.SiegeDAO;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.siege.SiegeLocation;
import com.aionemu.gameserver.model.siege.SiegeModType;
import com.aionemu.gameserver.model.siege.SiegeRace;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SIEGE_LOCATION_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.LegionService;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiegeAutoRace
{
	private static final Logger log = LoggerFactory.getLogger("SIEGE_LOG");
	private static String[] siegeIds = SiegeConfig.SIEGE_AUTO_LOCID.split(";");
	
	public static void AutoSiegeRace(final int locid) {
		final SiegeLocation loc = SiegeService.getInstance().getSiegeLocation(locid);
		if (!loc.getRace().equals(SiegeRace.ASMODIANS) || !loc.getRace().equals(SiegeRace.ELYOS)) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				public void run() {
					SiegeService.getInstance().startSiege(locid);
				}
			}, 300000);
			SiegeService.getInstance().deSpawnNpcs(locid);
			final int oldOwnerRaceId = loc.getRace().getRaceId();
			final int legionId = loc.getLegionId();
			final String legionName = legionId != 0 ? LegionService.getInstance().getLegion(legionId).getLegionName() : "";
			final DescriptionId NameId = new DescriptionId(loc.getTemplate().getNameId());
			if (ElyosAutoSiege(locid)) {
				loc.setRace(SiegeRace.ELYOS);
			} if (AsmoAutoSiege(locid)) {
				loc.setRace(SiegeRace.ASMODIANS);
			}
			loc.setLegionId(0);
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				public void visit(Player player) {
					if (legionId != 0 && player.getRace().getRaceId() == oldOwnerRaceId) {
						//%0 has conquered %1.
						PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1301038, legionName, NameId));
					}
					//%0 succeeded in conquering %1.
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404542, loc.getRace().getDescriptionId(), NameId));
					PacketSendUtility.sendPacket(player, new SM_SIEGE_LOCATION_INFO(loc));
				}
			});
			if (ElyosAutoSiege(locid)) {
				SiegeService.getInstance().spawnNpcs(locid, SiegeRace.ELYOS, SiegeModType.PEACE);
			} else if (AsmoAutoSiege(locid)) {
				SiegeService.getInstance().spawnNpcs(locid, SiegeRace.ASMODIANS, SiegeModType.PEACE);
			}
			DAOManager.getDAO(SiegeDAO.class).updateSiegeLocation(loc);
		}
		SiegeService.getInstance().broadcastUpdate(loc);
	}
	
	public static boolean isAutoSiege(int locId) {
		return ElyosAutoSiege(locId) || AsmoAutoSiege(locId);
	}
	
	public static boolean ElyosAutoSiege(int locId) {
		for (String id: siegeIds[0].split(",")) {
			if (locId == Integer.parseInt(id)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean AsmoAutoSiege(int locId) {
		for (String id: siegeIds[1].split(",")) {
			if (locId == Integer.parseInt(id)) {
				return true;
			}
		}
		return false;
	}
}
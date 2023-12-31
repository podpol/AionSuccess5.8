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
package admincommands;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.dao.SiegeDAO;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.model.siege.*;
import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.services.BaseService;
import com.aionemu.gameserver.services.LegionService;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.services.siegeservice.BalaurAssaultService;
import com.aionemu.gameserver.services.siegeservice.Siege;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

@SuppressWarnings("rawtypes")
public class SiegeCommand extends AdminCommand
{
	private static final String COMMAND_START = "start";
	private static final String COMMAND_STOP = "stop";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_LIST_LOCATIONS = "locations";
	private static final String COMMAND_LIST_SIEGES = "sieges";
	private static final String COMMAND_CAPTURE = "capture";
	private static final String COMMAND_ASSAULT = "assault";
	
	public SiegeCommand() {
		super("siegecommand");
	}
	
	@Override
	public void execute(Player player, String... params) {
		if (params.length == 0) {
			showHelp(player);
			return;
		} if (COMMAND_STOP.equalsIgnoreCase(params[0]) || COMMAND_START.equalsIgnoreCase(params[0])) {
			handleStartStopSiege(player, params);
		} else if (COMMAND_LIST.equalsIgnoreCase(params[0])) {
			handleList(player, params);
		} else if (COMMAND_LIST_SIEGES.equals(params[0])) {
			listLocations(player);
		} else if (COMMAND_CAPTURE.equals(params[0])) {
			capture(player, params);
		} else if (COMMAND_ASSAULT.equals(params[0])) {
			assault(player, params);
		}
	}
	
	protected void handleStartStopSiege(Player player, String... params) {
		if (params.length != 2 || !NumberUtils.isDigits(params[1])) {
			showHelp(player);
			return;
		}
		int siegeLocId = NumberUtils.toInt(params[1]);
		if (!isValidSiegeLocationId(player, siegeLocId)) {
			showHelp(player);
			return;
		} if (COMMAND_START.equalsIgnoreCase(params[0])) {
			if (SiegeService.getInstance().isSiegeInProgress(siegeLocId)) {
				PacketSendUtility.sendMessage(player, "Siege Location " + siegeLocId + " is already under siege");
			} else {
				PacketSendUtility.sendMessage(player, "Siege Location " + siegeLocId + " - starting siege!");
				SiegeService.getInstance().startSiege(siegeLocId);
			}
		} else if (COMMAND_STOP.equalsIgnoreCase(params[0])) {
			if (!SiegeService.getInstance().isSiegeInProgress(siegeLocId)) {
				PacketSendUtility.sendMessage(player, "Siege Location " + siegeLocId + " is not under siege");
			} else {
				PacketSendUtility.sendMessage(player, "Siege Location " + siegeLocId + " - stopping siege!");
				SiegeService.getInstance().stopSiege(siegeLocId);
			}
		}
	}
	
	protected boolean isValidSiegeLocationId(Player player, int fortressId) {
		if (!SiegeService.getInstance().getSiegeLocations().keySet().contains(fortressId)) {
			PacketSendUtility.sendMessage(player, "Id " + fortressId + " is invalid");
			return false;
		}
		return true;
	}
	
	protected void handleList(Player player, String[] params) {
		if (params.length != 2) {
			showHelp(player);
			return;
		} if (COMMAND_LIST_LOCATIONS.equalsIgnoreCase(params[1])) {
			listLocations(player);
		} else if (COMMAND_LIST_SIEGES.equalsIgnoreCase(params[1])) {
			listSieges(player);
		} else {
			showHelp(player);
		}
	}
	
	protected void listLocations(Player player) {
		for (FortressLocation f : SiegeService.getInstance().getFortresses().values()) {
			PacketSendUtility.sendMessage(player, "Fortress: " + f.getLocationId() + " belongs to " + f.getRace());
		} for (ArtifactLocation a : SiegeService.getInstance().getStandaloneArtifacts().values()) {
			PacketSendUtility.sendMessage(player, "Artifact: " + a.getLocationId() + " belongs to " + a.getRace());
		}
	}
	
	protected void listSieges(Player player) {
		for (Integer i : SiegeService.getInstance().getSiegeLocations().keySet()) {
			Siege s = SiegeService.getInstance().getSiege(i);
			if (s != null) {
				int secondsLeft = SiegeService.getInstance().getRemainingSiegeTimeInSeconds(i);
				String minSec = secondsLeft / 60 + "m ";
				minSec += secondsLeft % 60 + "s";
				PacketSendUtility.sendMessage(player, "Location: " + i + ": " + minSec + " left.");
			}
		}
	}
	
	protected void capture(Player player, String[] params) {
		if (params.length < 3 || !NumberUtils.isNumber(params[1])) {
			showHelp(player);
			return;
		}
		int siegeLocationId = NumberUtils.toInt(params[1]);
		if (!SiegeService.getInstance().getSiegeLocations().keySet().contains(siegeLocationId)) {
			PacketSendUtility.sendMessage(player, "Invalid Siege Location Id: " + siegeLocationId);
			return;
		}
		SiegeRace sr = null;
		try {
			sr = SiegeRace.valueOf(params[2].toUpperCase());
		}
		catch (IllegalArgumentException e) {
		}
		Legion legion = null;
		if (sr == null) {
			try {
				int legionId = Integer.valueOf(params[2]);
				legion = LegionService.getInstance().getLegion(legionId);
			} catch (NumberFormatException e) {
				String legionName = "";
				for (int i = 2; i < params.length; i++)
					legionName += " " + params[i];
				legion = LegionService.getInstance().getLegion(legionName.trim());
			} if (legion != null) {
				int legionBGeneral = LegionService.getInstance().getLegionBGeneral(legion.getLegionId());
				if (legionBGeneral != 0) {
					PlayerCommonData BGeneral = DAOManager.getDAO(PlayerDAO.class).loadPlayerCommonData(legionBGeneral);
					sr = SiegeRace.getByRace(BGeneral.getRace());
				}
			}
		} if (legion == null && sr == null) {
			PacketSendUtility.sendMessage(player, params[2] + " is not valid siege race or legion name");
			return;
		}
		SiegeLocation loc = SiegeService.getInstance().getSiegeLocation(siegeLocationId);
		Siege s = SiegeService.getInstance().getSiege(siegeLocationId);
		if (s != null) {
			s.getSiegeCounter().addRaceDamage(sr, s.getBoss().getLifeStats().getMaxHp() + 1);
			s.setBossKilled(true);
			SiegeService.getInstance().stopSiege(siegeLocationId);
			loc.setLegionId(legion != null ? legion.getLegionId() : 0);
		} else {
			SiegeService.getInstance().deSpawnNpcs(siegeLocationId);
			loc.setVulnerable(false);
			loc.setUnderShield(false);
			loc.setRace(sr);
			loc.setLegionId(legion != null ? legion.getLegionId() : 0);
			SiegeService.getInstance().spawnNpcs(siegeLocationId, sr, SiegeModType.PEACE);
			DAOManager.getDAO(SiegeDAO.class).updateSiegeLocation(loc);
			switch (siegeLocationId) {
				//Siel's Western Fortress.
				case 1131:
					if (loc.getRace() == SiegeRace.ASMODIANS) {
						//Shairing At Carpus Isle.
						BaseService.getInstance().capture(108, Race.ASMODIANS);
						//Bomishung At Siel's Left Wing.
						BaseService.getInstance().capture(109, Race.ASMODIANS);
					} else if (loc.getRace() == SiegeRace.ELYOS) {
						//Shairing At Carpus Isle.
						BaseService.getInstance().capture(108, Race.ELYOS);
						//Bomishung At Siel's Left Wing.
						BaseService.getInstance().capture(109, Race.ELYOS);
					} else if (loc.getRace() == SiegeRace.BALAUR) {
						//Shairing At Carpus Isle.
						BaseService.getInstance().capture(108, Race.NPC);
						//Bomishung At Siel's Left Wing.
						BaseService.getInstance().capture(109, Race.NPC);
					}
				break;
				//Siel's Eastern Fortress.
				case 1132:
					if (loc.getRace() == SiegeRace.ASMODIANS) {
						//Sasming At Siel's Right Wing.
						BaseService.getInstance().capture(110, Race.ASMODIANS);
					} else if (loc.getRace() == SiegeRace.ELYOS) {
						//Sasming At Siel's Right Wing.
						BaseService.getInstance().capture(110, Race.ELYOS);
					} else if (loc.getRace() == SiegeRace.BALAUR) {
						//Sasming At Siel's Right Wing.
						BaseService.getInstance().capture(110, Race.NPC);
					}
				break;
				//Sulfur Fortress.
				case 1141:
					if (loc.getRace() == SiegeRace.ASMODIANS) {
						//Oharung At The Sulfur Archipelago.
						BaseService.getInstance().capture(105, Race.ASMODIANS);
						//Joarin At Zephyr Island.
					    BaseService.getInstance().capture(106, Race.ASMODIANS);
						//Temirun At Leibo Island.
					    BaseService.getInstance().capture(107, Race.ASMODIANS);
					} else if (loc.getRace() == SiegeRace.ELYOS) {
						//Oharung At The Sulfur Archipelago.
						BaseService.getInstance().capture(105, Race.ELYOS);
						//Joarin At Zephyr Island.
					    BaseService.getInstance().capture(106, Race.ELYOS);
						//Temirun At Leibo Island.
					    BaseService.getInstance().capture(107, Race.ELYOS);
					} else if (loc.getRace() == SiegeRace.BALAUR) {
						//Oharung At The Sulfur Archipelago.
						BaseService.getInstance().capture(105, Race.NPC);
						//Joarin At Zephyr Island.
					    BaseService.getInstance().capture(106, Race.NPC);
						//Temirun At Leibo Island.
					    BaseService.getInstance().capture(107, Race.NPC);
					}
				break;
			}
		}
		SiegeService.getInstance().broadcastUpdate(loc);
	}
	
	protected void assault(Player player, String[] params) {
		if (params.length < 2 || (!NumberUtils.isNumber(params[1]) && !NumberUtils.isNumber(params[2]))) {
			showHelp(player);
			return;
		}
		int siegeLocationId = NumberUtils.toInt(params[1]);
		int delay = NumberUtils.toInt(params[2]);
		if (!SiegeService.getInstance().getSiegeLocations().keySet().contains(siegeLocationId)) {
			PacketSendUtility.sendMessage(player, "Invalid Siege Location Id: " + siegeLocationId);
			return;
		}
		BalaurAssaultService.getInstance().startAssault(player, siegeLocationId, delay);
	}
	
	protected void showHelp(Player player) {
		PacketSendUtility.sendMessage(player, "AdminCommand //siege Help\n"
		+ "//siege start|stop <LocationId>\n"
		+ "//siege list locations|sieges\n"
		+ "//siege capture <LocationId> <siegeRaceName|legionName|legionId>\n"
		+ "//siege assault <LocationId> <delaySec>");
		java.util.Set<Integer> fortressIds = SiegeService.getInstance().getFortresses().keySet();
		java.util.Set<Integer> artifactIds = SiegeService.getInstance().getStandaloneArtifacts().keySet();
		PacketSendUtility.sendMessage(player, "Fortress: " + StringUtils.join(fortressIds, ", "));
		PacketSendUtility.sendMessage(player, "Artifacts: " + StringUtils.join(artifactIds, ", "));
	}
}
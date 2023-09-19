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

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.base.BaseLocation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.BaseService;
import com.aionemu.gameserver.services.base.Base;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Rinzler
 */

@SuppressWarnings("rawtypes")
public class BaseCommand extends AdminCommand
{
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_CAPTURE = "capture";
	
	public BaseCommand() {
		super("base");
	}
	
	@Override
	public void execute(Player player, String... params) {
		if (params.length == 0) {
			showHelp(player);
			return;
		} if (COMMAND_LIST.equalsIgnoreCase(params[0])) {
			handleList(player, params);
		} else if (COMMAND_CAPTURE.equals(params[0])) {
			capture(player, params);
		}
	}
	
	protected boolean isValidBaseLocationId(Player player, int baseId) {
		if (!BaseService.getInstance().getBaseLocations().keySet().contains(baseId)) {
			PacketSendUtility.sendMessage(player, "Id " + baseId + " is invalid");
			return false;
		}
		return true;
	}
	
	protected void handleList(Player player, String[] params) {
		if (params.length != 1) {
			showHelp(player);
			return;
		} for (BaseLocation base : BaseService.getInstance().getBaseLocations().values()) {
			PacketSendUtility.sendMessage(player, "Base:" + base.getId() + " belongs to " + base.getRace());
		}
	}
	
	protected void capture(Player player, String[] params) {
		if (params.length < 3 || !NumberUtils.isNumber(params[1])) {
			showHelp(player);
			return;
		}
		int baseId = NumberUtils.toInt(params[1]);
		if (!isValidBaseLocationId(player, baseId)) {
			return;
		}
		Race race = null;
		try {
			race = Race.valueOf(params[2].toUpperCase());
		} catch (IllegalArgumentException e) {
		} if (race == null) {
			PacketSendUtility.sendMessage(player, params[2] + " is not valid race");
			showHelp(player);
			return;
		}
		Base base = BaseService.getInstance().getActiveBase(baseId);
		if (base != null) {
			BaseService.getInstance().capture(baseId, race);
		}
	}
	
	protected void showHelp(Player player) {
		PacketSendUtility.sendMessage(player, "AdminCommand //base Help\n" + "//base list\n" + "//base capture <Id> <Race (ELYOS, ASMODIANS, NPC)>");
	}
}
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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.RiftService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Ranastic
 */

public class Rift extends AdminCommand
{
	private static final String COMMAND_OPEN = "open";
	private static final String COMMAND_CLOSE = "close";
	
	public Rift() {
		super("rift");
	}
	
	@Override
	public void execute(Player player, String... params) {
		if (params.length == 0) {
			showHelp(player);
			return;
		} if (COMMAND_CLOSE.equalsIgnoreCase(params[0]) || COMMAND_OPEN.equalsIgnoreCase(params[0])) {
			handleRift(player, params);
		}
	}
	
	protected void handleRift(Player player, String... params) {
		if (params.length < 2 || !NumberUtils.isDigits(params[1])) {
			showHelp(player);
			return;
		}
		int id = NumberUtils.toInt(params[1]);
		boolean result;
		if (!isValidId(player, id)) {
			showHelp(player);
			return;
		} if (COMMAND_OPEN.equalsIgnoreCase(params[0])) {
			boolean guards = Boolean.parseBoolean(params[2]);
			result = RiftService.getInstance().openRifts(id, guards);
			PacketSendUtility.sendMessage(player, result ? "Rifts is opened!" : "Rifts was already opened");
		} else if (COMMAND_CLOSE.equalsIgnoreCase(params[0])) {
			result = RiftService.getInstance().closeRifts(id);
			PacketSendUtility.sendMessage(player, result ? "Rifts is closed!" : "Rifts was already closed");
		}
	}
	
	protected boolean isValidId(Player player, int id) {
		if (!RiftService.getInstance().isValidId(id)) {
			PacketSendUtility.sendMessage(player, "Id " + id + " is invalid");
			return false;
		}
		return true;
	}
	
	protected void showHelp(Player player) {
		PacketSendUtility.sendMessage(player, "AdminCommand //rift open|close <Id|worldId> + 0");
	}
}
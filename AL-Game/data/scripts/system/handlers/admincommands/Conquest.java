package admincommands;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.ConquestService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import org.apache.commons.lang.math.NumberUtils;

public class Conquest extends AdminCommand
{
	private static final String COMMAND_START = "start";
	private static final String COMMAND_STOP = "stop";
	
	public Conquest() {
		super("conquest");
	}
	
	@Override
	public void execute(Player player, String... params) {
		if (params.length == 0) {
			showHelp(player);
			return;
		} if (COMMAND_STOP.equalsIgnoreCase(params[0]) || COMMAND_START.equalsIgnoreCase(params[0])) {
			handleStartStop(player, params);
		}
	}
	
	protected void handleStartStop(Player player, String... params) {
		if (params.length != 2 || !NumberUtils.isDigits(params[1])) {
			showHelp(player);
			return;
		}
		int conquestId = NumberUtils.toInt(params[1]);
		if (!isValidConquestLocationId(player, conquestId)) {
			showHelp(player);
			return;
		} if (COMMAND_START.equalsIgnoreCase(params[0])) {
			if (ConquestService.getInstance().isConquestInProgress(conquestId)) {
				PacketSendUtility.sendMessage(player, "<Conquest> " + conquestId + " is already start");
			} else {
				PacketSendUtility.sendMessage(player, "<Conquest> " + conquestId + " started!");
				ConquestService.getInstance().startConquest(conquestId);
			}
		} else if (COMMAND_STOP.equalsIgnoreCase(params[0])) {
			if (!ConquestService.getInstance().isConquestInProgress(conquestId)) {
				PacketSendUtility.sendMessage(player, "<Conquest> " + conquestId + " is not start!");
			} else {
				PacketSendUtility.sendMessage(player, "<Conquest> " + conquestId + " stopped!");
				ConquestService.getInstance().stopConquest(conquestId);
			}
		}
	}
	
	protected boolean isValidConquestLocationId(Player player, int conquestId) {
		if (!ConquestService.getInstance().getConquestLocations().keySet().contains(conquestId)) {
			PacketSendUtility.sendMessage(player, "Id " + conquestId + " is invalid");
			return false;
		}
		return true;
	}
	
	protected void showHelp(Player player) {
		PacketSendUtility.sendMessage(player, "AdminCommand //conquest start|stop <Id>");
	}
}
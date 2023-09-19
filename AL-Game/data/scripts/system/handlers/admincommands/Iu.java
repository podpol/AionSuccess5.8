package admincommands;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.IuService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import org.apache.commons.lang.math.NumberUtils;

public class Iu extends AdminCommand
{
	private static final String COMMAND_START = "start";
	private static final String COMMAND_STOP = "stop";
	
	public Iu() {
		super("iu");
	}
	
	@Override
	public void execute(Player player, String... params) {
		if (params.length == 0) {
			showHelp(player);
			return;
		} if (COMMAND_STOP.equalsIgnoreCase(params[0]) || COMMAND_START.equalsIgnoreCase(params[0])) {
			handleStartStopConcert(player, params);
		}
	}
	
	protected void handleStartStopConcert(Player player, String... params) {
		if (params.length != 2 || !NumberUtils.isDigits(params[1])) {
			showHelp(player);
			return;
		}
		int iuId = NumberUtils.toInt(params[1]);
		if (!isValidConcertLocationId(player, iuId)) {
			showHelp(player);
			return;
		} if (COMMAND_START.equalsIgnoreCase(params[0])) {
			if (IuService.getInstance().isConcertInProgress(iuId)) {
				PacketSendUtility.sendMessage(player, "<Concert> " + iuId + " is already start");
			} else {
				PacketSendUtility.sendMessage(player, "<Concert> " + iuId + " started!");
				IuService.getInstance().startConcert(iuId);
			}
		} else if (COMMAND_STOP.equalsIgnoreCase(params[0])) {
			if (!IuService.getInstance().isConcertInProgress(iuId)) {
				PacketSendUtility.sendMessage(player, "<Concert> " + iuId + " is not start!");
			} else {
				PacketSendUtility.sendMessage(player, "<Concert> " + iuId + " stopped!");
				IuService.getInstance().stopConcert(iuId);
			}
		}
	}
	
	protected boolean isValidConcertLocationId(Player player, int iuId) {
		if (!IuService.getInstance().getIuLocations().keySet().contains(iuId)) {
			PacketSendUtility.sendMessage(player, "Id " + iuId + " is invalid");
			return false;
		}
		return true;
	}
	
	protected void showHelp(Player player) {
		PacketSendUtility.sendMessage(player, "AdminCommand //iu start|stop <Id>");
	}
}
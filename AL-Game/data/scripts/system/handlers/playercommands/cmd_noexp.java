/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen,      	    *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package playercommands;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Wakizashi
 */
public class cmd_noexp extends PlayerCommand {

	public cmd_noexp() {
		super("noexp");
	}

	@Override
	public void execute(Player player, String... params) {
		if (player.getCommonData().getNoExp()) {
			player.getCommonData().setNoExp(false);
			PacketSendUtility.sendMessage(player, "Experience rewards are reactivated !");
		}
		else {
			player.getCommonData().setNoExp(true);
			PacketSendUtility.sendMessage(player, "Experience rewards are desactivated !");
		}
	}

	@Override
	public void onFail(Player player, String message) {
		// TODO Auto-generated method stub
	}
}

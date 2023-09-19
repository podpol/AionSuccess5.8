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
import com.aionemu.gameserver.services.abyss.AbyssPointsService;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Maestross
 */
public class cmd_exchange extends PlayerCommand {

	public cmd_exchange() {
		super("exchange");
	}

	@Override
	public void execute(Player player, String... params) {
		int ap = 15000;
		int derived = 186000147;
		int derived_q = 5;
		if (player.getAbyssRank().getAp() < ap) {
			PacketSendUtility.sendMessage(player, "Du hast nicht genug AP, du hast nur: " + ap);
			return;
		}
		ItemService.addItem(player, derived, derived_q);
		AbyssPointsService.addAp(player, -ap);

	}
}

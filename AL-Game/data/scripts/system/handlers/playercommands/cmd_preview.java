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
import com.aionemu.gameserver.services.item.ItemRemodelService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Kashim
 */
public class cmd_preview extends PlayerCommand {

	private static final int REMODEL_PREVIEW_DURATION = 15;

	public cmd_preview() {
		super("preview");
	}

	public void executeCommand(Player admin, String[] params) {

		if (params.length < 1 || params[0] == "") {
			PacketSendUtility.sendMessage(admin, "Syntax: .preview <itemid>");
			return;
		}

		int itemId = 0;
		try {
			itemId = Integer.parseInt(params[0]);
		}
		catch (Exception e) {
			PacketSendUtility.sendMessage(admin, "Error! Item id's are numbers like 187000090 or [item:187000090]!");
			return;
		}
		ItemRemodelService.commandViewRemodelItem(admin, itemId, REMODEL_PREVIEW_DURATION);
	}

	@Override
	public void execute(Player player, String... params) {
		executeCommand(player, params);
	}
}

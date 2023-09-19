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

import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/**
 * @author Maestros
 */
public class cmd_asmo_channel extends PlayerCommand {

	public cmd_asmo_channel() {
		super("asmo");
	}

	@Override
	public void execute(Player player, String... params) {
		if (player.getRace() == Race.ASMODIANS && !player.isInPrison()) {
			int i = 1;
			boolean check = true;
			String adminTag = "";

			if (params.length < 1) {
				PacketSendUtility.sendMessage(player, "syntax : .asmo <message>");
				return;
			}
			StringBuilder sb = new StringBuilder(adminTag);
			if (AdminConfig.CUSTOMTAG_ENABLE) {
				if (player.getAccessLevel() == 1) {
					adminTag = AdminConfig.ADMIN_TAG_1.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 2) {
					adminTag = AdminConfig.ADMIN_TAG_2.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 3) {
					adminTag = AdminConfig.ADMIN_TAG_3.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 4) {
					adminTag = AdminConfig.ADMIN_TAG_4.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 5) {
					adminTag = AdminConfig.ADMIN_TAG_5.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 6) {
					adminTag = AdminConfig.ADMIN_TAG_6.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 7) {
					adminTag = AdminConfig.ADMIN_TAG_7.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 8) {
					adminTag = AdminConfig.ADMIN_TAG_8.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 9) {
					adminTag = AdminConfig.ADMIN_TAG_9.replace("%s", sb.toString());
				}
				else if (player.getAccessLevel() == 10) {
					adminTag = AdminConfig.ADMIN_TAG_10.replace("%s", sb.toString());
				}
			}

			adminTag += player.getName() + " : ";

			StringBuilder sbMessage;
			if (player.isGM()) {
				sbMessage = new StringBuilder("[Asmodians]" + " " + adminTag);
			}
			else {
				sbMessage = new StringBuilder("[Asmodians]" + " " + player.getName() + " : ");
			}
			Race adminRace = Race.ASMODIANS;

			for (String s : params) {
				if (i++ != 0 && (check)) {
					sbMessage.append(s).append(" ");
				}
			}

			String message = sbMessage.toString().trim();
			int messageLenght = message.length();

			final String sMessage = message.substring(0, CustomConfig.MAX_CHAT_TEXT_LENGHT > messageLenght ? messageLenght : CustomConfig.MAX_CHAT_TEXT_LENGHT);
			final boolean toAll = params[0].equals("ALL");
			final Race race = adminRace;

			World.getInstance().doOnAllPlayers(new Visitor<Player>() {

				@Override
				public void visit(Player player) {
					if (toAll || player.getRace() == race || (player.getAccessLevel() > 0)) {
						PacketSendUtility.sendMessage(player, sMessage);
					}
				}
			});
		}
		else {
			PacketSendUtility.sendMessage(player, "You are Elyos! You can't use this chat. Please use .ely <message> to use you're faction chat!");

		}
	}

	@Override
	public void onFail(Player player, String message) {
		PacketSendUtility.sendMessage(player, "syntax : .asmo <message>");
	}
}


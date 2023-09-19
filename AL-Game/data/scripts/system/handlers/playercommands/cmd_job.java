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

public class cmd_job extends PlayerCommand {

	public cmd_job() {
		super("job");
	}

	@Override
	public void execute(Player player, String... params) {
		player.getSkillList().addSkill(player, 30002, 499); // Vita
		player.getSkillList().addSkill(player, 30003, 499); // Ether
		player.getSkillList().addSkill(player, 40001, 550); // Cuisine
		player.getSkillList().addSkill(player, 40002, 550); // Armes
		player.getSkillList().addSkill(player, 40003, 550); // Armure
		player.getSkillList().addSkill(player, 40004, 550); // Couture
		player.getSkillList().addSkill(player, 40007, 550); // Alchimie
		player.getSkillList().addSkill(player, 40008, 550); // Artisanat
	}

	@Override
	public void onFail(Player player, String message) {
		PacketSendUtility.sendMessage(player, "Syntax: .job ");
	}
}

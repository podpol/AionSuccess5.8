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
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUEST_ACTION;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author ginho1
 */
public class cmd_questrestart extends PlayerCommand {

	public cmd_questrestart() {
		super("questrestart");
	}

	private int id;

	@Override
	public void execute(Player player, String... params) {

		if (params == null || params.length < 1) {
			PacketSendUtility.sendMessage(player, "syntax .questrestart <quest id>");
			return;
		}

		try {
			id = Integer.valueOf(params[0]);
		}
		catch (NumberFormatException e) {
			PacketSendUtility.sendMessage(player, "syntax .questrestart <quest id>");
			return;
		}

		QuestState qs = player.getQuestStateList().getQuestState(id);

		if (qs == null || id == 1006 || id == 2008 || id == 10021 || id == 20021 || id == 18602 || id == 28602) {
			PacketSendUtility.sendMessage(player, "Quest [quest: " + id + "] can't be restarted.");
			return;
		}

		if (qs.getStatus() == QuestStatus.START || qs.getStatus() == QuestStatus.REWARD) {
			if (qs.getQuestVarById(0) != 0) {
				qs.setStatus(QuestStatus.START);
				qs.setQuestVar(0);
				PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(id, qs.getStatus(), qs.getQuestVars().getQuestVars()));
				PacketSendUtility.sendMessage(player, "Quest [quest: " + id + "] restarted.");
			}
			else {
				PacketSendUtility.sendMessage(player, "Quest [quest: " + id + "] can't be restarted.");
			}
		}
		else {
			PacketSendUtility.sendMessage(player, "Quest [quest: " + id + "] can't be restarted.");
		}
	}

	@Override
	public void onFail(Player player, String message) {
		// TODO Auto-generated method stub
	}
}

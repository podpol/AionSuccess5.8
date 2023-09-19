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
package quest.theobomos;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RewardType;
import com.aionemu.gameserver.model.templates.quest.Rewards;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUEST_ACTION;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _3096Examine_The_Extraction_Devices extends QuestHandler
{
	private final static int questId = 3096;
	
	public _3096Examine_The_Extraction_Devices() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(798225).addOnQuestStart(questId);
		qe.registerQuestNpc(798225).addOnTalkEvent(questId);
		qe.registerQuestNpc(700423).addOnTalkEvent(questId);
		qe.registerQuestNpc(700424).addOnTalkEvent(questId);
		qe.registerQuestNpc(700425).addOnTalkEvent(questId);
		qe.registerQuestNpc(700426).addOnTalkEvent(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc) {
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		} if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.getStatus() == QuestStatus.COMPLETE) {
			if (targetId == 798225) {
				switch (env.getDialog()) {
					case START_DIALOG: {
						return sendQuestDialog(env, 1011);
					} default:
						return sendQuestStartDialog(env);
				}
			}
		} if (qs == null) {
			return false;
		} if (qs.getStatus() == QuestStatus.START) {
			switch (targetId) {
				case 798225: {
					switch (env.getDialog()) {
						case START_DIALOG: {
							long itemCount1 = player.getInventory().getItemCountByItemId(182208067);
							long itemCount2 = player.getInventory().getItemCountByItemId(182208068);
							long itemCount3 = player.getInventory().getItemCountByItemId(182208069);
							long itemCount4 = player.getInventory().getItemCountByItemId(182208070);
							if (itemCount1 >= 1 && itemCount2 >= 1 && itemCount3 >= 1 && itemCount4 >= 1) {
								return sendQuestDialog(env, 5);
							}
						} case SELECT_NO_REWARD: {
							qs.setStatus(QuestStatus.COMPLETE);
							qs.setCompleteCount(qs.getCompleteCount() + 1);
							removeQuestItem(env, 182208067, 1);
							removeQuestItem(env, 182208068, 1);
							removeQuestItem(env, 182208069, 1);
							removeQuestItem(env, 182208070, 1);
							Rewards rewards = DataManager.QUEST_DATA.getQuestById(questId).getRewards().get(0);
							int rewardExp = rewards.getExp();
							int rewardKinah = (int) (player.getRates().getQuestKinahRate() * rewards.getGold());
							giveQuestItem(env, 182400001, rewardKinah);
							player.getCommonData().addExp(rewardExp, RewardType.QUEST);
							PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(questId, QuestStatus.COMPLETE, 2));
							PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 0));
							updateQuestStatus(env);
							return true;
						}
					}
				} case 700423: {
					switch (env.getDialog()) {
						case USE_OBJECT: {
							if (player.getInventory().getItemCountByItemId(182208067) < 1) {
								return true;
							}
						}
					}
				} case 700424: {
					switch (env.getDialog()) {
						case USE_OBJECT: {
							if (player.getInventory().getItemCountByItemId(182208068) < 1) {
								return true;
							}
						}
					}
				} case 700425: {
					switch (env.getDialog()) {
						case USE_OBJECT: {
							if (player.getInventory().getItemCountByItemId(182208069) < 1) {
								return true;
							}
						}
					}
				} case 700426: {
					switch (env.getDialog()) {
						case USE_OBJECT: {
							if (player.getInventory().getItemCountByItemId(182208070) < 1) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
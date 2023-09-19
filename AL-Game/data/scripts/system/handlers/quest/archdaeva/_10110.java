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
package quest.archdaeva;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _10110 extends QuestHandler
{
    public static final int questId = 10110;
	private final static int[] npcs = {806075, 805351, 703463, 703464};
	
    public _10110() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        }
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerOnEnterZone(ZoneName.get("EYE_OF_RESHANTA_ENTRANCE_400010000"), questId);
    }
	
    @Override
    public boolean onZoneMissionEndEvent(QuestEnv env) {
        return defaultOnZoneMissionEndEvent(env);
    }
	
    @Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 10521, true);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null) {
            return false;
        }
        int var = qs.getQuestVarById(0);
        int targetId = 0;
        if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        } if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806075) { //Viola.
                switch (env.getDialog()) {
					case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        } else if (var == 1) {
							return sendQuestDialog(env, 1352);
						} else if (var == 2) {
							return sendQuestDialog(env, 1693);
						}
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case SELECT_ACTION_1013: {
						if (var == 0) {
							return sendQuestDialog(env, 1013);
						}
					} case SELECT_ACTION_1014: {
						if (var == 0) {
							return sendQuestDialog(env, 1014);
						}
					} case SELECT_ACTION_1353: {
						if (var == 1) {
							return sendQuestDialog(env, 1353);
						}
					} case SELECT_ACTION_1694: {
						if (var == 2) {
							return sendQuestDialog(env, 1694);
						}
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					} case STEP_TO_3: {
						playQuestMovie(env, 1007);
						removeQuestItem(env, 182216227, 1);
						removeQuestItem(env, 182216228, 1);
						removeQuestItem(env, 182216229, 1);
						removeQuestItem(env, 182216230, 1);
						return defaultCloseDialog(env, 2, 3, false, false, 182216231, 1, 0, 0);
					} case CHECK_COLLECTED_ITEMS: {
						return checkQuestItems(env, 1, 2, false, 10000, 10001);
					} case FINISH_DIALOG: {
						if (var == 2) {
							defaultCloseDialog(env, 2, 2);
						} else if (var == 1) {
							defaultCloseDialog(env, 1, 1);
						}
					}
                }
            } if (targetId == 703463) {
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        return closeDialogWindow(env);
					}
                }
            } if (targetId == 703464) {
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        return closeDialogWindow(env);
					}
                }
            } if (targetId == 805351) { //Giscours.
                switch (env.getDialog()) {
				    case START_DIALOG: {
						if (var == 3) {
							return sendQuestDialog(env, 2034);
						} else if (var == 5) {
							return sendQuestDialog(env, 2716);
						}
					} case SELECT_ACTION_2035: {
						if (var == 3) {
							return sendQuestDialog(env, 2035);
						}
					} case SELECT_ACTION_2717: {
						if (var == 5) {
							return sendQuestDialog(env, 2717);
						}
					} case SELECT_ACTION_2718: {
						if (var == 5) {
							return sendQuestDialog(env, 2718);
						}
					} case STEP_TO_4: {
						changeQuestStep(env, 3, 4, false);
						return closeDialogWindow(env);
					} case SET_REWARD: {
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					}
				}
            }
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 805351) { //Giscours.
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 10002);
				} else if (env.getDialog() == QuestDialog.SELECT_REWARD) {
					return sendQuestDialog(env, 5);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		}
        return false;
    }
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("EYE_OF_RESHANTA_ENTRANCE_400010000")) {
				if (var == 4) {
					changeQuestStep(env, 4, 5, false);
					return true;
				}
			}
		}
		return false;
	}
}
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

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.HandlerResult;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _10527Finding_The_Traces_Of_The_Sage extends QuestHandler
{
    public static final int questId = 10527;
	private final static int[] npcs = {806075, 806291, 703313, 703314, 703315, 731705, 731706, 731707};
	private final static int[] LF6MissionDarkRa71An = {244107}; //그림�? 잠입부대 아칸 정찰병.
	
    public _10527Finding_The_Traces_Of_The_Sage() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: LF6MissionDarkRa71An) {
            qe.registerQuestNpc(mob).addOnKillEvent(questId);
        }
		qe.registerOnLevelUp(questId);
		qe.registerQuestItem(182216075, questId); //그림�? 잠입부대 비밀 문서.
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerOnEnterZone(ZoneName.get("FALLOW_RUINS_210100000"), questId);
		qe.registerOnEnterZone(ZoneName.get("TARHA_KRALL_VILLAGE_210100000"), questId);
		qe.registerOnEnterZone(ZoneName.get("VALLEY_OF_THE_WAYWARD_210100000"), questId);
    }
	
	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}
	
	@Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 10526, true);
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
            if (targetId == 806075) { //Weatha.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        } else if (var == 2) {
                            return sendQuestDialog(env, 1695);
                        } else if (var == 12) {
                            return sendQuestDialog(env, 7182);
                        } else if (var == 13) {
                            return sendQuestDialog(env, 7523);
                        }
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case SELECT_ACTION_1696: {
						if (var == 2) {
							return sendQuestDialog(env, 1696);
						}
					} case SELECT_ACTION_7183: {
						if (var == 12) {
							return sendQuestDialog(env, 7183);
						}
					} case SELECT_ACTION_7524: {
						if (var == 13) {
							return sendQuestDialog(env, 7524);
						}
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					} case SELECT_REWARD: {
                        changeQuestStep(env, 2, 3, false);
						return closeDialogWindow(env);
					} case STEP_TO_14: {
						removeQuestItem(env, 182216105, 1); //밀봉�?� 비밀 문서.
						return defaultCloseDialog(env, 13, 14, false, false, 182216075, 1, 0, 0); //그림�? 잠입부대 비밀 문서.
					} case CHECK_COLLECTED_ITEMS: {
						return checkQuestItems(env, 12, 13, false, 10000, 10001);
					} case FINISH_DIALOG: {
						if (var == 13) {
							defaultCloseDialog(env, 13, 13);
						} else if (var == 12) {
							defaultCloseDialog(env, 12, 12);
						}
					}
                }
            } if (targetId == 806291) { //�?��?보보.
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 1) {
							return sendQuestDialog(env, 1355);
						}
					} case SELECT_ACTION_1356: {
						if (var == 1) {
							return sendQuestDialog(env, 1356);
						}
					} case STEP_TO_2: {
						changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 703313) { //움찔거리는 나무 �?�굴.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 4) {
                            changeQuestStep(env, 4, 5, false);
							return closeDialogWindow(env);
                        }
					}
                }
            } if (targetId == 731705) { //빛나는 �?�?�?� 탑 조�?.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 5) {
                            changeQuestStep(env, 5, 6, false);
							return closeDialogWindow(env);
                        }
					}
                }
            } if (targetId == 703314) { //움�?�?�는 냄비.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 7) {
                            changeQuestStep(env, 7, 8, false);
							return closeDialogWindow(env);
                        }
					}
                }
            } if (targetId == 731706) { //�?롱한 �?�?�?� 탑 조�?.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 8) {
                            changeQuestStep(env, 8, 9, false);
							return closeDialogWindow(env);
                        }
					}
                }
            } if (targetId == 703315) { //꿈틀대는 �?루.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 10) {
							changeQuestStep(env, 10, 11, false);
							QuestService.addNewSpawn(210100000, 1, 244107, 2130.926f, 2857.0728f, 246.58539f, (byte) 106); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2140.2065f, 2846.4036f, 246.90082f, (byte) 61); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2136.1633f, 2837.4336f, 246.85143f, (byte) 51); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2115.5115f, 2826.012f, 246.7515f, (byte) 117); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2104.703f, 2824.9727f, 249.81421f, (byte) 75); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2122.6865f, 2793.037f, 251.90955f, (byte) 87); //그림�? 잠입부대 아칸 정찰병.
							QuestService.addNewSpawn(210100000, 1, 244107, 2103.6562f, 2800.0583f, 252.26054f, (byte) 3); //그림�? 잠입부대 아칸 정찰병.
							return closeDialogWindow(env);
                        }
					}
                }
            }
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806075) { //Weatha.
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
    public boolean onKillEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (var == 11) {
                int var1 = qs.getQuestVarById(1);
                if (var1 >= 0 && var1 < 6) {
                    return defaultOnKillEvent(env, LF6MissionDarkRa71An, var1, var1 + 1, 1);
                } else if (var1 == 6) {
					qs.setQuestVar(12);
					updateQuestStatus(env);
					QuestService.addNewSpawn(210100000, 1, 244108, player.getX(), player.getY(), player.getZ(), (byte) 0); //잠입부대 부사관 투르�??.
                    return true;
                }
            }
        }
        return false;
    }
	
	@Override
	public HandlerResult onItemUseEvent(QuestEnv env, Item item) {
		final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getQuestVarById(0) == 14) {
			return HandlerResult.fromBoolean(useQuestItem(env, item, 14, 15, true));
		}
		return HandlerResult.FAILED;
	}
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("FALLOW_RUINS_210100000")) {
				if (var == 3) {
					changeQuestStep(env, 3, 4, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("TARHA_KRALL_VILLAGE_210100000")) {
				if (var == 6) {
					changeQuestStep(env, 6, 7, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("VALLEY_OF_THE_WAYWARD_210100000")) {
				if (var == 9) {
					changeQuestStep(env, 9, 10, false);
					return true;
				}
			}
		}
		return false;
	}
}
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

import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _10529Protection_Artifact_2 extends QuestHandler
{
    public static final int questId = 10529;
	private final static int[] npcs = {806075, 806293, 806294, 806295, 703317, 731710};
	private final static int[] LF6MissionDarkFi75An = {244111}; //그림�? 아칸 전투병.
	private final static int[] LF6MissionDarkWi75An = {244112}; //그림�? 아칸 마법병.
	
    public _10529Protection_Artifact_2() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: LF6MissionDarkFi75An) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		} for (int mob: LF6MissionDarkWi75An) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterWorld(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerQuestNpc(244113).addOnKillEvent(questId); //헬뇨르.
    }
	
	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}
	
	@Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 10528, true);
    }
	
	@Override
    public boolean onEnterWorldEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (player.getWorldId() == 301690000) { //오드광산.
			if (qs != null && qs.getStatus() == QuestStatus.START) {
                int var = qs.getQuestVars().getQuestVars();
                if (var == 4) {
                    changeQuestStep(env, 4, 5, false);
				}
			}
		}
        return false;
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
                        } else if (var == 10) {
							return sendQuestDialog(env, 6502);
						}
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case SELECT_ACTION_6503: {
						if (var == 10) {
							return sendQuestDialog(env, 6503);
						}
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					} case SET_REWARD: {
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 806295) { //�?�제르.
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 1) {
							return sendQuestDialog(env, 1352);
						}
					} case SELECT_ACTION_1353: {
						if (var == 1) {
							return sendQuestDialog(env, 1353);
						}
					} case STEP_TO_2: {
						changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 731710) { //부서진 천족 회랑 장치.
                switch (env.getDialog()) {
				    case USE_OBJECT: {
						if (var == 2) {
							return sendQuestDialog(env, 1693);
						} else if (var == 3) {
							return sendQuestDialog(env, 2034);
						}
					} case SELECT_ACTION_1694: {
						if (var == 2) {
							return sendQuestDialog(env, 1694);
						}
					} case SELECT_ACTION_2035: {
						if (var == 3) {
							return sendQuestDialog(env, 2035);
						}
					} case STEP_TO_4: {
						changeQuestStep(env, 3, 4, false);
						removeQuestItem(env, 182216078, 20); //천족 회랑 장치 부품.
						removeQuestItem(env, 182216079, 7); //천족 회랑 �?�력 장치.
						removeQuestItem(env, 182216080, 1); //천족 회랑 �?�력핵.
						WorldMapInstance AetherMine = InstanceService.getNextAvailableInstance(301690000); //오드광산.
						InstanceService.registerPlayerWithInstance(AetherMine, player);
						TeleportService2.teleportTo(player, 301690000, AetherMine.getInstanceId(), 323, 267, 259);
						return closeDialogWindow(env);
					} case CHECK_COLLECTED_ITEMS: {
						return checkQuestItems(env, 2, 3, false, 10000, 10001);
					} case FINISH_DIALOG: {
						if (var == 3) {
							defaultCloseDialog(env, 3, 3);
						} else if (var == 2) {
							defaultCloseDialog(env, 2, 2);
						}
					}
				}
            } if (targetId == 806293) { //�?��?보보.
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 5) {
							return sendQuestDialog(env, 2717);
						}
					} case SELECT_ACTION_2718: {
						if (var == 5) {
							return sendQuestDialog(env, 2718);
						}
					} case STEP_TO_6: {
						changeQuestStep(env, 5, 6, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 703317) { //마족 차�?�?� 소용�?��?�.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 7) {
							playQuestMovie(env, 1006);
                            changeQuestStep(env, 7, 8, false);
							ThreadPoolManager.getInstance().schedule(new Runnable() {
								@Override
								public void run() {
									QuestService.addNewSpawn(301690000, player.getInstanceId(), 244113, (float) 172.000, (float) 156.000, (float) 230.53053, (byte) 96); //헬뇨르.
									QuestService.addNewSpawn(301690000, player.getInstanceId(), 806294, (float) 173.08958, (float) 153.30316, (float) 230.3820, (byte) 67); //쓰러진 �?��?보보.
								}
							}, 30000);
							return closeDialogWindow(env);
                        }
					}
                }
            } if (targetId == 806294) { //쓰러진 �?��?보보.
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 9) {
							return sendQuestDialog(env, 4080);
						}
					} case SELECT_ACTION_4081: {
					    if (var == 9) {
							return sendQuestDialog(env, 4081);
						}
					} case STEP_TO_10: {
						//깊�?� 잠�? 빠진 �?��?보보.
						giveQuestItem(env, 182216107, 1);
						changeQuestStep(env, 9, 10, false);
						TeleportService2.teleportTo(player, 210100000, 639.8976f, 2404.188f, 248.18759f, (byte) 79);
						return closeDialogWindow(env);
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
        int targetId = env.getTargetId();
		if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (var == 6) {
				int[] LF6MissionDarkFi75An = {244111}; //그림�? 아칸 전투병.
				int[] LF6MissionDarkWi75An = {244112}; //그림�? 아칸 마법병.
				switch (targetId) {
					case 244111: { //그림�? 아칸 전투병.
						return defaultOnKillEvent(env, LF6MissionDarkFi75An, 0, 7, 1);
					} case 244112: { //그림�? 아칸 마법병.
						qs.setQuestVar(7);
					    updateQuestStatus(env);
						return defaultOnKillEvent(env, LF6MissionDarkWi75An, 0, 3, 2);
					}
				}
            } else if (var == 8) {
				switch (targetId) {
                    case 244113: { //헬뇨르.
						qs.setQuestVar(9);
						updateQuestStatus(env);
						return true;
					}
                }
			}
		}
		return false;
    }
}
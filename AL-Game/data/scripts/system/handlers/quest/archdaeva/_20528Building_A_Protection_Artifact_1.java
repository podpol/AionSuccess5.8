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
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _20528Building_A_Protection_Artifact_1 extends QuestHandler
{
    public static final int questId = 20528;
	private final static int[] npcs = {806079, 806296, 703324, 731714, 731715};
	private final static int[] DF6MissionGriffon73An = {244125}; //스피리투스�?� 새�?�. 
	
    public _20528Building_A_Protection_Artifact_1() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: DF6MissionGriffon73An) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerQuestItem(182216088, questId); //잠든 위�?보보.
		qe.registerQuestNpc(244126).addOnKillEvent(questId); //�?욕스런 퓨케.
		qe.registerOnEnterZone(ZoneName.get("DF6_SENSORY_AREA_Q20528_A_220110000"), questId);
		qe.registerOnEnterZone(ZoneName.get("DF6_SENSORY_AREA_Q20528_B_220110000"), questId);
    }
	
	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}
	
	@Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 20527, true);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        final Npc npc = (Npc) env.getVisibleObject();
        if (qs == null) {
            return false;
        }
        int var = qs.getQuestVarById(0);
        int targetId = 0;
        if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        } if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806079) { //Feregran.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        } else if (var == 2) {
                            return sendQuestDialog(env, 1695);
                        }
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case SELECT_ACTION_1696: {
						if (var == 2) {
							return sendQuestDialog(env, 1696);
						}
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					} case STEP_TO_3: {
                        changeQuestStep(env, 2, 3, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 806296) { //�?��?보보.
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 1) {
							return sendQuestDialog(env, 1354);
						}
					} case SELECT_ACTION_1355: {
						if (var == 1) {
							return sendQuestDialog(env, 1355);
						}
					} case STEP_TO_2: {
						//잠든 위�?보보.
						giveQuestItem(env, 182216088, 1);
						changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 703324) { //바르테온�?� 보물 �?�?.
                switch (env.getDialog()) {
				    case USE_OBJECT: {
						if (var == 5) {
						    qs.setQuestVar(6);
							updateQuestStatus(env);
							//�?명�?� 눈물.
							giveQuestItem(env, 182216089, 1);
						    return closeDialogWindow(env);
						}
					}
				}
            } if (targetId == 731714) { //�?춰진 하늘섬 �?��?��?.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
						if (var == 6) {
							return sendQuestDialog(env, 3057);
						}
					} case SELECT_ACTION_3058: {
						if (var == 6) {
							return sendQuestDialog(env, 3058);
						}
					} case STEP_TO_7: {
						changeQuestStep(env, 6, 7, false);
						TeleportService2.teleportTo(player, 220110000, 2230.3057f, 2310.2126f, 535.7577f, (byte) 91);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 731715) {
                switch (env.getDialog()) {
                    case USE_OBJECT: {
                        if (var == 9) {
							return sendQuestDialog(env, 3739);
						} else if (var == 10) {
							return sendQuestDialog(env, 4080);
						}
					} case SELECT_ACTION_3740: {
						if (var == 9) {
							return sendQuestDialog(env, 3740);
						}
					} case SELECT_ACTION_4081: {
						if (var == 10) {
							return sendQuestDialog(env, 4081);
						}
					} case STEP_TO_9: {
                        changeQuestStep(env, 9, 10, false);
						return closeDialogWindow(env);
					} case STEP_TO_10: {
						playQuestMovie(env, 877);
						giveQuestItem(env, 182216094, 1); //�?명�?� 눈물.
						giveQuestItem(env, 182216088, 1); //잠든 위�?보보.
						changeQuestStep(env, 10, 11, false);
						return closeDialogWindow(env);
					}
                }
            }
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806079) { //Feregran.
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
			if (var == 4) {
				int[] DF6MissionGriffon73An = {244125}; //스피리투스�?� 새�?�.
				switch (targetId) {
					case 244125: { //바르탈 해�? �?�꾼.
						return defaultOnKillEvent(env, DF6MissionGriffon73An, 0, 10, 1);
					}
				} switch (targetId) {
				    case 244126: { //�?욕스런 퓨케.
						qs.setQuestVar(5);
						updateQuestStatus(env);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
    public HandlerResult onItemUseEvent(final QuestEnv env, Item item) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (var == 8) {
                return HandlerResult.fromBoolean(useQuestItem(env, item, 8, 9, false));
            } if (var == 11) {
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						giveQuestItem(env, 164002348, 1); //잠�?� 든 위�?보보.
						TeleportService2.teleportTo(env.getPlayer(), 220110000, 2301.303f, 2574.1775f, 310.40747f, (byte) 34);
					}
				}, 5000);
                return HandlerResult.fromBoolean(useQuestItem(env, item, 11, 12, true));
            }
        }
        return HandlerResult.FAILED;
    }
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("DF6_SENSORY_AREA_Q20528_A_220110000")) {
				if (var == 3) {
					changeQuestStep(env, 3, 4, false);
					QuestService.addNewSpawn(220110000, 1, 244125, 2691.0403f, 647.167f, 270.70297f, (byte) 60); //바르탈 해�? �?�꾼.
                    QuestService.addNewSpawn(220110000, 1, 244125, 2681.0925f, 632.02625f, 269.82822f, (byte) 44); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2671.9636f, 635.10345f, 268.55148f, (byte) 37); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2685.7607f, 654.4808f, 270.05008f, (byte) 52); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2683.1902f, 646.7986f, 269.64877f, (byte) 50); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2669.229f, 644.03455f, 267.3993f, (byte) 42); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2677.9429f, 654.6115f, 268.44983f, (byte) 60); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2671.857f, 651.171f, 267.34818f, (byte) 49); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2662.8188f, 634.545f, 267.96545f, (byte) 11); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244125, 2683.5947f, 663.9745f, 268.92566f, (byte) 67); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(220110000, 1, 244126, 2684.0596f, 639.54694f, 269.78577f, (byte) 51); //�?욕스런 퓨케.
					return true;
				}
			} else if (zoneName == ZoneName.get("DF6_SENSORY_AREA_Q20528_B_220110000")) {
				if (var == 7) {
					changeQuestStep(env, 7, 8, false);
					return true;
				}
			}
		}
		return false;
	}
}
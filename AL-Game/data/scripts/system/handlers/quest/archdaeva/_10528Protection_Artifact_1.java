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

public class _10528Protection_Artifact_1 extends QuestHandler
{
    public static final int questId = 10528;
	private final static int[] npcs = {806075, 806291, 703316, 731708, 731709};
	private final static int[] LF6MissionTesinon73An = {244109}; //바르탈 해�? �?�꾼.
	
    public _10528Protection_Artifact_1() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: LF6MissionTesinon73An) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerQuestItem(182216076, questId); //잠든 �?��?보보.
		qe.registerQuestNpc(244110).addOnKillEvent(questId); //�?욕스런 �?�쎄오.
		qe.registerOnEnterZone(ZoneName.get("LF6_SENSORY_AREA_Q10528_A_210100000"), questId);
		qe.registerOnEnterZone(ZoneName.get("LF6_SENSORY_AREA_Q10528_B_210100000"), questId);
    }
	
	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}
	
	@Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 10527, true);
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
            if (targetId == 806075) { //Weatha.
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
            } if (targetId == 806291) { //�?��?보보.
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
						//잠든 �?��?보보.
						giveQuestItem(env, 182216076, 1);
						changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 703316) { //바르테온�?� 보물 �?�?.
                switch (env.getDialog()) {
				    case USE_OBJECT: {
						if (var == 5) {
							qs.setQuestVar(6);
							updateQuestStatus(env);
							//�?명�?� 숨결.
							giveQuestItem(env, 182216077, 1);
						    return closeDialogWindow(env);
						}
					}
				}
            } if (targetId == 731708) {
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
						TeleportService2.teleportTo(player, 210100000, 2497.7742f, 643.23785f, 458.36703f, (byte) 75);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 731709) {
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
						playQuestMovie(env, 1005);
						giveQuestItem(env, 182216093, 1); //�?명�?� 숨결.
						giveQuestItem(env, 182216076, 1); //잠든 �?��?보보.
						changeQuestStep(env, 10, 11, false);
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
			if (var == 4) {
				int[] LF6MissionTesinon73An = {244109}; //바르탈 해�? �?�꾼.
				switch (targetId) {
					case 244109: { //바르탈 해�? �?�꾼.
						return defaultOnKillEvent(env, LF6MissionTesinon73An, 0, 10, 1);
					}
				} switch (targetId) {
				    case 244110: { //�?욕스런 �?�쎄오.
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
						giveQuestItem(env, 164002347, 1); //잠�?� 든 �?��?보보.
						TeleportService2.teleportTo(env.getPlayer(), 210100000, 2412.4185f, 775.91626f, 236.68776f, (byte) 79);
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
			if (zoneName == ZoneName.get("LF6_SENSORY_AREA_Q10528_A_210100000")) {
				if (var == 3) {
					changeQuestStep(env, 3, 4, false);
					QuestService.addNewSpawn(210100000, 1, 244109, 2889.9578f, 2380.9097f, 236.62764f, (byte) 74); //바르탈 해�? �?�꾼.
                    QuestService.addNewSpawn(210100000, 1, 244109, 2906.218f, 2378.6492f, 235.3418f, (byte) 65); //바르탈 해�? �?�꾼.
                    QuestService.addNewSpawn(210100000, 1, 244109, 2891.9438f, 2361.2007f, 233.62851f, (byte) 35); //바르탈 해�? �?�꾼.
                    QuestService.addNewSpawn(210100000, 1, 244109, 2914.7168f, 2370.3203f, 236.04294f, (byte) 70); //바르탈 해�? �?�꾼.
                    QuestService.addNewSpawn(210100000, 1, 244109, 2874.361f, 2380.2175f, 237.0f, (byte) 94); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244109, 2890.476f, 2352.9487f, 233.9343f, (byte) 90); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244109, 2868.4194f, 2341.2292f, 237.02196f, (byte) 16); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244109, 2873.2576f, 2352.1912f, 237.20416f, (byte) 112); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244109, 2851.1619f, 2370.3933f, 237.10155f, (byte) 110); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244109, 2881.8997f, 2341.9282f, 235.0179f, (byte) 110); //바르탈 해�? �?�꾼.
					QuestService.addNewSpawn(210100000, 1, 244110, 2865.538f, 2363.0034f, 237.20416f, (byte) 111); //�?욕스런 �?�쎄오.
					return true;
				}
			} else if (zoneName == ZoneName.get("LF6_SENSORY_AREA_Q10528_B_210100000")) {
				if (var == 7) {
					changeQuestStep(env, 7, 8, false);
					return true;
				}
			}
		}
		return false;
	}
}
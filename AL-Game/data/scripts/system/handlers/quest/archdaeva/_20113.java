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
import com.aionemu.gameserver.questEngine.handlers.HandlerResult;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _20113 extends QuestHandler
{
    public static final int questId = 20113;
	private final static int[] npcs = {805356, 731844, 731845};
	
    public _20113() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        }
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterWorld(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerQuestNpc(248013).addOnKillEvent(questId); //IDAbRe_Core_03_Witch_Boss_Ae.
		qe.registerQuestNpc(248452).addOnKillEvent(questId); //IDAbRe_Core_03_Resurrect_Drakan_Atk_04.
		qe.registerQuestNpc(248950).addOnKillEvent(questId); //IDAbRe_Core_03_Real_Witch_1_3_An.
		qe.registerOnEnterZone(ZoneName.get("IDABRE_CORE_03_Q10113_A_301720000"), questId);
    }
	
	@Override
    public boolean onZoneMissionEndEvent(QuestEnv env) {
        return defaultOnZoneMissionEndEvent(env);
    }
	
    @Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 20112, true);
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
            if (targetId == 805356) { //Pontekane.
                switch (env.getDialog()) {
					case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        }
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case SELECT_ACTION_1013: {
						if (var == 0) {
							return sendQuestDialog(env, 1013);
						}
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 731844) { //IDAbRe_Core_03_FOBJ_Empty_Altar_Q10113A.
                switch (env.getDialog()) {
				    case START_DIALOG: {
						if (var == 4) {
							return sendQuestDialog(env, 2378);
						}
					} case SELECT_ACTION_2379: {
						if (var == 4) {
							return sendQuestDialog(env, 2379);
						}
					} case STEP_TO_5: {
						changeQuestStep(env, 4, 5, false);
						return closeDialogWindow(env);
					}
				}
            } if (targetId == 731845) { //IDAbRe_Core_03_FOBJ_Ere_Mirror_Q10113A.
                switch (env.getDialog()) {
				    case START_DIALOG: {
						if (var == 6) {
							return sendQuestDialog(env, 3057);
						}
					} case SELECT_ACTION_3058: {
						if (var == 6) {
							return sendQuestDialog(env, 3058);
						}
					} case SET_REWARD: {
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					}
				}
            }
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 805356) { //Pontekane.
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
    public boolean onEnterWorldEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (player.getWorldId() == 301720000) { //Mirash Sanctuary.
			if (qs != null && qs.getStatus() == QuestStatus.START) {
                int var = qs.getQuestVars().getQuestVars();
                if (var == 1) {
                    changeQuestStep(env, 1, 2, false);
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
			if (zoneName == ZoneName.get("IDABRE_CORE_03_Q10113_A_301720000")) {
				if (var == 2) {
					changeQuestStep(env, 2, 3, false);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onKillEvent(final QuestEnv env) {
		final Player player = env.getPlayer();
		final int instanceId = player.getInstanceId();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs == null || qs.getStatus() != QuestStatus.START) {
			return false;
		}
		int var = qs.getQuestVarById(0);
		final Npc npc = (Npc) env.getVisibleObject();
		switch (env.getTargetId()) {
			case 248452: //IDAbRe_Core_03_Resurrect_Drakan_Atk_04.
			case 248950: //IDAbRe_Core_03_Real_Witch_1_3_An.
			    if (var == 3)  {
					qs.setQuestVar(4);
					updateQuestStatus(env);
					return true;
				}
			break;
			case 248013: //IDAbRe_Core_03_Witch_Boss_Ae.
			    if (var == 5)  {
					qs.setQuestVar(6);
					updateQuestStatus(env);
					return true;
				}
			break;
		}
		return false;
	}
}
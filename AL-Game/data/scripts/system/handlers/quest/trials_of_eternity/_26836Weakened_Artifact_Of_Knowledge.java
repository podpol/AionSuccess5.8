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
package quest.trials_of_eternity;

import com.aionemu.gameserver.model.gameobjects.player.Player;
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

public class _26836Weakened_Artifact_Of_Knowledge extends QuestHandler
{
    private final static int questId = 26836;
	private final static int[] npcs = {806572, 806578};
	private final static int[] IDEternity03GuardBoss = {246425, 246426};
	
    public _26836Weakened_Artifact_Of_Knowledge() {
        super(questId);
    }
	
	@Override
	public void register() {
		for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: IDEternity03GuardBoss) {
			qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
		qe.registerOnEnterWorld(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_A_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_B_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_C_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_D_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_E_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_F_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_G_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_H_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_I_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_J_301560000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDETERNITY_03_Q16836_K_301560000"), questId);
	}
	
	@Override
	public boolean onZoneMissionEndEvent(QuestEnv env) {
		return defaultOnZoneMissionEndEvent(env);
	}
	
	@Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(0);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.START) {
			if (targetId == 806572) { //Peregrine Start.
				switch (env.getDialog()) {
                    case START_DIALOG: {
						if (var == 0) {
							return sendQuestDialog(env, 1011);
						}
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case STEP_TO_1: {
						changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					}
                }
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806578) { //Peregrine End.
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
		if (player.getWorldId() == 301560000) { //Heart Of Eternity 5.5
            if (qs == null) {
                env.setQuestId(questId);
                if (QuestService.startQuest(env)) {
					return true;
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
			if (var == 8) {
				if (env.getTargetId() == 246425 || env.getTargetId() == 246426) {
					playQuestMovie(env, 955);
					changeQuestStep(env, 8, 9, false);
					return true;
				}
			} else if (var == 13) {
				if (env.getTargetId() == 247075) {
					changeQuestStep(env, 13, 14, true);
					return true;
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
			if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_A_301560000")) {
				if (var == 1) {
					changeQuestStep(env, 1, 2, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_B_301560000")) {
				if (var == 2) {
					changeQuestStep(env, 2, 3, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_C_301560000")) {
				if (var == 3) {
					changeQuestStep(env, 3, 4, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_D_301560000")) {
				if (var == 4) {
					changeQuestStep(env, 4, 5, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_E_301560000")) {
				if (var == 5) {
					changeQuestStep(env, 5, 6, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_F_301560000")) {
				if (var == 6) {
					changeQuestStep(env, 6, 7, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_G_301560000")) {
				if (var == 7) {
					changeQuestStep(env, 7, 8, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_H_301560000")) {
				if (var == 9) {
					playQuestMovie(env, 964);
					changeQuestStep(env, 9, 10, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_I_301560000")) {
				if (var == 10) {
					changeQuestStep(env, 10, 11, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_J_301560000")) {
				if (var == 11) {
					changeQuestStep(env, 11, 12, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDETERNITY_03_Q16836_K_301560000")) {
				if (var == 12) {
					changeQuestStep(env, 12, 13, false);
					return true;
				}
			}
		}
		return false;
	}
}
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
package quest.stigma_vision;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.LetterType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.HandlerResult;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.mail.SystemMailService;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _23832Better_Than_Great extends QuestHandler
{
    private final static int questId = 23832;
	
    public _23832Better_Than_Great() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
	@Override
	public void register() {
		qe.registerOnLevelUp(questId);
		qe.registerQuestNpc(204061).addOnTalkEvent(questId); //Aud.
		qe.registerQuestNpc(834921).addOnTalkEvent(questId); //1141_D_Master_Stigma.
		qe.registerQuestNpc(834922).addOnTalkEvent(questId); //1131_D_Master_Stigma.
		qe.registerQuestNpc(834923).addOnTalkEvent(questId); //1132_D_Master_Stigma.
		qe.registerQuestItem(182216125, questId); //�?�?�모니움 보급품 안내서3.
		qe.registerOnEnterZone(ZoneName.get("VIFROST_BRIDGE_120010000"), questId);
		qe.registerOnEnterZone(ZoneName.get("PRIMUM_LANDING_400010000"), questId);
	}
	
	@Override
	public HandlerResult onItemUseEvent(QuestEnv env, Item item) {
		final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			if (qs.getQuestVarById(0) == 0) {
				qs.setQuestVar(1);
				changeQuestStep(env, 1, 1, true);
				return HandlerResult.SUCCESS;
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
			if (zoneName == ZoneName.get("VIFROST_BRIDGE_120010000")) {
				if (var == 0) {
					SystemMailService.getInstance().sendMail("Aud", player.getName(), "[Stigma Bundle]",
					"I'am Aud a stigma's master and I expect you at Pandaemonium. Come join me.", 182216125, 1, 0, 0, LetterType.NORMAL); //�?�?�모니움 보급품 안내서3.
					return true;
				}
			} else if (zoneName == ZoneName.get("PRIMUM_LANDING_400010000")) {
				if (var == 0) {
					SystemMailService.getInstance().sendMail("Master Stigma", player.getName(), "[Stigma Bundle]",
					"I'am a stigma's master and I expect you in a Fortress. Come join me.", 182216125, 1, 0, 0, LetterType.NORMAL); //�?�?�모니움 보급품 안내서3.
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = env.getTargetId();
		QuestDialog dialog = env.getDialog();
		if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 204061 || //Aud.
			    targetId == 834921 || //1141_L_Master_Stigma.
				targetId == 834922 || //1131_L_Master_Stigma.
				targetId == 834923) { //1132_L_Master_Stigma.
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
}
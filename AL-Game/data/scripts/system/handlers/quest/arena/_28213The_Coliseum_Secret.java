/*
 * This file is part Of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free sOftware: you can redistribute it and/or modify
 *  it under the terms Of the GNU Lesser Public License as published by
 *  the Free SOftware Foundation, either version 3 Of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty Of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy Of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.arena;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _28213The_Coliseum_Secret extends QuestHandler
{
	private final static int questId = 28213;

	public _28213The_Coliseum_Secret() {
		super(questId);
	}
	
	@Override
    public void register() {
        qe.registerQuestNpc(205986).addOnQuestStart(questId);
        qe.registerQuestNpc(205320).addOnTalkEvent(questId);
        qe.registerQuestNpc(798604).addOnTalkEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null) {
            if (targetId == 205986) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 1011);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            switch (targetId) {
                case 205320:
                    if (var == 0) {
                        switch (env.getDialog()) {
                            case START_DIALOG:
                                return sendQuestDialog(env, 1352);
                            case STEP_TO_1:
                                return defaultCloseDialog(env, 0, 1);
                        }
                    }
                    break;
                case 798804:
                    if (var == 1) {
                        switch (env.getDialog()) {
                            case START_DIALOG:
                                return sendQuestDialog(env, 1693);
                            case STEP_TO_2:
                                return defaultCloseDialog(env, 1, 3);
                        }
                    } else if (var == 3) {
                        switch (env.getDialog()) {
                            case START_DIALOG:
                                return sendQuestDialog(env, 2375);
                            case SELECT_REWARD:
                                return defaultCloseDialog(env, 3, 3, true, false);
                        }
                    }
                    break;
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 798804) {
                if (env.getDialog() == QuestDialog.USE_OBJECT) {
                    return sendQuestDialog(env, 5);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
        return false;
    }
}
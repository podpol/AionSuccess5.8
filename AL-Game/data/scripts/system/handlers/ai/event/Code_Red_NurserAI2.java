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
package ai.event;

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.joda.time.DateTime;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("coderednurse")
public class Code_Red_NurserAI2 extends GeneralNpcAI2
{
  	@Override
	protected void handleDialogStart(Player player) {
        switch (getNpcId()) {
            case 831435: //Jorpine.
            case 831436: //Yennu.
            case 831437: //Dalloren.
            case 831518: //Dalliea.
            case 831441: //Hylian.
            case 831442: //Rordah.
            case 831443: //Mazka.
            case 831524: { //Desha.
				super.handleDialogStart(player);
				break;
			} default: {
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
				break;
			}
		}
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		QuestEnv env = new QuestEnv(getOwner(), player, questId, dialogId);
		env.setExtendedRewardIndex(extendedRewardIndex);
		PlayerEffectController effectController = player.getEffectController();
		if (QuestEngine.getInstance().onDialog(env) && dialogId != 1011) {
			return true;
		} if (dialogId == 10000) {
			int skillId = 0;
			switch (getNpcId()) {
				case 831435: //Jorpine.
				case 831441: //Hylian.
					skillId = 21280;
					effectController.removeEffect(21281);
				break;
				case 831436: //Yennu.
				case 831442: //Rordah.
					skillId = 21281;
					effectController.removeEffect(21280);
				break;
				case 831437: //Dalloren.
				case 831524: //Desha.
					skillId = 21309;
					effectController.removeEffect(21283);
				break;
				case 831518: //Dalliea.
				case 831443: //Mazka.
					skillId = 21283;
					effectController.removeEffect(21309);
				break;
			}
			SkillEngine.getInstance().getSkill(getOwner(), skillId, 1, player).useNoAnimationSkill();
		} else if (dialogId == 1011 && questId != 0) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), dialogId, questId));
		}
		return true;
	}
	
	@Override
	protected void handleSpawned() {
		DateTime now = DateTime.now();
		int currentDay = now.getDayOfWeek();
		switch (getNpcId()) {
			case 831435: //Jorpine.
			case 831436: //Yennu.
			case 831441: //Hylian.
			case 831442: //Rordah.
				if (currentDay >= 1 && currentDay <= 4) {
					super.handleSpawned();
				} else {
					if (!isAlreadyDead()) {
						getOwner().getController().onDelete();
					}
				}
			break;
			case 831437: //Dalloren.
			case 831518: //Dalliea.
			case 831443: //Mazka.
			case 831524: //Deshna.
				if (currentDay >= 5 && currentDay <= 7) {
					super.handleSpawned();
				} else {
					if (!isAlreadyDead()) {
						getOwner().getController().onDelete();
					}
				}
			break;
		}
	}
}
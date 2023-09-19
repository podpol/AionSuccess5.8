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
package ai.npcSupport;

import ai.GeneralNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("npc_support")
public class Npc_SupportAI2 extends GeneralNpcAI2
{
    @Override
	protected void handleDialogStart(Player player) {
        switch (getNpcId()) {
            //Elyos.
			case 831024: //Ryoenniya.
			case 831025: //Luella.
			case 831030: //Netalion.
			case 831031: //Nebrith.
            //Asmodians.
            case 831026: //Rikanellie.
			case 831027: //Karzanke.
			case 831028: //Erdat.
			case 831029: { //Edandos.
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
			    case 831024: //Ryoenniya.
			    case 831025: //Luella.
			    case 831030: //Netalion.
				case 831031: //Nebrith.
				case 831026: //Rikanellie.
			    case 831027: //Karzanke.
			    case 831028: //Erdat.
				case 831029: //Edandos.
				player.getLifeStats().setCurrentHpPercent(100);
				player.getLifeStats().setCurrentMpPercent(100);
				player.getLifeStats().updateCurrentStats();
						switch (Rnd.get(1, 2)) {
						case 1:
							skillId = 20950; //Blessing Of Growth.
							effectController.removeEffect(20951);
						break;
						case 2:
							skillId = 20951; //Blessing Of Guardianship.
							effectController.removeEffect(20950);
						break;
					}
				break;
			}
			SkillEngine.getInstance().getSkill(getOwner(), skillId, 1, player).useNoAnimationSkill();
		} else if (dialogId == 1011 && questId != 0) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), dialogId, questId));
		}
        return true;
    }
}
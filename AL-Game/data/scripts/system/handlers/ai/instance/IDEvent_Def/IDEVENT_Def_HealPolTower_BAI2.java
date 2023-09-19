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
package ai.instance.IDEvent_Def;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEVENT_Def_HealPolTower_B")
public class IDEVENT_Def_HealPolTower_BAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		PlayerEffectController effectController = player.getEffectController();
		//강력한 �?명�?� �?�바 I
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(186000470, 2)) {
			if (player.getCommonData().getRace() == Race.ELYOS) {
				effectController.removeEffect(4936);
			    effectController.removeEffect(4937);
			    effectController.removeEffect(4938);
			    effectController.removeEffect(4939);
				SkillEngine.getInstance().applyEffectDirectly(4935, player, player, 1200000 * 1);
			} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
				effectController.removeEffect(4941);
			    effectController.removeEffect(4942);
			    effectController.removeEffect(4943);
			    effectController.removeEffect(4944);
				SkillEngine.getInstance().applyEffectDirectly(4940, player, player, 1200000 * 1);
			}
		}
		//강력한 �?명�?� �?�바 II
		else if (dialogId == 10001 && player.getInventory().decreaseByItemId(186000470, 7)) {
			if (player.getCommonData().getRace() == Race.ELYOS) {
				effectController.removeEffect(4935);
			    effectController.removeEffect(4937);
			    effectController.removeEffect(4938);
			    effectController.removeEffect(4939);
				SkillEngine.getInstance().applyEffectDirectly(4936, player, player, 1200000 * 1);
			} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
				effectController.removeEffect(4940);
			    effectController.removeEffect(4942);
			    effectController.removeEffect(4943);
			    effectController.removeEffect(4944);
				SkillEngine.getInstance().applyEffectDirectly(4941, player, player, 1200000 * 1);
			}
		}
		//강력한 �?명�?� �?�바 III
		else if (dialogId == 10002 && player.getInventory().decreaseByItemId(186000470, 13)) {
			if (player.getCommonData().getRace() == Race.ELYOS) {
				effectController.removeEffect(4935);
			    effectController.removeEffect(4936);
			    effectController.removeEffect(4938);
			    effectController.removeEffect(4939);
				SkillEngine.getInstance().applyEffectDirectly(4937, player, player, 1200000 * 1);
			} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
				effectController.removeEffect(4940);
			    effectController.removeEffect(4941);
			    effectController.removeEffect(4943);
			    effectController.removeEffect(4944);
				SkillEngine.getInstance().applyEffectDirectly(4942, player, player, 1200000 * 1);
			}
		}
		//강력한 �?명�?� �?�바 IV
		else if (dialogId == 10003 && player.getInventory().decreaseByItemId(186000470, 20)) {
			if (player.getCommonData().getRace() == Race.ELYOS) {
				effectController.removeEffect(4935);
			    effectController.removeEffect(4936);
			    effectController.removeEffect(4937);
			    effectController.removeEffect(4939);
				SkillEngine.getInstance().applyEffectDirectly(4938, player, player, 1200000 * 1);
			} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
				effectController.removeEffect(4940);
			    effectController.removeEffect(4941);
			    effectController.removeEffect(4942);
			    effectController.removeEffect(4944);
				SkillEngine.getInstance().applyEffectDirectly(4943, player, player, 1200000 * 1);
			}
		}
		//강력한 �?명�?� �?�바 V
		else if (dialogId == 10004 && player.getInventory().decreaseByItemId(186000470, 30)) {
			if (player.getCommonData().getRace() == Race.ELYOS) {
				effectController.removeEffect(4935);
			    effectController.removeEffect(4936);
			    effectController.removeEffect(4937);
			    effectController.removeEffect(4938);
				SkillEngine.getInstance().applyEffectDirectly(4939, player, player, 1200000 * 1);
			} else if (player.getCommonData().getRace() == Race.ASMODIANS) {
				effectController.removeEffect(4940);
			    effectController.removeEffect(4941);
			    effectController.removeEffect(4942);
			    effectController.removeEffect(4943);
				SkillEngine.getInstance().applyEffectDirectly(4944, player, player, 1200000 * 1);
			}
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
}
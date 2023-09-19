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
package ai.event.daevaDay;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.model.ChatType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MESSAGE;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("daeva_day_cake")
public class Daeva_Day_CakeAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(186000188) != null) { //[Event] Aether Flame.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.broadcastPacket(player, new SM_MESSAGE(player,
			"You must have 1 <Aether Flame>", ChatType.BRIGHT_YELLOW_CENTER), true);
        }
    }
	
	@Override
    public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		PlayerEffectController effectController = player.getEffectController();
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(186000188, 1)) { //[Event] Aether Flame.
			switch (getNpcId()) {
			    case 832180: //Daeva's Day Cake E.
				case 832181: //Daeva's Day Cake A.
				    switch (Rnd.get(1, 3)) {
						case 1:
							SkillEngine.getInstance().applyEffectDirectly(20884, player, player, 14400000 * 1);
							effectController.removeEffect(20885);
							effectController.removeEffect(20886);
						break;
						case 2:
							SkillEngine.getInstance().applyEffectDirectly(20885, player, player, 14400000 * 1);
							effectController.removeEffect(20884);
							effectController.removeEffect(20886);
						break;
						case 3:
							SkillEngine.getInstance().applyEffectDirectly(20886, player, player, 14400000 * 1);
							effectController.removeEffect(20884);
							effectController.removeEffect(20885);
						break;
					}
				break;
			}
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
        return true;
    }
}
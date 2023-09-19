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
package ai.instance.theEternalBastion;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("defense_cannon")
public class Defense_CannonAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(185000136) != null) { //Aetheric Power Crystal.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDLDF5B_TD_DEFWeapon);
        }
    }
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(185000136, 1)) { //Aetheric Power Crystal.
		    switch (getNpcId()) {
				//Defense Cannon Elyos.
				case 701596:
				case 701597:
				case 701598:
				case 701599:
			    case 701600:
			    case 701601:
			    case 701602:
			    case 701603:
			    case 701604:
			    case 701605:
			    case 701606:
			    case 701607:
				    SkillEngine.getInstance().getSkill(player, 21065, 1, player).useNoAnimationSkill();
				break;
				//Defense Cannon Asmodians.
				case 701610:
				case 701611:
				case 701612:
				case 701613:
				case 701614:
				case 701615:
				case 701616:
				case 701617:
				case 701618:
				case 701619:
				case 701620:
				case 701621:
				    SkillEngine.getInstance().getSkill(player, 21066, 1, player).useNoAnimationSkill();
				break;
			}
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		return true;
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
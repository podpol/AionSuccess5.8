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

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEVENT_Def_SWPrototype_01a")
public class IDEVENT_Def_SWPrototype_01aAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(186000470, 1)) {
			spawn(836025, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0);
		} else if (dialogId == 10001 && player.getInventory().decreaseByItemId(186000470, 2)) {
			spawn(836030, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0);
		} else if (dialogId == 10002 && player.getInventory().decreaseByItemId(186000470, 3)) {
			spawn(836035, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0);
		} else if (dialogId == 10003 && player.getInventory().decreaseByItemId(186000470, 3)) {
			spawn(836036, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0);
		} else if (dialogId == 10004 && player.getInventory().decreaseByItemId(186000470, 5)) {
			spawn(836045, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0);
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		return true;
	}
}
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
package ai.instance.empyreanCrucible;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.instance.handlers.InstanceHandler;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("recordkeeperreward")
public class RecordKeeperRewardAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
    }
	
	@Override
    public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
        InstanceHandler instanceHandler = getPosition().getWorldMapInstance().getInstanceHandler();
        PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
		if (dialogId == 10000) {
            switch (getNpcId()) {
                case 205344: //Record Keeper Reward.
                    getPosition().getWorldMapInstance().getInstanceHandler().doReward(player);
                break;
            }
		}
        return true;
    }
	
	@Override
    protected void handleSpawned() {
		switch (getNpcId()) {
			case 205344:
				//Wonderful, Daeva! You completed entire Crucible! Very impressive, nyerk!
				sendMsg(1111469, getObjectId(), false, 2000);
			break;
		}
		super.handleSpawned();
    }
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}
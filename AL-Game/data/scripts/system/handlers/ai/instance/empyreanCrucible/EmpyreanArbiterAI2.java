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
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.instancereward.InstanceReward;
import com.aionemu.gameserver.model.instance.playerreward.CruciblePlayerReward;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("empyreanarbiter")
public class EmpyreanArbiterAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(186000124) != null) { //Worthiness Ticket.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1097));
        }
    }
	
	@Override
	public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(186000124, 1)) {
			switch (getNpcId()) {
				case 799573:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 358.2547f, 349.26443f, 96.09108f, (byte) 59);
				break;
				case 205426:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1260.15f, 812.34f, 358.6056f, (byte) 90);
				break;
				case 205427:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1616.0248f, 154.43837f, 126f, (byte) 10);
				break;
				case 205428:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1793.9233f, 796.92f, 469.36542f, (byte) 60);
				break;
				case 205429:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1776.4169f, 1749.9952f, 303.69553f, (byte) 0);
				break;
				case 205430:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1328.935f, 1742.0771f, 316.74188f, (byte) 0);
				break;
				case 205431:
				    announceReentered();
					TeleportService2.teleportTo(player, 300300000, instanceId, 1760.9441f, 1278.033f, 394.23764f, (byte) 0);
				break;
			}
			InstanceReward<?> instance = getPosition().getWorldMapInstance().getInstanceHandler().getInstanceReward();
			if (instance != null) {
				CruciblePlayerReward reward = (CruciblePlayerReward) instance.getPlayerReward(player.getObjectId());
				if (reward != null) {
					reward.setPlayerDefeated(false);
				}
			}
		}
		return true;
	}
	
	private void announceReentered() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//"Player Name" has reentered the Illusion Stadium.
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400964, player.getName()));
				}
			}
		});
	}
}
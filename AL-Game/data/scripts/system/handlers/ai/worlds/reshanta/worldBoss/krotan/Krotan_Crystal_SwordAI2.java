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
package ai.worlds.reshanta.worldBoss.krotan;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("krotan_crystal_sword")
public class Krotan_Crystal_SwordAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(185000245) != null) { //Spirit Of Krotan's Pendant.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
			//Spirit of Krotan's Pendant is required to break this seal.
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_Named_Spawn_Fail01);
        }
    }
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(185000245, 1)) { //Spirit Of Krotan's Pendant.
			switch (getNpcId()) {
				case 702840: //Krotan's Crystal Sword [Elyos]
				    announceSpiritOfKrotan30Min();
					announceSpiritOfKrotanAppears();
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							spawn(883323, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Spirit Of Krotan.
						}
					}, 1800000); //30 Minutes.
				break;
				case 702841: //Krotan's Crystal Sword [Asmodians]
				    announceSpiritOfKrotan30Min();
					announceSpiritOfKrotanAppears();
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							spawn(884027, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Spirit Of Krotan.
						}
					}, 1800000); //30 Minutes.
				break;
			}
		}
		//Spirit Of Krotan's Pendant has broken the seal.
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_Crotan_Named_Spawn_Item);
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
		return true;
	}
	
	private void announceSpiritOfKrotanAppears() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Krotan Protector Spirit will appear after 5 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_BossNamed_SpawnAlarm_1221_05, 1500000);
				//The Krotan Protector Spirit will appear after 3 minutes.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_BossNamed_SpawnAlarm_1221_03, 1620000);
				//The Krotan Protector Spirit will appear after 1 minute.
				PacketSendUtility.playerSendPacketTime(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_BossNamed_SpawnAlarm_1221_01, 1740000);
			}
		});
	}
	
	private void announceSpiritOfKrotan30Min() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Spirit of Krotan will be summoned from Krotan's Crystal Sword in 30 minutes.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Ab1_Crotan_Named_Spawn_System);
			}
		});
	}
}
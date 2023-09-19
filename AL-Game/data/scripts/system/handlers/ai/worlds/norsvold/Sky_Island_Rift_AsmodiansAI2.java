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
package ai.worlds.norsvold;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("sky_island_rift_A")
public class Sky_Island_Rift_AsmodiansAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 805862: //Sky Island Rift.
			case 805863: //Sky Island Rift.
			case 805864: //Sky Island Rift.
			case 805865: //Sky Island Rift.
			case 805866: //Sky Island Rift.
			case 805867: //Sky Island Rift.
			case 805868: //Sky Island Rift.
			case 805869: //Sky Island Rift.
			case 805870: //Sky Island Rift.
			case 805871: //Sky Island Rift.
				startLifeTask();
			break;
        }
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Sky_Island_Rift_AsmodiansAI2.this);
			}
		}, 3600000); //1Hrs.
	}
	
	@Override
    protected void handleDialogStart(Player player) {
        if (player.isArchDaeva()) {
		    //This is a Sky Island Teleport Stone, to which Aether energy is gathered.
			//It feels like a powerful beam of Aether energy is shooting out towards the sky.
			//You may be able to reach the island in the sky by using the power infused in the fragment.
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
		}
    }
	
    @Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		//Use the teleport stone to teleport to the Sky Island.
		if (dialogId == 10000) {
            switch (getNpcId()) {
                //ASMODIANS
				case 805862: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 1971.852f, 1133.1721f, 876.35187f, (byte) 85);
                break;
				case 805863: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 2540.828f, 238.66917f, 884.73773f, (byte) 19);
                break;
				case 805864: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 2432.7473f, 2032.2451f, 845.83813f, (byte) 107);
                break;
				case 805865: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 2230.0217f, 2939.4053f, 930.6146f, (byte) 34);
                break;
				case 805866: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 1574.717f, 2993.7537f, 786.12787f, (byte) 110);
                break;
				case 805867: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 803.5246f, 2422.3174f, 806.5194f, (byte) 103);
                break;
				case 805868: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 218.0479f, 2862.6848f, 847.5248f, (byte) 2);
                break;
				case 805869: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 398.20267f, 1621.8885f, 920.9332f, (byte) 114);
                break;
				case 805870: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 558.4859f, 557.29987f, 921.48206f, (byte) 50);
                break;
				case 805871: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 220110000, 1678.5807f, 613.7282f, 955.04645f, (byte) 14);
                break;
            }
        }
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
        return true;
    }
}
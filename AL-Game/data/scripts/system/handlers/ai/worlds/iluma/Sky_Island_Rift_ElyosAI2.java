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
package ai.worlds.iluma;

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

@AIName("sky_island_rift_E")
public class Sky_Island_Rift_ElyosAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 805897: //Sky Island Rift.
			case 805898: //Sky Island Rift.
			case 805899: //Sky Island Rift.
			case 805900: //Sky Island Rift.
			case 805901: //Sky Island Rift.
			case 805902: //Sky Island Rift.
			case 805903: //Sky Island Rift.
			case 805904: //Sky Island Rift.
			case 805905: //Sky Island Rift.
			case 805906: //Sky Island Rift.
				startLifeTask();
			break;
        }
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Sky_Island_Rift_ElyosAI2.this);
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
				//ELYOS
				case 805897: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 2317.9f, 2258.29f, 1116.813f, (byte) 108);
                break;
                case 805898: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 2642.66f, 2747.09f, 1117.5881f, (byte) 6);
                break;
				case 805899: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 2581.79f, 1429.55f, 1116.9115f, (byte) 35);
                break;
                case 805900: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 2571.52f, 499.2f, 1116.8452f, (byte) 16);
                break;
				case 805901: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 1539.2189f, 311.21225f, 1067.249f, (byte) 8);
                break;
				case 805902: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 715.2502f, 966.5452f, 1117.2305f, (byte) 67);
                break;
				case 805903: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 305.39f, 435.61f, 1117.0474f, (byte) 4);
                break;
				case 805904: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 500.43f, 1683.58f, 1117.03f, (byte) 1);
                break;
				case 805905: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 677.18f, 2593.64f, 1116.9187f, (byte) 64);
                break;
				case 805906: //Sky Island Rift.
                    TeleportService2.teleportTo(player, 210100000, 1651.76f, 2645.18f, 1116.8591f, (byte) 117);
                break;
            }
        }
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
        return true;
    }
}
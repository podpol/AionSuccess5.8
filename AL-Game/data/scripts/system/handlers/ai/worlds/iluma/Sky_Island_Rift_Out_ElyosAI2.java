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
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.controllers.observer.ItemUseObserver;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_USE_OBJECT;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("sky_island_rift_out_E")
public class Sky_Island_Rift_Out_ElyosAI2 extends NpcAI2
{
	protected int startBarAnimation = 1;
	protected int cancelBarAnimation = 2;
	
	@Override
	protected void handleDialogStart(Player player) {
		handleUseItemStart(player);
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 805907: //Valley Of The Lost Scout Post.
			case 805908: //Coast Of The Light-Deprived Scout Post.
			case 805909: //Black Wind Valley Scout Post.
			case 805910: //Forest Of Dormant Life Scout Post.
			case 805911: //Ancient Temple Of Life Scout Post.
			case 805912: //Krall Aether Mine Scout Post.
			case 805913: //Polten Marsh.
			case 805914: //Ariel's Rest.
			case 805915: //Zephyr Vale.
			case 805916: //Kojol Valley.
				startLifeTask();
			break;
        }
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Sky_Island_Rift_Out_ElyosAI2.this);
			}
		}, 3600000); //1Hrs.
	}
	
	protected void handleUseItemStart(final Player player) {
		final int delay = getTalkDelay();
		if (delay != 0) {
			final ItemUseObserver observer = new ItemUseObserver() {
				@Override
				public void abort() {
					player.getController().cancelTask(TaskId.ACTION_ITEM_NPC);
					PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.END_QUESTLOOT, 0, getObjectId()), true);
					PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), getObjectId(), 0, cancelBarAnimation));
					player.getObserveController().removeObserver(this);
				}
			};
			player.getObserveController().attach(observer);
			PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), getObjectId(), getTalkDelay(), startBarAnimation));
			PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_QUESTLOOT, 0, getObjectId()), true);
			player.getController().addTask(TaskId.ACTION_ITEM_NPC, ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.END_QUESTLOOT, 0, getObjectId()), true);
					PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), getObjectId(), getTalkDelay(), cancelBarAnimation));
					player.getObserveController().removeObserver(observer);
					handleUseItemFinish(player);
				}
			}, delay));
		} else {
			handleUseItemFinish(player);
		}
	}
	
    protected void handleUseItemFinish(Player player) {
        switch (getNpcId()) {
			case 805907: //Valley Of The Lost Scout Post.
                TeleportService2.teleportTo(player, 210100000, 2065.393f, 2626.8037f, 268.625f, (byte) 7);
            break;
            case 805908: //Coast Of The Light-Deprived Scout Post.
                TeleportService2.teleportTo(player, 210100000, 2655.64f, 2357.6018f, 223.125f, (byte) 31);
            break;
			case 805909: //Black Wind Valley Scout Post.
                TeleportService2.teleportTo(player, 210100000, 2142.0142f, 333.43063f, 302.75f, (byte) 100);
            break;
            case 805910: //Forest Of Dormant Life Scout Post.
                TeleportService2.teleportTo(player, 210100000, 944.2284f, 715.76404f, 367.75f, (byte) 50);
            break;
			case 805911: //Ancient Temple Of Life Scout Post.
                TeleportService2.teleportTo(player, 210100000, 336.9908f, 978.84924f, 267.75f, (byte) 99);
            break;
			case 805912: //Krall Aether Mine Scout Post.
                TeleportService2.teleportTo(player, 210100000, 217.54932f, 2176.8188f, 298.65317f, (byte) 116);
            break;
			case 805913: //Polten Marsh.
                TeleportService2.teleportTo(player, 210100000, 2438.378f, 1282.583f, 227.75f, (byte) 31);
            break;
			case 805914: //Ariel's Rest.
                TeleportService2.teleportTo(player, 210100000, 1356.1259f, 381.74872f, 349.73428f, (byte) 93);
            break;
			case 805915: //Zephyr Vale.
                TeleportService2.teleportTo(player, 210100000, 842.8415f, 1850.0061f, 310.42758f, (byte) 100);
            break;
			case 805916: //Kojol Valley.
                TeleportService2.teleportTo(player, 210100000, 1651.76f, 2645.18f, 1116.8591f, (byte) 117);
            break;
        }
    }
	
	protected int getTalkDelay() {
		return getObjectTemplate().getTalkDelay() * 1000;
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
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
package ai;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.handler.FollowEventHandler;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("deliveryman")
public class DeliveryManAI2 extends FollowingNpcAI2
{
	private Player owner;
	public static int EVENT_SET_CREATOR = 1;
	private static int SERVICE_TIME = 10 * 60 * 1000;
	private static int SPAWN_ACTION_DELAY = 1000;
	
	@Override
	protected void handleSpawned() {
		ThreadPoolManager.getInstance().schedule(new DeleteDeliveryMan(), SERVICE_TIME);
		ThreadPoolManager.getInstance().schedule(new DeliveryManSpawnAction(), SPAWN_ACTION_DELAY);
		super.handleSpawned();
	}
	
	@Override
	protected void handleDespawned() {
		sendMsg(390267, getObjectId(), false, 0);
		super.handleDespawned();
	}
	
	@Override
	protected void handleDialogStart(Player player) {
		if (player.equals(owner)) {
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 18));
			player.getMailbox().sendMailList(true);
		}
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature == owner) {
			FollowEventHandler.creatureMoved(this, creature);
		}
	}
	
	@Override
	protected void handleCustomEvent(int eventId, Object... args) {
		if (eventId == EVENT_SET_CREATOR) {
			owner = (Player) args[0];
		}
	}
	
	private final class DeleteDeliveryMan implements Runnable {
		@Override
		public void run() {
			AI2Actions.deleteOwner(DeliveryManAI2.this);
		}
	}
	
	private final class DeliveryManSpawnAction implements Runnable {
		@Override
		public void run() {
		    sendMsg(390266, getObjectId(), false, 2000);
			sendMsg(390268, getObjectId(), false, 5000);
			handleFollowMe(owner);
			handleCreatureMoved(owner);
		}
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}
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
package ai.worlds.panesterra;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AI2Request;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.controllers.observer.ItemUseObserver;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Temple_Gate")
public class Temple_GateAI2 extends NpcAI2
{
	protected int startBarAnimation = 1;
	protected int cancelBarAnimation = 2;
	private final int CANCEL_DIALOG_METERS = 10;
	
	@Override
	protected void handleDialogStart(Player player) {
		handleUseItemStart(player);
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
		if (player.getLevel() >= 65) {
			//Do you want to pass through the castle gate ?
			AI2Actions.addRequest(this, player, SM_QUESTION_WINDOW.STR_ASK_PASS_BY_GATE, getOwner().getObjectId(), CANCEL_DIALOG_METERS, new AI2Request() {
				private boolean decisionTaken = false;
				@Override
				public void acceptRequest(Creature requester, Player responder) {
					if (!decisionTaken) {
			            decisionTaken = true;
						moveToAcrossTempleGate(responder);
					}
				}
				@Override
				public void denyRequest(Creature requester, Player responder) {
					decisionTaken = true;
				}
			});
		} else {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CANNOT_USE_DOOR_FAR_FROM_NPC);
		}
	}
	
	private void moveToAcrossTempleGate(Player responder) {
		int worldId = responder.getWorldId();
		double radian = Math.toRadians(MathUtil.convertHeadingToDegree(responder.getHeading()));
		float x = (float) (Math.cos(radian) * 18.0D); //Move Forward 18M.
		float y = (float) (Math.sin(radian) * 18.0D); //Move Forward 18M.
		responder.getEffectController().updatePlayerEffectIcons();
        PacketSendUtility.sendPacket(responder, new SM_TARGET_UPDATE(responder));
		PacketSendUtility.broadcastPacketAndReceive(responder, new SM_TRANSFORM(responder, true));
		PacketSendUtility.broadcastPacketAndReceive(responder, new SM_TRANSFORM(responder, responder.getTransformedModelId(), true, responder.getTransformedItemId()));
		TeleportService2.teleportTo(responder, worldId, responder.getX() + x, responder.getY() + y, responder.getZ(), (byte) 0);
	}
	
	protected int getTalkDelay() {
		return getObjectTemplate().getTalkDelay() * 1000;
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
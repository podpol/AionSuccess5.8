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
package ai.instance.rentusBase;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("sensory_area")
public class SensoryAreaAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	public void think() {
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			if (MathUtil.getDistance(getOwner(), player) <= 10) {
				if (startedEvent.compareAndSet(false, true)) {
					switch (player.getWorldId()) {
		                case 300280000: //Rentus Base
						    //Xasta flies past overhead.
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_Spawn_01, 9000);
							//Use the anti-aircraft gun to attack Xasta flying overhead.
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_Spawn_02, 10000);
							//Xasta falls from the sky, wounded!
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_SUCCEED_01, 120000);
							ThreadPoolManager.getInstance().schedule(new Runnable() {
							    @Override
								public void run() {
								    spawn(217309, 445.6442f, 439.13187f, 168.64172f, (byte) 40);
								}
							}, 10000);
							AI2Actions.deleteOwner(SensoryAreaAI2.this);
				        break;
					} switch (player.getWorldId()) {
		                case 300620000: //[Occupied] Rentus Base 4.8
						    //Xasta flies past overhead.
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_Spawn_01, 9000);
							//Use the anti-aircraft gun to attack Xasta flying overhead.
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_Spawn_02, 10000);
							//Xasta falls from the sky, wounded!
							PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDYun_Rasta_SUCCEED_01, 120000);
							ThreadPoolManager.getInstance().schedule(new Runnable() {
							    @Override
								public void run() {
								    spawn(236296, 445.6442f, 439.13187f, 168.64172f, (byte) 40);
								}
							}, 10000);
							AI2Actions.deleteOwner(SensoryAreaAI2.this);
				        break;
					}
				}
			}
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
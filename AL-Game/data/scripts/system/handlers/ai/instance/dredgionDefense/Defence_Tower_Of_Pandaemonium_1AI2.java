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
package ai.instance.dredgionDefense;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Defence_Tower_Of_Pandaemonium_1")
public class Defence_Tower_Of_Pandaemonium_1AI2 extends ActionItemNpcAI2
{
	private Future<?> dreadgionDrakanATKTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleDialogStart(Player player) {
		super.handleDialogStart(player);
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		//Pandaemonium Defense Turret Coolant.
		if (!player.getInventory().decreaseByItemId(185000285, 1)) {
			//You don’t have coolant.
            PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403692));
			return;
        }
		dreadgionDrakanATK();
		startDreadgionOverheatTask();
		//The defense turret is now cool enough to use!
		PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403951));
	}
	
	@Override
	protected void handleSpawned() {
		dreadgionDrakanATK();
		startDreadgionOverheatTask();
		super.handleSpawned();
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 220818: //Defence Tower Of Pandaemonium 1.
				    announceDefencePandaemoniumA();
				break;
			}
		}
	}
	
	private void dreadgionDrakanATK() {
		dreadgionDrakanATKTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.targetCreature(Defence_Tower_Of_Pandaemonium_1AI2.this, getPosition().getWorldMapInstance().getNpc(220966));
                AI2Actions.useSkill(Defence_Tower_Of_Pandaemonium_1AI2.this, 18311);
			}
		}, 4000, 10000);
	}
	
	private void startDreadgionOverheatTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				cancelATKTask();
				announceDreadgionOverheat();
				//AI2Actions.useSkill(Defence_Tower_Of_Pandaemonium_1AI2.this, 18310); //Dreadgion Overheat.
			}
		}, 300000);
	}
	
	private void announceDefencePandaemoniumA() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The defense turret is being attacked!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403709));
				}
			}
		});
	}
	private void announceDefencePandaemoniumDieA() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The defense turret has been destroyed.
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403710));
				}
			}
		});
	}
	private void announceDreadgionOverheat() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The defense turret is overheating. Find some coolant!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403947));
				}
			}
		});
	}
	
	@Override
	protected void handleDied() {
		announceDefencePandaemoniumDieA();
		super.handleDied();
	}
	
	private void cancelATKTask() {
		if (dreadgionDrakanATKTask != null && !dreadgionDrakanATKTask.isDone()) {
			dreadgionDrakanATKTask.cancel(true);
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
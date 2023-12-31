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

import ai.GeneralNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Sanctum_Tank_1")
public class Sanctum_Tank_1AI2 extends GeneralNpcAI2
{
    private boolean canThink = true;
	private String walkerId = "302200001";
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			if (MathUtil.getDistance(getOwner(), player) <= 5) {
				if (startedEvent.compareAndSet(false, true)) {
					canThink = false;
					getSpawnTemplate().setWalkerId("302200001");
					WalkManager.startWalking(this);
					getOwner().setState(1);
					PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							if (!isAlreadyDead()) {
								despawn();
								giveItem();
								ItemService.addItem(player, 185000286, 1); //Sanctum Defense Turret Energy Source.
								spawn(834307, 1366.033f, 1506.3276f, 569.1809f, (byte) 68); //Joarunerk.
								spawn(220822, 1363.2576f, 1511.7175f, 569.1623f, (byte) 90); //Dreadgion_Turret_Carrier_C_Li.
							}
						}
					}, 213000);
				}
			}
		}
	}
	
	@Override
	protected void handleMoveArrived() {
		int point = getOwner().getMoveController().getCurrentPoint();
		super.handleMoveArrived();
		if (getNpcId() == 220820) { //Sanctum Tank A.
			if (point == 3) {
				spawn(220810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(220814, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			} else if (point == 6) {
				spawn(220810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(220814, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			} else if (point == 10) {
				spawn(220810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(220814, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			} else if (point == 12) {
				spawn(220810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(220814, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			} else if (point == 15) {
				spawn(220810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(220814, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			}
		}
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 220820: //Sanctum Tank A.
				    announceSanctumTankA();
				break;
			}
		}
	}
	
	private void announceSanctumTankA() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The Ereshkigal Legion is attacking the chariot!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403978));
				}
			}
		});
	}
	private void announceSanctumTankADie() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//The transportation chariot was destroyed! You'll have to go back for another!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403975));
				}
			}
		});
	}
	private void giveItem() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Success! Use the energy core from the chariot to charge the defense turret!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1403976));
				}
			}
		});
	}
	
	@Override
	protected void handleDied() {
		announceSanctumTankADie();
		spawn(834305, 1380.8705f, 1689.9844f, 573.12286f, (byte) 0); //Korunerk.
		super.handleDied();
	}
	
	private void despawn() {
		AI2Actions.deleteOwner(this);
	}
}
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
package ai.instance.crucibleChallenge;

import ai.ActionItemNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("cruciblerift")
public class CrucibleRiftAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
			case 730459: //Crucible Rift.
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
			break;
			case 730460: //Crucible Rift.
				TeleportService2.teleportTo(player, 300320000, getPosition().getInstanceId(), 1759.5004f, 1273.5414f, 389.11743f, (byte) 10);
				spawn(205679, 1765.522f, 1282.1051f, 389.11743f, (byte) 0);
				AI2Actions.deleteOwner(this);
			break;
		}
	}
	
	@Override
	protected void handleSpawned() {
		switch (getNpcId()) {
			case 730459: //Crucible Rift.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						announceCrucibleRift1();
					}
				}, 2000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						announceCrucibleRift2();
					}
				}, 6000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						announceCrucibleRift3();
					}
				}, 10000);
			break;
		}
		super.handleSpawned();
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000 && getNpcId() == 730459) { //Crucible Rift.
			PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
			TeleportService2.teleportTo(player, 300320000, getPosition().getInstanceId(), 1807.0531f, 306.2831f, 469.25f, (byte) 54);
			switch (player.getRace()) {
			    case ELYOS:
			        spawn(218200, 1765.4385f, 315.67407f, 469.25f, (byte) 114); //Rank 5, Asmodian Soldier Mediatec.
			    break;
				case ASMODIANS:
			        spawn(218192, 1765.4385f, 315.67407f, 469.25f, (byte) 114); //Rank 5, Elyos Soldier Odos.
			    break;
			}
			AI2Actions.deleteOwner(this);
		}
		return true;
	}
	
	private void announceCrucibleRift1() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//A Crucible Rift has appeared at the spot where Vanktrist vanished. I'd better go investigate!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(false, 1111482, player.getObjectId(), 2));
				}
			}
		});
	}
	
	private void announceCrucibleRift2() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Hmm, just as I suspected... Tiamat's Balaur have infiltrated the Crucible.
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(false, 1111483, player.getObjectId(), 2));
				}
			}
		});
	}
	
	private void announceCrucibleRift3() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//Weird. It looks like a Crucible... just not OUR Crucible. I wonder who it belongs to?
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(false, 1111484, player.getObjectId(), 2));
				}
			}
		});
	}
}
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
package ai.rvr;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.HTMLService;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("rvr_officer")
public class Rvr_OfficerAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		startLifeTask();
	}
	
	@Override
	protected void handleDied() {
        switch (getNpcId()) {
			//Attacker Asmodians.
			case 857733: //Officer Tarkan.
				sendRvrGuide();
				announceGeneralMiltarRescued();
			break;
			case 857734: //Officer Shagad.
			    sendRvrGuide();
				announceGeneralKuparoRescued();
			break;
			case 857735: //Officer Argan.
			    sendRvrGuide();
				announceGeneralLanstriRescued();
			break;
			//Attacker Elyos.
			case 857740: //Officer Nars.
			    sendRvrGuide();
				announceGeneralMagkenRescued();
			break;
			case 857741: //Officer Fasig.
			    sendRvrGuide();
				announceGeneralHarkRescued();
			break;
			case 857742: //Officer Gadevir.
			    sendRvrGuide();
				announceGeneralTombolkRescued();
			break;
		}
		super.handleDied();
	}
	
	private void sendRvrGuide() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(player, getOwner(), 15)) {
					HTMLService.sendGuideHtml(player, "Rvr_Guide");
				}
			}
		});
	}
	
   /**
	* Attacker Asmodians.
	*/
	private void announceGeneralMiltarRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Raiders have successfully eliminated General Miltar.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_LF3_BOSS_HEAL_NOTICE_01);
			}
		});
	}
	private void announceGeneralKuparoRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Raiders have successfully eliminated General Kupiaro.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_LF3_BOSS_HEAL_NOTICE_02);
			}
		});
	}
	private void announceGeneralLanstriRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Raiders have successfully eliminated General Lanstri.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_LF3_BOSS_HEAL_NOTICE_03);
			}
		});
	}
	
   /**
	* Attacker Elyos.
	*/
	private void announceGeneralMagkenRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Protectors have successfully rescued General Magken.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_DF3_BOSS_HEAL_NOTICE_01);
			}
		});
	}
	private void announceGeneralHarkRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Protectors have successfully rescued General Hark.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_DF3_BOSS_HEAL_NOTICE_02);
			}
		});
	}
	private void announceGeneralTombolkRescued() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Asmodian Protectors have successfully rescued General Tombolk.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_RVR_DF3_BOSS_HEAL_NOTICE_03);
			}
		});
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Rvr_OfficerAI2.this);
			}
		}, 3540000);
	}
}
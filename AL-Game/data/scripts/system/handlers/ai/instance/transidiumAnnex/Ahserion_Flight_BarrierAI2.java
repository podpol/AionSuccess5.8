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
package ai.instance.transidiumAnnex;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.NpcType;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("ahserion_flight_barrier")
public class Ahserion_Flight_BarrierAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		startShieldVulnerable();
	}
	
   /**
	* Indestructible barrier around "Ahserion" is removed
	* Once the barriers are off you can start attacking "Ahserion".
	* You can say that after 25min.
	*/
	private void startShieldVulnerable() {
		final Npc GAb1SubCenterBarricadeDa65Ah = getPosition().getWorldMapInstance().getNpc(277230); //Ahserion Flight Barrier.
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				GAb1SubCenterBarricadeDa65Ah.setTarget(getOwner());
				GAb1SubCenterBarricadeDa65Ah.setNpcType(NpcType.ATTACKABLE);
				WorldMapInstance instance = getPosition().getWorldMapInstance();
				for (Player player: instance.getPlayersInside()) {
					if (MathUtil.isIn3dRange(player, GAb1SubCenterBarricadeDa65Ah, 20)) {
						player.getEffectController().updatePlayerEffectIcons();
						player.clearKnownlist();
						player.updateKnownlist();
					}
				}
				//The effect of the Transidium Annex has weakened the Ahserion's Flight Barrier.
				PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_GAB1_SUB_ALARM_11, 0);
			}
		}, 1500000); //...25 Minutes.
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
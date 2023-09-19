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
package ai.siege;

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.siege.SiegeNpc;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ABYSS_ARTIFACT_INFO3;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FORTRESS_INFO;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("siege_teleporter")
public class Siege_TeleporterAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleDespawned() {
		siegeTeleport(false);
		artifactTeleport(false);
		super.handleDespawned();
	}
	
	@Override
	protected void handleSpawned() {
		siegeTeleport(true);
		artifactTeleport(true);
		super.handleSpawned();
	}
	
	private void siegeTeleport(final boolean status) {
		final int id = ((SiegeNpc) getOwner()).getSiegeId();
		SiegeService.getInstance().getFortress(id).setCanTeleport(status);
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, new SM_FORTRESS_INFO(id, status));
			}
		});
	}
	
	private void artifactTeleport(final boolean status) {
        final int id = ((SiegeNpc) getOwner()).getSiegeId();
        SiegeService.getInstance().getArtifact(id).setCanTeleport(status);
        getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
            @Override
            public void visit(Player player) {
                PacketSendUtility.sendPacket(player, new SM_ABYSS_ARTIFACT_INFO3(id, status));
            }
        });
    }
}
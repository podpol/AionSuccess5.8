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

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RequestResponseHandler;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUESTION_WINDOW;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("teleport_district")
public class District_Group_TeleportAI2 extends NpcAI2
{
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        checkDistance(this, creature);
    }
	
	private void checkDistance(NpcAI2 ai, Creature creature) {
        if (creature instanceof Player && !creature.getLifeStats().isAlreadyDead()) {
        	final Player player = (Player) creature;
			if (MathUtil.isIn3dRange(getOwner(), creature, 5)) {
        		sendDistrictGroupRequest(player);
        	}
        }
    }
	
	public void sendDistrictGroupRequest(final Player player) {
        String message = "Accept use District Group Teleport ?";
        RequestResponseHandler responseHandler = new RequestResponseHandler(player) {
            @Override
            public void acceptRequest(Creature requester, Player responder) {
				switch (getNpcId()) {
					case 833268:
						switch (responder.getWorldId()) {
						    case 400020000: //Belus.
							    TeleportService2.teleportTo(responder, 400020000, 240.24854f, 1610.5688f, 1463.8889f, (byte) 86); //[Sylvan District]
							break;
							case 400040000: //Aspida.
							    TeleportService2.teleportTo(responder, 400040000, 240.24854f, 1610.5688f, 1463.8889f, (byte) 86); //[Nebulum District]
							break;
							case 400050000: //Atanatos.
							    TeleportService2.teleportTo(responder, 400050000, 240.24854f, 1610.5688f, 1463.8889f, (byte) 86); //[Bronzium District]
							break;
							case 400060000: //Disillon.
							    TeleportService2.teleportTo(responder, 400060000, 240.24854f, 1610.5688f, 1463.8889f, (byte) 86); //[Divinatum District]
							break;
						}
					break;
					case 833269:
						switch (responder.getWorldId()) {
						    case 400020000: //Belus.
							    TeleportService2.teleportTo(responder, 400020000, 1609.0776f, 1810.1611f, 1463.9395f, (byte) 112); //[Heirloom District]
							break;
							case 400040000: //Aspida.
							    TeleportService2.teleportTo(responder, 400040000, 1609.0776f, 1810.1611f, 1463.9395f, (byte) 112); //[Blaekmor District]
							break;
							case 400050000: //Atanatos.
							    TeleportService2.teleportTo(responder, 400050000, 1609.0776f, 1810.1611f, 1463.9395f, (byte) 112); //[Aureus District]
							break;
							case 400060000: //Disillon.
							    TeleportService2.teleportTo(responder, 400060000, 1609.0776f, 1810.1611f, 1463.9395f, (byte) 112); //[Fulminaer District]
							break;
						}
					break;
					case 833270:
						switch (responder.getWorldId()) {
							case 400020000: //Belus.
							    TeleportService2.teleportTo(responder, 400020000, 1812.733f, 431.72452f, 1463.8632f, (byte) 85); //[Vernalium District]
							break;
							case 400040000: //Aspida.
							    TeleportService2.teleportTo(responder, 400040000, 1812.733f, 431.72452f, 1463.8632f, (byte) 85); //[Myrkin District]
							break;
							case 400050000: //Atanatos.
							    TeleportService2.teleportTo(responder, 400050000, 1812.733f, 431.72452f, 1463.8632f, (byte) 85); //[Cyprian District]
							break;
							case 400060000: //Disillon.
							    TeleportService2.teleportTo(responder, 400060000, 1812.733f, 431.72452f, 1463.8632f, (byte) 85); //[Thonderen District]
							break;
						}
					break;
					case 833271:
						switch (responder.getWorldId()) {
						    case 400020000: //Belus.
							    TeleportService2.teleportTo(responder, 400020000, 438.7579f, 234.54915f, 1464.1918f, (byte) 58); //[Evergreen District]
							break;
							case 400040000: //Aspida.
							    TeleportService2.teleportTo(responder, 400040000, 438.7579f, 234.54915f, 1464.1918f, (byte) 58); //[Shaedwian District]
							break;
							case 400050000: //Atanatos.
							    TeleportService2.teleportTo(responder, 400050000, 438.7579f, 234.54915f, 1464.1918f, (byte) 58); //[Braesen District]
							break;
							case 400060000: //Disillon.
							    TeleportService2.teleportTo(responder, 400060000, 438.7579f, 234.54915f, 1464.1918f, (byte) 58); //[Severus District]
							break;
						}
					break;
				}
            }
            @Override
            public void denyRequest(Creature requester, Player responder) {
            }
        };
        boolean requested = player.getResponseRequester().putRequest(902247, responseHandler);
        if (requested) {
            PacketSendUtility.sendPacket(player, new SM_QUESTION_WINDOW(902247, 0, 0, message));
        }
    }
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
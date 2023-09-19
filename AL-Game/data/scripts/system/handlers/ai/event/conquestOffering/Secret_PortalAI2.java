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
package ai.event.conquestOffering;

import ai.ActionItemNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;
import com.aionemu.gameserver.world.World;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("secret_portal")
public class Secret_PortalAI2 extends ActionItemNpcAI2
{
	@Override
    protected void handleSpawned() {
        announceRotationBuff();
		super.handleSpawned();
    }
	
	private void announceRotationBuff() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//You feel a strange presence around you.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_BF4_Rotation_Buff_NPC_01);
			}
		});
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 833018: //Secret Portal.
				switch (player.getWorldId()) {
                    case 210130000: //Inggison.
						switch (Rnd.get(1, 13)) {
						    case 1:
							    TeleportService2.teleportTo(player, 210130000, 1814.2759f, 242.06152f, 521.8501f, (byte) 10);
							break;
							case 2:
							    TeleportService2.teleportTo(player, 210130000, 2331.7358f, 487.32224f, 431.90802f, (byte) 14);
							break;
							case 3:
							    TeleportService2.teleportTo(player, 210130000, 2070.4924f, 205.9982f, 490.71933f, (byte) 31);
							break;
							case 4:
							    TeleportService2.teleportTo(player, 210130000, 2605.1113f, 1318.8029f, 330.1698f, (byte) 74);
							break;
							case 5:
							    TeleportService2.teleportTo(player, 210130000, 2240.141f, 2092.4824f, 58.125f, (byte) 65);
							break;
							case 6:
							    TeleportService2.teleportTo(player, 210130000, 191.14435f, 474.06177f, 577.7558f, (byte) 15);
							break;
							case 7:
							    TeleportService2.teleportTo(player, 210130000, 762.1146f, 232.81108f, 541.2699f, (byte) 66);
							break;
							case 8:
							    TeleportService2.teleportTo(player, 210130000, 146.30334f, 136.93912f, 558.5093f, (byte) 98);
							break;
							case 9:
							    TeleportService2.teleportTo(player, 210130000, 321.7804f, 82.18708f, 499.76416f, (byte) 115);
							break;
							case 10:
							    TeleportService2.teleportTo(player, 210130000, 1429.834f, 1745.4474f, 162.54492f, (byte) 62);
							break;
							case 11:
							    TeleportService2.teleportTo(player, 210130000, 822.2454f, 1047.0043f, 213.04636f, (byte) 13);
							break;
							case 12:
							    TeleportService2.teleportTo(player, 210130000, 1602.6942f, 1583.8927f, 168.625f, (byte) 37);
							break;
							case 13:
							    TeleportService2.teleportTo(player, 210130000, 1738.6703f, 1154.055f, 393.07278f, (byte) 20);
							break;
						}
			        break;
				}
		    break;
			case 833021: //Secret Portal.
				switch (player.getWorldId()) {
                    case 220140000: //Gelkmaros.
						switch (Rnd.get(1, 13)) {
						    case 1:
							    TeleportService2.teleportTo(player, 220140000, 519.70667f, 1843.6685f, 362.52618f, (byte) 86);
							break;
							case 2:
							    TeleportService2.teleportTo(player, 220140000, 304.01727f, 1757.0371f, 353.4341f, (byte) 33);
							break;
							case 3:
							    TeleportService2.teleportTo(player, 220140000, 804.1618f, 1981.9644f, 326.0f, (byte) 90);
							break;
							case 4:
							    TeleportService2.teleportTo(player, 220140000, 907.40564f, 1383.379f, 51.78881f, (byte) 3);
							break;
							case 5:
							    TeleportService2.teleportTo(player, 220140000, 740.72034f, 1366.3945f, 277.81967f, (byte) 90);
							break;
							case 6:
							    TeleportService2.teleportTo(player, 220140000, 451.3702f, 1453.679f, 283.41513f, (byte) 101);
							break;
							case 7:
							    TeleportService2.teleportTo(player, 220140000, 1103.892f, 1272.3827f, 280.87134f, (byte) 108);
							break;
							case 8:
							    TeleportService2.teleportTo(player, 220140000, 571.23206f, 1541.0194f, 277.125f, (byte) 82);
							break;
							case 9:
							    TeleportService2.teleportTo(player, 220140000, 2351.7795f, 673.98047f, 142.015f, (byte) 115);
							break;
							case 10:
							    TeleportService2.teleportTo(player, 220140000, 2656.8284f, 666.3557f, 141.40376f, (byte) 62);
							break;
							case 11:
							    TeleportService2.teleportTo(player, 220140000, 2179.4883f, 1163.6583f, 206.09595f, (byte) 13);
							break;
							case 12:
							    TeleportService2.teleportTo(player, 220140000, 2118.3875f, 1099.9666f, 300.2773f, (byte) 37);
							break;
							case 13:
							    TeleportService2.teleportTo(player, 220140000, 391.5f, 1988.5851f, 2281.3125f, (byte) 20);
							break;
						}
			        break;
				}
		    break;
		}
	}
}
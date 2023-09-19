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
package zone.pvpZones;

import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.world.zone.ZoneName;
import com.aionemu.gameserver.world.zone.handler.ZoneNameAnnotation;

@ZoneNameAnnotation(value = "LC1_PVP_SUB_C DC1_PVP_ZONE")
public class PvPAreaZone extends PvPZone
{
	@Override
	protected void doTeleport(Player player, ZoneName zoneName) {
		if (zoneName == ZoneName.get("LC1_PVP_SUB_C")) {
			TeleportService2.teleportTo(player, 110010000, 1465.1226f, 1336.6649f, 566.41583f, (byte) 92);
		} else if (zoneName == ZoneName.get("DC1_PVP_ZONE")) {
			TeleportService2.teleportTo(player, 120010000, 1004.49927f, 1528.2157f, 222.19403f, (byte) 52);
		}
	}
}
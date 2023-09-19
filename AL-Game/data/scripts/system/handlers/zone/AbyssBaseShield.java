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
package zone;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.world.zone.ZoneInstance;
import com.aionemu.gameserver.world.zone.ZoneName;
import com.aionemu.gameserver.world.zone.handler.ZoneHandler;
import com.aionemu.gameserver.world.zone.handler.ZoneNameAnnotation;

@ZoneNameAnnotation("PRIMUM_FORTRESS TERMINON_LANDING")
public class AbyssBaseShield implements ZoneHandler
{
	@Override
	public void onEnterZone(Creature creature, ZoneInstance zone) {
		Creature actingCreature = creature.getActingCreature();
		if (actingCreature instanceof Player && !((Player) actingCreature).isGM()) {
			ZoneName currZone = zone.getZoneTemplate().getName();
			if (currZone == ZoneName.get("PRIMUM_FORTRESS")) {
				if (((Player) actingCreature).getRace() == Race.ELYOS) {
					creature.getController().die();
				}
			} else if (currZone == ZoneName.get("TERMINON_LANDING")) {
				if (((Player) actingCreature).getRace() == Race.ASMODIANS) {
					creature.getController().die();
				}
			}
		}
	}
	
	@Override
	public void onLeaveZone(Creature player, ZoneInstance zone) {
	}
}
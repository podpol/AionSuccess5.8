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
package com.aionemu.gameserver.model.siege;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.IActor;
import com.aionemu.gameserver.geoEngine.scene.Spatial;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.ShieldService;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.world.zone.ZoneInstance;
import com.aionemu.gameserver.world.zone.handler.ZoneHandler;
import javolution.util.FastMap;

/**
 * Shields have material ID 11 in geo.
 * 
 * @author Rolandas
 */
public class SiegeShield implements ZoneHandler {

	FastMap<Integer, IActor> observed = new FastMap<Integer, IActor>();
	private Spatial geometry;
	private int siegeLocationId;
	private boolean isEnabled = false;

	public SiegeShield(Spatial geometry) {
		this.geometry = geometry;
	}

	public Spatial getGeometry() {
		return geometry;
	}

	@Override
	public void onEnterZone(Creature creature, ZoneInstance zone) {
		if (!(creature instanceof Player))
			return;
		Player player = (Player) creature;
		if (isEnabled || siegeLocationId == 0) {
			FortressLocation loc = SiegeService.getInstance().getFortress(siegeLocationId);
			if (loc == null || loc.getRace() != SiegeRace.getByRace(player.getRace())) {
				ActionObserver actor = ShieldService.getInstance().createShieldObserver(this, creature);
				if (actor instanceof IActor) {
					creature.getObserveController().addObserver(actor);
					observed.put(creature.getObjectId(), (IActor) actor);
				}
			}
		}
	}

	@Override
	public void onLeaveZone(Creature creature, ZoneInstance zone) {
		IActor actor = observed.get(creature.getObjectId());
		if (actor != null) {
			creature.getObserveController().removeObserver((ActionObserver) actor);
			observed.remove(creature.getObjectId());
			actor.abort();
		}
	}

	public void setEnabled(boolean enable) {
		isEnabled = enable;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public int getSiegeLocationId() {
		return siegeLocationId;
	}

	public void setSiegeLocationId(int siegeLocationId) {
		this.siegeLocationId = siegeLocationId;
	}

	@Override
	public String toString() {
		return "LocId=" + siegeLocationId + "; Name=" + geometry.getName() + "; Bounds=" + geometry.getWorldBound();
	}

}

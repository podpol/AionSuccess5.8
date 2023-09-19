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

package com.aionemu.gameserver.world.zone.handler;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.CollisionMaterialActor;
import com.aionemu.gameserver.controllers.observer.IActor;
import com.aionemu.gameserver.geoEngine.scene.Spatial;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.templates.materials.MaterialSkill;
import com.aionemu.gameserver.model.templates.materials.MaterialTemplate;
import com.aionemu.gameserver.world.zone.ZoneInstance;
import javolution.util.FastMap;

/**
 * @author Rolandas
 */
public class MaterialZoneHandler implements ZoneHandler {

	FastMap<Integer, IActor> observed = new FastMap<Integer, IActor>();

	private Spatial geometry;
	private MaterialTemplate template;
	private boolean actOnEnter = false;
	private Race ownerRace = Race.NONE;

	public MaterialZoneHandler(Spatial geometry, MaterialTemplate template) {
		this.geometry = geometry;
		this.template = template;
		String name = geometry.getName(); 
		if (name.indexOf("FIRE_BOX") != -1 || name.indexOf("FIRE_SEMISPHERE") != -1 || name.indexOf("FIREPOT") != -1 ||
			name.indexOf("FIRE_CYLINDER") != -1 || name.indexOf("FIRE_CONE") != -1 || name.startsWith("BU_H_CENTERHALL"))
			actOnEnter = true;
		if (name.startsWith("BU_AB_DARKSP"))
			ownerRace = Race.ASMODIANS;
		else if (name.startsWith("BU_AB_LIGHTSP"))
			ownerRace = Race.ELYOS;
	}

	@Override
	public void onEnterZone(Creature creature, ZoneInstance zone) {
		if (ownerRace == creature.getRace())
			return;
		MaterialSkill foundSkill = null;
		for (MaterialSkill skill : template.getSkills()) {
			if (skill.getTarget().isTarget(creature)) {
				foundSkill = skill;
				break;
			}
		}
		if (foundSkill == null)
			return;
		CollisionMaterialActor actor = new CollisionMaterialActor(creature, geometry, template);
		creature.getObserveController().addObserver(actor);
		observed.put(creature.getObjectId(), actor);
		if (actOnEnter)
			actor.act();
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

}

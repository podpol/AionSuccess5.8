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
package com.aionemu.gameserver.controllers.observer;

import com.aionemu.gameserver.configs.main.GeoDataConfig;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.geoEngine.collision.CollisionIntention;
import com.aionemu.gameserver.geoEngine.collision.CollisionResults;
import com.aionemu.gameserver.geoEngine.math.Vector3f;
import com.aionemu.gameserver.geoEngine.scene.Spatial;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.materials.MaterialActTime;
import com.aionemu.gameserver.model.templates.materials.MaterialSkill;
import com.aionemu.gameserver.model.templates.materials.MaterialTemplate;
import com.aionemu.gameserver.model.templates.zone.ZoneClassName;
import com.aionemu.gameserver.services.WeatherService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.utils.gametime.DayTime;
import com.aionemu.gameserver.utils.gametime.GameTime;
import com.aionemu.gameserver.utils.gametime.GameTimeManager;
import com.aionemu.gameserver.world.zone.ZoneInstance;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class CollisionMaterialActor extends AbstractCollisionObserver implements IActor
{
	private MaterialTemplate actionTemplate;
	private AtomicReference<MaterialSkill> currentSkill = new AtomicReference<MaterialSkill>();
	
	public CollisionMaterialActor(Creature creature, Spatial geometry, MaterialTemplate actionTemplate) {
		super(creature, geometry, CollisionIntention.MATERIAL.getId());
		this.actionTemplate = actionTemplate;
	}
	
	private MaterialSkill getSkillForTarget(Creature creature) {
		if (creature instanceof Player) {
			Player player = (Player) creature;
			if (player.isProtectionActive())
				return null;
		}
		MaterialSkill foundSkill = null;
		for (MaterialSkill skill : actionTemplate.getSkills()) {
			if (skill.getTarget().isTarget(creature)) {
				foundSkill = skill;
				break;
			}
		}
		if (foundSkill == null)
			return null;
		int weatherCode = -1;
		if (creature.getActiveRegion() == null)
			return null;
		List<ZoneInstance> zones = creature.getActiveRegion().getZones(creature);
		for (ZoneInstance regionZone : zones) {
			if (regionZone.getZoneTemplate().getZoneType() == ZoneClassName.WEATHER) {
				Vector3f center = geometry.getWorldBound().getCenter();
				if (!regionZone.getAreaTemplate().isInside3D(center.x, center.y, center.z))
					continue;
				int weatherZoneId = DataManager.ZONE_DATA.getWeatherZoneId(regionZone.getZoneTemplate());
				weatherCode = WeatherService.getInstance().getWeatherCode(creature.getWorldId(), weatherZoneId);
				break;
			}
		}
		
		boolean dependsOnWeather = geometry.getName().indexOf("WEATHER") != -1;
		if (dependsOnWeather && weatherCode > 0)
			return null;
		if (foundSkill.getTime() == null)
			return foundSkill;
		GameTime gameTime = (GameTime) GameTimeManager.getGameTime().clone();
		if (foundSkill.getTime() == MaterialActTime.DAY && weatherCode == 0)
			return foundSkill;
		if (gameTime.getDayTime() == DayTime.NIGHT) {
			if (foundSkill.getTime() == MaterialActTime.NIGHT)
				return foundSkill;
		} else
			return foundSkill;

		return null;
	}
	
	@Override
	public void onMoved(CollisionResults collisionResults) {
		if (collisionResults.size() == 0) {
			return;
		} else {
			if (GeoDataConfig.GEO_MATERIALS_SHOWDETAILS && creature instanceof Player) {
				Player player = (Player) creature;
			}
			act();
		}
	}
	
	@Override
	public void act() {
		final MaterialSkill actSkill = getSkillForTarget(creature);
		if (currentSkill.getAndSet(actSkill) != actSkill) {
			if (actSkill == null)
				return;
			if (creature.getEffectController().hasAbnormalEffect(actSkill.getId())) {
				return;
			}
			Future<?> task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					if (!creature.getEffectController().hasAbnormalEffect(actSkill.getId())) {
						if (GeoDataConfig.GEO_MATERIALS_SHOWDETAILS && creature instanceof Player) {
							Player player = (Player) creature;
						}
						Skill skill = SkillEngine.getInstance().getSkill(creature, actSkill.getId(), actSkill.getSkillLevel(), creature);
						skill.getEffectedList().add(creature);
						skill.useWithoutPropSkill();
					}
				}
			}, 0, (long) (actSkill.getFrequency() * 1000));
			creature.getController().addTask(TaskId.MATERIAL_ACTION, task);
		}
	}
	
	@Override
	public void abort() {
		Future<?> existingTask = creature.getController().getTask(TaskId.MATERIAL_ACTION);
		if (existingTask != null) {
			creature.getController().cancelTask(TaskId.MATERIAL_ACTION);
		}
		currentSkill.set(null);
	}
	
	@Override
	public void died(Creature creature) {
		abort();
	}
	
	@Override
	public void setEnabled(boolean enable) {
	};
}
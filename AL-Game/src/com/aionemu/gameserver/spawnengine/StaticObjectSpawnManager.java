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
package com.aionemu.gameserver.spawnengine;

import com.aionemu.gameserver.controllers.StaticObjectController;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.StaticObject;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.templates.VisibleObjectTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.PlayerAwareKnownList;

/**
 * @author ATracer
 */
public class StaticObjectSpawnManager {

	/**
	 * @param spawnGroup
	 * @param instanceIndex
	 */
	public static void spawnTemplate(SpawnGroup2 spawn, int instanceIndex) {
		VisibleObjectTemplate objectTemplate = DataManager.ITEM_DATA.getItemTemplate(spawn.getNpcId());
		if (objectTemplate == null)
			return;

		if (spawn.hasPool()) {
			spawn.resetTemplates(instanceIndex);
			for (int i = 0; i < spawn.getPool(); i++) {
				SpawnTemplate template = spawn.getRndTemplate(instanceIndex);
				int objectId = IDFactory.getInstance().nextId();
				StaticObject staticObject = new StaticObject(objectId, new StaticObjectController(), template, objectTemplate);
				staticObject.setKnownlist(new PlayerAwareKnownList(staticObject));
				bringIntoWorld(staticObject, template, instanceIndex);
			}
		}
		else {
			for (SpawnTemplate template : spawn.getSpawnTemplates()) {
				int objectId = IDFactory.getInstance().nextId();
				StaticObject staticObject = new StaticObject(objectId, new StaticObjectController(), template, objectTemplate);
				staticObject.setKnownlist(new PlayerAwareKnownList(staticObject));
				bringIntoWorld(staticObject, template, instanceIndex);
			}
		}
	}

	/**
	 * @param visibleObject
	 * @param spawn
	 * @param instanceIndex
	 */
	private static void bringIntoWorld(VisibleObject visibleObject, SpawnTemplate spawn, int instanceIndex) {
		World world = World.getInstance();
		world.storeObject(visibleObject);
		world.setPosition(visibleObject, spawn.getWorldId(), instanceIndex, spawn.getX(), spawn.getY(), spawn.getZ(),
			spawn.getHeading());
		world.spawn(visibleObject);
	}
}

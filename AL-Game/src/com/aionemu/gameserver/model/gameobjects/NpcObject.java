package com.aionemu.gameserver.model.gameobjects;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.templates.housing.HousingNpc;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.spawnengine.SpawnEngine;

public class NpcObject extends HouseObject<HousingNpc> {

	Npc npc = null;

	public NpcObject(House owner, int objId, int templateId) {
		super(owner, objId, templateId);
	}

	@Override
	public void onUse(Player player) {
	}

	public synchronized void spawn() {
		super.spawn();
		if (npc == null) {
			HousingNpc template = (HousingNpc) getObjectTemplate();
			SpawnTemplate spawn = SpawnEngine.addNewSingleTimeSpawn(getOwnerHouse().getWorldId(), template.getNpcId(), getX(), getY(), getZ(), getHeading());

			npc = ((Npc) SpawnEngine.spawnObject(spawn, getOwnerHouse().getInstanceId()));
		}
	}

	@Override
	public synchronized void onDespawn() {
		super.onDespawn();
		if (npc != null) {
			npc.getController().onDelete();
			npc = null;
		}
	}

	@Override
	public synchronized boolean canExpireNow() {
		if (npc == null)
			return true;
		return npc.getTarget() == null;
	}

	public int getNpcObjectId() {
		return npc == null ? 0 : npc.getObjectId();
	}
}

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
package com.aionemu.gameserver.model.templates.spawns.siegespawns;

import com.aionemu.gameserver.model.siege.SiegeModType;
import com.aionemu.gameserver.model.siege.SiegeRace;
import com.aionemu.gameserver.model.siege.SiegeSpawnType;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnSpotTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;

/**
 *
 * @author xTz
 */
public class SiegeSpawnTemplate extends SpawnTemplate {

	private int siegeId;
	private SiegeRace siegeRace;
	private SiegeSpawnType siegeSpawnType;
	private SiegeModType siegeModType;

	public SiegeSpawnTemplate(SpawnGroup2 spawnGroup, SpawnSpotTemplate spot) {
		super(spawnGroup, spot);
	}
	
	public SiegeSpawnTemplate(SpawnGroup2 spawnGroup, float x, float y, float z, byte heading, int randWalk, String walkerId, int entityId, int fly) {
		super(spawnGroup, x, y, z, heading, randWalk, walkerId, entityId, fly);
	}

	public int getSiegeId() {
		return siegeId;
	}

	public SiegeRace getSiegeRace() {
		return siegeRace;
	}

	public SiegeSpawnType getSiegeSpawnType() {
		return siegeSpawnType;
	}

	public SiegeModType getSiegeModType() {
		return siegeModType;
	}

	public void setSiegeId(int siegeId) {
		this.siegeId = siegeId;
	}

	public void setSiegeRace(SiegeRace siegeRace) {
		this.siegeRace = siegeRace;
	}

	public void setSiegeSpawnType(SiegeSpawnType siegeSpawnType) {
		this.siegeSpawnType = siegeSpawnType;
	}

	public void setSiegeModType(SiegeModType siegeModType) {
		this.siegeModType = siegeModType;
	}

	public final boolean isPeace() {
		return siegeModType.equals(SiegeModType.PEACE);
	}

	public final boolean isSiege() {
		return siegeModType.equals(SiegeModType.SIEGE);
	}

	public final boolean isAssault() {
		return siegeModType.equals(SiegeModType.ASSAULT);
	}
}

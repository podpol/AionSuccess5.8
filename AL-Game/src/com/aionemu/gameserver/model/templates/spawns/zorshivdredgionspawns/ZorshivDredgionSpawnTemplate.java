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
package com.aionemu.gameserver.model.templates.spawns.zorshivdredgionspawns;

import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnSpotTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.zorshivdredgion.ZorshivDredgionStateType;

/**
 * @author Rinzler (Encom)
 */

public class ZorshivDredgionSpawnTemplate extends SpawnTemplate
{
	private int id;
	private ZorshivDredgionStateType zorshivDredgionType;
	
	public ZorshivDredgionSpawnTemplate(SpawnGroup2 spawnGroup, SpawnSpotTemplate spot) {
		super(spawnGroup, spot);
	}
	
	public ZorshivDredgionSpawnTemplate(SpawnGroup2 spawnGroup, float x, float y, float z, byte heading, int randWalk, String walkerId, int entityId, int fly) {
		super(spawnGroup, x, y, z, heading, randWalk, walkerId, entityId, fly);
	}
	
	public int getId() {
		return id;
	}
	
	public ZorshivDredgionStateType getZStateType() {
		return zorshivDredgionType;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setZStateType(ZorshivDredgionStateType zorshivDredgionType) {
		this.zorshivDredgionType = zorshivDredgionType;
	}
	
	public final boolean isLanding() {
		return zorshivDredgionType.equals(ZorshivDredgionStateType.LANDING);
	}
	
	public final boolean isPeace() {
		return zorshivDredgionType.equals(ZorshivDredgionStateType.PEACE);
	}
}
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
package com.aionemu.gameserver.model.templates.spawns.idiandepthsspawns;

import com.aionemu.gameserver.model.idiandepths.IdianDepthsStateType;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnSpotTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;

/**
 * @author Rinzler (Encom)
 */

public class IdianDepthsSpawnTemplate extends SpawnTemplate
{
	private int id;
	private IdianDepthsStateType idianDepthsType;
	
	public IdianDepthsSpawnTemplate(SpawnGroup2 spawnGroup, SpawnSpotTemplate spot) {
		super(spawnGroup, spot);
	}
	
	public IdianDepthsSpawnTemplate(SpawnGroup2 spawnGroup, float x, float y, float z, byte heading, int randWalk, String walkerId, int entityId, int fly) {
		super(spawnGroup, x, y, z, heading, randWalk, walkerId, entityId, fly);
	}
	
	public int getId() {
		return id;
	}
	
	public IdianDepthsStateType getIStateType() {
		return idianDepthsType;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIStateType(IdianDepthsStateType idianDepthsType) {
		this.idianDepthsType = idianDepthsType;
	}
	
	public final boolean isIdianDepthsOpen() {
		return idianDepthsType.equals(IdianDepthsStateType.OPEN);
	}
	
	public final boolean isIdianDepthsClosed() {
		return idianDepthsType.equals(IdianDepthsStateType.CLOSED);
	}
}
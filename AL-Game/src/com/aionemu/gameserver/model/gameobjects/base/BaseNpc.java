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
package com.aionemu.gameserver.model.gameobjects.base;

import com.aionemu.gameserver.controllers.NpcController;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawns.basespawns.BaseSpawnTemplate;

/**
 * @author Ranastic
 */

public class BaseNpc extends Npc
{
	private int baseId;
	
	public BaseNpc(int objId, NpcController controller, BaseSpawnTemplate spawnTemplate, NpcTemplate objectTemplate) {
		super(objId, controller, spawnTemplate, objectTemplate);
		this.baseId = spawnTemplate.getId();
	}
	
	public int getBaseId() {
		return baseId;
	}
	
	@Override
	public BaseSpawnTemplate getSpawn() {
		return (BaseSpawnTemplate) super.getSpawn();
	}
	
	@Override
	public boolean isEnemyFrom(Creature creature) {
		if (creature instanceof BaseNpc) {
			return true;
		} else {
			return super.isEnemyFrom(creature);
		}
	}
}
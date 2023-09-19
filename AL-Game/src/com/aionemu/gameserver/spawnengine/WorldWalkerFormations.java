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

import com.aionemu.commons.utils.internal.chmv8.PlatformDependent;

import java.util.Map;

/**
 * @author Rolandas
 */
public class WorldWalkerFormations {

	private Map<Integer, InstanceWalkerFormations> formations;

	public WorldWalkerFormations() {
		formations = PlatformDependent.newConcurrentHashMap();
	}

	/**
	 * @param instanceId
	 * @return
	 */
	protected InstanceWalkerFormations getInstanceFormations(int instanceId) {
		InstanceWalkerFormations instanceFormation = formations.get(instanceId);
		if (instanceFormation == null) {
			instanceFormation = new InstanceWalkerFormations();
			formations.put(instanceId, instanceFormation);
		}
		return instanceFormation;
	}

}

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
package com.aionemu.gameserver.services.instanceriftservice;

import com.aionemu.gameserver.model.instancerift.InstanceRiftLocation;
import com.aionemu.gameserver.model.instancerift.InstanceRiftStateType;

/**
 * @author Rinzler (Encom)
 */

public class Rift extends RiftInstance<InstanceRiftLocation>
{
	public Rift(InstanceRiftLocation instanceRift) {
		super(instanceRift);
	}
	
	@Override
	public void startInstanceRift() {
		getInstanceRiftLocation().setActiveInstanceRift(this);
		despawn();
		spawn(InstanceRiftStateType.OPEN);
	}
	
	@Override
	public void stopInstanceRift() {
		getInstanceRiftLocation().setActiveInstanceRift(null);
		despawn();
		spawn(InstanceRiftStateType.CLOSED);
	}
}
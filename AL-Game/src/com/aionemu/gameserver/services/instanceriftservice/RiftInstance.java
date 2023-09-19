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
import com.aionemu.gameserver.services.InstanceRiftService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class RiftInstance<RL extends InstanceRiftLocation>
{
	private boolean started;
	private final RL instanceRiftLocation;
	protected abstract void stopInstanceRift();
	protected abstract void startInstanceRift();
	private final AtomicBoolean closed = new AtomicBoolean();
	
	public RiftInstance(RL instanceRiftLocation) {
		this.instanceRiftLocation = instanceRiftLocation;
	}
	
	public final void start() {
		boolean doubleStart = false;
		synchronized (this) {
			if (started) {
				doubleStart = true;
			} else {
				started = true;
			}
		} if (doubleStart) {
			return;
		}
		startInstanceRift();
	}
	
	public final void stop() {
		if (closed.compareAndSet(false, true)) {
			stopInstanceRift();
		}
	}
	
	protected void spawn(InstanceRiftStateType type) {
		InstanceRiftService.getInstance().spawn(getInstanceRiftLocation(), type);
	}
	
	protected void despawn() {
		InstanceRiftService.getInstance().despawn(getInstanceRiftLocation());
	}
	
	public boolean isClosed() {
		return closed.get();
	}
	
	public RL getInstanceRiftLocation() {
		return instanceRiftLocation;
	}
	
	public int getInstanceRiftLocationId() {
		return instanceRiftLocation.getId();
	}
}
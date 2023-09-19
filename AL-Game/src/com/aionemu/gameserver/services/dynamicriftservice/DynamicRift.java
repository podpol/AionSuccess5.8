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
package com.aionemu.gameserver.services.dynamicriftservice;

import com.aionemu.gameserver.model.dynamicrift.DynamicRiftLocation;
import com.aionemu.gameserver.model.dynamicrift.DynamicRiftStateType;
import com.aionemu.gameserver.services.DynamicRiftService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class DynamicRift<DL extends DynamicRiftLocation>
{
	private boolean started;
	private final DL dynamicRiftLocation;
	protected abstract void stopDynamicRift();
	protected abstract void startDynamicRift();
	private final AtomicBoolean closed = new AtomicBoolean();
	
	public DynamicRift(DL dynamicRiftLocation) {
		this.dynamicRiftLocation = dynamicRiftLocation;
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
		startDynamicRift();
	}
	
	public final void stop() {
		if (closed.compareAndSet(false, true)) {
			stopDynamicRift();
		}
	}
	
	protected void spawn(DynamicRiftStateType type) {
		DynamicRiftService.getInstance().spawn(getDynamicRiftLocation(), type);
	}
	
	protected void despawn() {
		DynamicRiftService.getInstance().despawn(getDynamicRiftLocation());
	}
	
	public boolean isClosed() {
		return closed.get();
	}
	
	public DL getDynamicRiftLocation() {
		return dynamicRiftLocation;
	}
	
	public int getDynamicRiftLocationId() {
		return dynamicRiftLocation.getId();
	}
}
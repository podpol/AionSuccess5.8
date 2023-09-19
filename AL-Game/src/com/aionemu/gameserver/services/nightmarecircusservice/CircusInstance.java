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
package com.aionemu.gameserver.services.nightmarecircusservice;

import com.aionemu.gameserver.model.nightmarecircus.NightmareCircusLocation;
import com.aionemu.gameserver.model.nightmarecircus.NightmareCircusStateType;
import com.aionemu.gameserver.services.NightmareCircusService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class CircusInstance<CL extends NightmareCircusLocation>
{
	private boolean started;
	private final CL nightmareCircusLocation;
	protected abstract void stopNightmareCircus();
	protected abstract void startNightmareCircus();
	private final AtomicBoolean closed = new AtomicBoolean();
	
	public CircusInstance(CL nightmareCircusLocation) {
		this.nightmareCircusLocation = nightmareCircusLocation;
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
		startNightmareCircus();
	}
	
	public final void stop() {
		if (closed.compareAndSet(false, true)) {
			stopNightmareCircus();
		}
	}
	
	protected void spawn(NightmareCircusStateType type) {
		NightmareCircusService.getInstance().spawn(getNightmareCircusLocation(), type);
	}
	
	protected void despawn() {
		NightmareCircusService.getInstance().despawn(getNightmareCircusLocation());
	}
	
	public boolean isClosed() {
		return closed.get();
	}
	
	public CL getNightmareCircusLocation() {
		return nightmareCircusLocation;
	}
	
	public int getNightmareCircusLocationId() {
		return nightmareCircusLocation.getId();
	}
}
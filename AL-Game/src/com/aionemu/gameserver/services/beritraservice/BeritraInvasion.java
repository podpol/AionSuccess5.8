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
package com.aionemu.gameserver.services.beritraservice;

import com.aionemu.gameserver.model.beritra.BeritraLocation;
import com.aionemu.gameserver.model.beritra.BeritraStateType;
import com.aionemu.gameserver.services.BeritraService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class BeritraInvasion<BL extends BeritraLocation>
{
	private boolean started;
	private final BL beritraLocation;
	protected abstract void stopBeritraInvasion();
	protected abstract void startBeritraInvasion();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public BeritraInvasion(BL beritraLocation) {
		this.beritraLocation = beritraLocation;
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
		startBeritraInvasion();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopBeritraInvasion();
		}
	}
	
	protected void spawn(BeritraStateType type) {
		BeritraService.getInstance().spawn(getBeritraLocation(), type);
	}
	
	protected void despawn() {
		BeritraService.getInstance().despawn(getBeritraLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public BL getBeritraLocation() {
		return beritraLocation;
	}
	
	public int getBeritraLocationId() {
		return beritraLocation.getId();
	}
}
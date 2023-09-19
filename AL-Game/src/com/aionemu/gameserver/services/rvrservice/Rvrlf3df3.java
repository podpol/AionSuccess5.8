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
package com.aionemu.gameserver.services.rvrservice;

import com.aionemu.gameserver.model.rvr.RvrLocation;
import com.aionemu.gameserver.model.rvr.RvrStateType;
import com.aionemu.gameserver.services.RvrService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class Rvrlf3df3 <RL extends RvrLocation>
{
	private boolean started;
	private final RL rvrLocation;
	protected abstract void stopRvr();
	protected abstract void startRvr();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public Rvrlf3df3(RL rvrLocation) {
		this.rvrLocation = rvrLocation;
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
		startRvr();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopRvr();
		}
	}
	
	protected void spawn(RvrStateType type) {
		RvrService.getInstance().spawn(getRvrLocation(), type);
	}
	
	protected void despawn() {
		RvrService.getInstance().despawn(getRvrLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public RL getRvrLocation() {
		return rvrLocation;
	}
	
	public int getRvrLocationId() {
		return rvrLocation.getId();
	}
}
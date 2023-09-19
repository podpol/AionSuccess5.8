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
package com.aionemu.gameserver.services.idiandepthsservice;

import com.aionemu.gameserver.model.idiandepths.IdianDepthsLocation;
import com.aionemu.gameserver.model.idiandepths.IdianDepthsStateType;
import com.aionemu.gameserver.services.IdianDepthsService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class IdianDepths<IL extends IdianDepthsLocation>
{
	private boolean started;
	private final IL idianDepthsLocation;
	protected abstract void stopIdianDepths();
	protected abstract void startIdianDepths();
	private final AtomicBoolean closed = new AtomicBoolean();
	
	public IdianDepths(IL idianDepthsLocation) {
		this.idianDepthsLocation = idianDepthsLocation;
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
		startIdianDepths();
	}
	
	public final void stop() {
		if (closed.compareAndSet(false, true)) {
			stopIdianDepths();
		}
	}
	
	protected void spawn(IdianDepthsStateType type) {
		IdianDepthsService.getInstance().spawn(getIdianDepthsLocation(), type);
	}
	
	protected void despawn() {
		IdianDepthsService.getInstance().despawn(getIdianDepthsLocation());
	}
	
	public boolean isClosed() {
		return closed.get();
	}
	
	public IL getIdianDepthsLocation() {
		return idianDepthsLocation;
	}
	
	public int getIdianDepthsLocationId() {
		return idianDepthsLocation.getId();
	}
}
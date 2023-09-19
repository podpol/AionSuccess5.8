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
package com.aionemu.gameserver.services.iuservice;

import com.aionemu.gameserver.model.iu.IuLocation;
import com.aionemu.gameserver.model.iu.IuStateType;
import com.aionemu.gameserver.services.IuService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class Iu<IUL extends IuLocation>
{
	private boolean started;
	private final IUL iuLocation;
	protected abstract void stopConcert();
	protected abstract void startConcert();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public Iu(IUL iuLocation) {
		this.iuLocation = iuLocation;
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
		startConcert();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopConcert();
		}
	}
	
	protected void spawn(IuStateType type) {
		IuService.getInstance().spawn(getIuLocation(), type);
	}
	
	protected void despawn() {
		IuService.getInstance().despawn(getIuLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public IUL getIuLocation() {
		return iuLocation;
	}
	
	public int getIuLocationId() {
		return iuLocation.getId();
	}
}
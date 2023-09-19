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
package com.aionemu.gameserver.services.anohaservice;

import com.aionemu.gameserver.model.anoha.AnohaLocation;
import com.aionemu.gameserver.model.anoha.AnohaStateType;
import com.aionemu.gameserver.services.AnohaService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class BerserkAnoha<AL extends AnohaLocation>
{
	private boolean started;
	private final AL anohaLocation;
	protected abstract void stopAnoha();
	protected abstract void startAnoha();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public BerserkAnoha(AL anohaLocation) {
		this.anohaLocation = anohaLocation;
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
		startAnoha();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopAnoha();
		}
	}
	
	protected void spawn(AnohaStateType type) {
		AnohaService.getInstance().spawn(getAnohaLocation(), type);
	}
	
	protected void despawn() {
		AnohaService.getInstance().despawn(getAnohaLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public AL getAnohaLocation() {
		return anohaLocation;
	}
	
	public int getAnohaLocationId() {
		return anohaLocation.getId();
	}
}
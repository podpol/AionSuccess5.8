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
package com.aionemu.gameserver.services.zorshivdredgionservice;

import com.aionemu.gameserver.model.zorshivdredgion.ZorshivDredgionLocation;
import com.aionemu.gameserver.model.zorshivdredgion.ZorshivDredgionStateType;
import com.aionemu.gameserver.services.ZorshivDredgionService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class ZorshivDredgion<ZL extends ZorshivDredgionLocation>
{
	private boolean started;
	private final ZL zorshivDredgionLocation;
	protected abstract void stopZorshivDredgion();
	protected abstract void startZorshivDredgion();
	private final AtomicBoolean peace = new AtomicBoolean();
	
	public ZorshivDredgion(ZL zorshivDredgionLocation) {
		this.zorshivDredgionLocation = zorshivDredgionLocation;
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
		startZorshivDredgion();
	}
	
	public final void stop() {
		if (peace.compareAndSet(false, true)) {
			stopZorshivDredgion();
		}
	}
	
	protected void spawn(ZorshivDredgionStateType type) {
		ZorshivDredgionService.getInstance().spawn(getZorshivDredgionLocation(), type);
	}
	
	protected void despawn() {
		ZorshivDredgionService.getInstance().despawn(getZorshivDredgionLocation());
	}
	
	public boolean isPeace() {
		return peace.get();
	}
	
	public ZL getZorshivDredgionLocation() {
		return zorshivDredgionLocation;
	}
	
	public int getZorshivDredgionLocationId() {
		return zorshivDredgionLocation.getId();
	}
}
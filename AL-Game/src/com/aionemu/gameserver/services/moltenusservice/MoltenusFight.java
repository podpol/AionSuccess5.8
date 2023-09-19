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
package com.aionemu.gameserver.services.moltenusservice;

import com.aionemu.gameserver.model.moltenus.MoltenusLocation;
import com.aionemu.gameserver.model.moltenus.MoltenusStateType;
import com.aionemu.gameserver.services.MoltenusService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class MoltenusFight<ML extends MoltenusLocation>
{
	private boolean started;
	private final ML moltenusLocation;
	protected abstract void stopMoltenus();
	protected abstract void startMoltenus();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public MoltenusFight(ML moltenusLocation) {
		this.moltenusLocation = moltenusLocation;
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
		startMoltenus();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopMoltenus();
		}
	}
	
	protected void spawn(MoltenusStateType type) {
		MoltenusService.getInstance().spawn(getMoltenusLocation(), type);
	}
	
	protected void despawn() {
		MoltenusService.getInstance().despawn(getMoltenusLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public ML getMoltenusLocation() {
		return moltenusLocation;
	}
	
	public int getMoltenusLocationId() {
		return moltenusLocation.getId();
	}
}
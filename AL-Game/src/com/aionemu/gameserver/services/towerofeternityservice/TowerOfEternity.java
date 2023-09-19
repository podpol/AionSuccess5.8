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
package com.aionemu.gameserver.services.towerofeternityservice;

import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityLocation;
import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityStateType;
import com.aionemu.gameserver.services.TowerOfEternityService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Wnkrz on 22/08/2017.
 */

public abstract class TowerOfEternity <TE extends TowerOfEternityLocation>
{
    private boolean started;
    private final TE towerOfEternityLocation;
    protected abstract void stopTowerOfEternity();
    protected abstract void startTowerOfEternity();
    private final AtomicBoolean closed = new AtomicBoolean();
	
    public TowerOfEternity(TE towerOfEternityLocation) {
        this.towerOfEternityLocation = towerOfEternityLocation;
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
        startTowerOfEternity();
    }
	
    public final void stop() {
        if (closed.compareAndSet(false, true)) {
            stopTowerOfEternity();
        }
    }
	
    protected void spawn(TowerOfEternityStateType type) {
        TowerOfEternityService.getInstance().spawn(getTowerOfEternityLocation(), type);
    }
	
    protected void despawn() {
        TowerOfEternityService.getInstance().despawn(getTowerOfEternityLocation());
    }
	
    public boolean isClosed() {
        return closed.get();
    }
	
    public TE getTowerOfEternityLocation() {
        return towerOfEternityLocation;
    }
	
    public int getTowerOfEternityLocationId() {
        return towerOfEternityLocation.getId();
    }
}
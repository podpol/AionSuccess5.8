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
package com.aionemu.gameserver.services.abysslandingservice;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.AbyssLandingDAO;
import com.aionemu.gameserver.model.landing.LandingLocation;
import com.aionemu.gameserver.model.landing.LandingStateType;
import com.aionemu.gameserver.services.AbyssLandingService;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Landing<RL extends LandingLocation>
{
    private int level;
	private boolean started;
    private final RL landingLocation;
    protected abstract void stopLanding();
	protected abstract void saveLanding();
    protected abstract void startLanding(int level);
    private final AtomicBoolean closed = new AtomicBoolean();
	
    public Landing(RL landingLocation) {
        this.landingLocation = landingLocation;
    }
	
    public final void start(int level) {
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
        startLanding(level);
    }
	
    public final void stop() {
        stopLanding();
    }
	
    public final void update() {
        saveLanding();
    }
	
    protected void spawn(LandingStateType type) {
        AbyssLandingService.spawn(getLandingLocation(), type);
    }
	
    protected void despawn() {
        AbyssLandingService.despawn(getLandingLocation());
    }
	
    public boolean isClosed() {
        return closed.get();
    }
	
    public RL getLandingLocation() {
        return landingLocation;
    }
	
    public int getLandingLocationId() {
        return landingLocation.getId();
    }
	
    public int getLevel() {
        return this.level;
    }
	
    public void setLevel(int level) {
        this.level = level;
    }
	
    private AbyssLandingDAO getDAO() {
        return DAOManager.getDAO(AbyssLandingDAO.class);
    }
}
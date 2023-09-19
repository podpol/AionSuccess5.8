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
package com.aionemu.gameserver.services.abysslandingservice.landingspecialservice;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.AbyssSpecialLandingDAO;
import com.aionemu.gameserver.model.landing_special.LandingSpecialLocation;
import com.aionemu.gameserver.model.landing_special.LandingSpecialStateType;
import com.aionemu.gameserver.services.AbyssLandingSpecialService;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SpecialLanding <RL extends LandingSpecialLocation>
{
    private boolean started;
    private final RL spacialLandingLocation;
    private LandingSpecialStateType type;
    protected abstract void stopLanding();
    protected abstract void startLanding();
    private final AtomicBoolean closed = new AtomicBoolean();
	
    public SpecialLanding(RL specialLandingLocation) {
        this.spacialLandingLocation = specialLandingLocation;
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
        startLanding();
    }
	
    public final void stop() {
        stopLanding();
    }
	
    protected void spawn(LandingSpecialStateType type) {
        AbyssLandingSpecialService.spawn(getSpecialLandingLocation(), type);
    }
	
    protected void despawn() {
        AbyssLandingSpecialService.despawn(getSpecialLandingLocation());
    }
	
    public boolean isClosed() {
        return closed.get();
    }
	
    public RL getSpecialLandingLocation() {
        return spacialLandingLocation;
    }
	
    public int getSpecialLandingLocationId() {
        return spacialLandingLocation.getId();
    }
	
    public LandingSpecialStateType getType() {
        return this.type;
    }
	
    public void setType(LandingSpecialStateType tp) {
        this.type = tp;
    }
	
	private AbyssSpecialLandingDAO getDAO() {
        return DAOManager.getDAO(AbyssSpecialLandingDAO.class);
    }
}
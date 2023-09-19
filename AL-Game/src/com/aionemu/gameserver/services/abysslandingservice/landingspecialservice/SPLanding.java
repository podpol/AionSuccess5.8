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

public class SPLanding  extends SpecialLanding<LandingSpecialLocation>
{
    public SPLanding(LandingSpecialLocation landing) {
        super(landing);
    }
	
    @Override
    public void startLanding() {
        getSpecialLandingLocation().setActiveLanding(this);
        if (!getSpecialLandingLocation().getSpawned().isEmpty()) {
            despawn();
        }
        spawn(LandingSpecialStateType.ACTIVE);
    }
	
	public void saveLanding() {
        DAOManager.getDAO(AbyssSpecialLandingDAO.class).updateLocation(getSpecialLandingLocation());
    }
	
    @Override
    public void stopLanding() {
        getSpecialLandingLocation().setActiveLanding(null);
        despawn();
        spawn(LandingSpecialStateType.NO_ACTIVE);
    }
}
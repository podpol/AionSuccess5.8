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

import com.aionemu.commons.services.CronService;
import com.aionemu.gameserver.configs.main.AbyssLandingConfig;
import com.aionemu.gameserver.model.landing.LandingLocation;
import com.aionemu.gameserver.services.AbyssLandingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandingUpdateService
{
    private static final Logger log = LoggerFactory.getLogger(LandingUpdateService.class);
	
    final LandingLocation redemptionLanding = AbyssLandingService.getInstance().redemptionLanding();
    final LandingLocation harbingerLanding = AbyssLandingService.getInstance().harbingerLanding();
	
	//Quest Points.
    final int redemptionPts = redemptionLanding.getQuestPoints() - redemptionLanding.getQuestPoints();
    final int harbingerPts = harbingerLanding.getQuestPoints() - harbingerLanding.getQuestPoints();
	
	//Monument Points.
	final int redemptionPts1 = redemptionLanding.getMonumentsPoints() - redemptionLanding.getMonumentsPoints();
    final int harbingerPts1 = harbingerLanding.getMonumentsPoints() - harbingerLanding.getMonumentsPoints();
	
	//Facility Points.
	final int redemptionPts2 = redemptionLanding.getFacilityPoints() - redemptionLanding.getFacilityPoints();
    final int harbingerPts2 = harbingerLanding.getFacilityPoints() - harbingerLanding.getFacilityPoints();
	
	//Commander Points.
	final int redemptionPts3 = redemptionLanding.getCommanderPoints() - redemptionLanding.getCommanderPoints();
    final int harbingerPts3 = harbingerLanding.getCommanderPoints() - harbingerLanding.getCommanderPoints();
	
    private LandingUpdateService() {
    }
	
    public void initResetQuestPoints() {
        if (AbyssLandingConfig.ABYSS_LANDING_QUEST_RESET_ENABLED) {
            CronService.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    resetQuestPoints();
                }
            }, AbyssLandingConfig.ABYSS_LANDING_QUEST_RESET_TIME);
        }
    }
	
	public void initResetAbyssLandingPoints() {
        if (AbyssLandingConfig.ABYSS_LANDING_POINTS_RESET_ENABLED) {
            CronService.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
					resetMonumentPoints();
					resetFacilityPoints();
					resetCommanderPoints();
                }
            }, AbyssLandingConfig.ABYSS_LANDING_POINTS_RESET_TIME);
        }
    }
	
    public void resetQuestPoints() {
        log.debug("##### Abyss Landing Reset Quest Points #####");
        long startTime = System.currentTimeMillis();
        //Redemption's Landing.
        redemptionLanding.setPoints(redemptionPts);
        redemptionLanding.setQuestPoints(0);
        AbyssLandingService.getInstance().checkRedemptionLanding(redemptionLanding.getPoints(), false);
        //Harbinger's Landing.
        harbingerLanding.setPoints(harbingerPts);
        harbingerLanding.setQuestPoints(0);
        AbyssLandingService.getInstance().checkHarbingerLanding(harbingerLanding.getPoints(), false);
        //Update All Landing.
        AbyssLandingService.getInstance().onUpdate();
    }
	
	public void resetMonumentPoints() {
		log.debug("##### Abyss Landing Reset Monuments Points #####");
        long startTime = System.currentTimeMillis();
        //Redemption's Landing.
        redemptionLanding.setPoints(redemptionPts1);
        redemptionLanding.setMonumentsPoints(0);
        AbyssLandingService.getInstance().checkRedemptionLanding(redemptionLanding.getPoints(), false);
        //Harbinger's Landing.
        harbingerLanding.setPoints(harbingerPts1);
        harbingerLanding.setMonumentsPoints(0);
        AbyssLandingService.getInstance().checkHarbingerLanding(harbingerLanding.getPoints(), false);
        //Update All Landing.
        AbyssLandingService.getInstance().onUpdate();
    }
	
	public void resetFacilityPoints() {
		log.debug("##### Abyss Landing Reset Facility Points #####");
        long startTime = System.currentTimeMillis();
        //Redemption's Landing.
        redemptionLanding.setPoints(redemptionPts2);
        redemptionLanding.setFacilityPoints(0);
        AbyssLandingService.getInstance().checkRedemptionLanding(redemptionLanding.getPoints(), false);
        //Harbinger's Landing.
        harbingerLanding.setPoints(harbingerPts2);
        harbingerLanding.setFacilityPoints(0);
        AbyssLandingService.getInstance().checkHarbingerLanding(harbingerLanding.getPoints(), false);
        //Update All Landing.
        AbyssLandingService.getInstance().onUpdate();
    }
	
	public void resetCommanderPoints() {
		log.debug("##### Abyss Landing Reset Commander Points #####");
        long startTime = System.currentTimeMillis();
        //Redemption's Landing.
        redemptionLanding.setPoints(redemptionPts3);
        redemptionLanding.setCommanderPoints(0);
        AbyssLandingService.getInstance().checkRedemptionLanding(redemptionLanding.getPoints(), false);
        //Harbinger's Landing.
        harbingerLanding.setPoints(harbingerPts3);
        harbingerLanding.setCommanderPoints(0);
        AbyssLandingService.getInstance().checkHarbingerLanding(harbingerLanding.getPoints(), false);
        //Update All Landing.
        AbyssLandingService.getInstance().onUpdate();
    }
	
    public static LandingUpdateService getInstance() {
        return SingletonHolder.instance;
    }
	
    private static class SingletonHolder {
        protected static final LandingUpdateService instance = new LandingUpdateService();
    }
}
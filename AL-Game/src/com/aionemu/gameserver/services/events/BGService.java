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
package com.aionemu.gameserver.services.events;

import com.aionemu.gameserver.eventEngine.EventScheduler;
import com.aionemu.gameserver.eventEngine.events.BattlegroundEvent;
import com.aionemu.gameserver.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Ghostur (Aion-Unique)
 */
public class BGService
{
    Logger log = LoggerFactory.getLogger(EventService.class);
    private static final int DELAY = 60 * 100;
    private List<ScheduledFuture<?>> futures = new ArrayList<ScheduledFuture<?>>();
	
    private BGService() {
        register(DELAY);
        log.info("[BGService] is initialized...");
    }
	
    public void register(int delay) {
        if (futures.isEmpty()) {
            BattlegroundEvent bgEvent = new BattlegroundEvent();
            bgEvent.setPriority(1);
            futures.add(EventScheduler.getInstance().scheduleAtFixedRate(bgEvent, delay, 6 * 60 * 1000));
        }
    }
	
    private static class SingletonHolder {
        protected static final BGService instance = new BGService();
    }
	
    public static final BGService getInstance() {
        return SingletonHolder.instance;
    }
}

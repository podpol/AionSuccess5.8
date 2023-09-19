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
package com.aionemu.gameserver.eventEngine;

import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by wanke on 12/02/2017.
 */

class EventScheduleWrapper implements Runnable
{
    private static final int RECHECK_DELAY = 2;
    private final Event event;
    private boolean first = true;
    private ScheduledFuture<?> last_future;
	
    public EventScheduleWrapper(Event event) {
        this.event = event;
    }
	
    @Override
    public void run() {
        if (last_future != null) {
            if (!last_future.isDone()) {
                return;
            }
        } if (!check()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    check();
                }
            };
            last_future = ThreadPoolManager.getInstance().schedule(runnable, RECHECK_DELAY * 60 * 1000);
        }
    }
	
    private boolean check() {
        if (event.isFinished() || first) {
            first = false;
            EventScheduler.getInstance().schedule(event, 10);
            return true;
        } else {
            event.cancel(true);
            return false;
        }
    }
}
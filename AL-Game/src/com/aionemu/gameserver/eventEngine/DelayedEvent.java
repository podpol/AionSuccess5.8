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

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanke on 12/02/2017.
 */

class DelayedEvent extends Event implements Comparable<DelayedEvent>
{
    private final Date forecast;
    private final Event event;
	
    public DelayedEvent(Event event, int delay) {
        this.forecast = new Date(System.currentTimeMillis() + delay);
        this.event = event;
    }
	
    @Override
    public int compareTo(DelayedEvent o) {
        int delay = (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        if (delay > (0 - Event.MAX_PRIORITY) * 60 * 1000 && delay < MAX_PRIORITY * 60 * 1000) {
            delay = (int) ((getDelay(TimeUnit.MILLISECONDS) - getEvent().getPriority() * 60 * 1000) - (o.getDelay(TimeUnit.MILLISECONDS) - o.getEvent().getPriority() * 60 * 1000));
        }
        return delay;
    }
	
    public long getDelay(TimeUnit unit) {
        return unit.convert(forecast.compareTo(new Date()), TimeUnit.MILLISECONDS);
    }
	
    public Event getEvent() {
        return event;
    }

    @Override
    public void execute() {
        getEvent().execute();
    }
	
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return event.cancel(mayInterruptIfRunning);
    }
	
    @Override
    public boolean isFinished() {
        return event.isFinished();
    }
	
    @Override
    public int getCooldown() {
        return event.getCooldown();
    }
	
    @Override
    public int getPriority() {
        return event.getPriority();
    }
	
    @Override
    public void setPriority(int priority) {
        event.setPriority(priority);
    }
	
    @Override
    protected void onReset() {
        event.onReset();
    }
}
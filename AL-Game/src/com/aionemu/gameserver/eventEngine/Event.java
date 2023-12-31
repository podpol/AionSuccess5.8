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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.Collection;

/**
 * Created by wanke on 12/02/2017.
 */

public abstract class Event implements Runnable
{
    public static final int MAX_PRIORITY = 10;
    public static final int MIN_PRIORITY = 0;
    public static final int DEFAULT_PRIORITY = 5;
    private int priority = DEFAULT_PRIORITY;
    private boolean finished = false;
	
    public final void run() {
        execute();
    }
	
    abstract protected void execute();
	
    public final void reset() {
        finished = false;
        onReset();
    }
	
    abstract protected void onReset();
	
    protected void finish() {
        finished = true;
    }
	
    public abstract boolean cancel(boolean mayInterruptIfRunning);
	
    public int getCooldown() {
        return 30 * 1000;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority > MAX_PRIORITY) {
            priority = MAX_PRIORITY;
        } if (priority < MIN_PRIORITY) {
            priority = MIN_PRIORITY;
        }
        this.priority = priority;
    }
	
    public boolean isFinished() {
        return finished;
    }
	
    protected void announce(Player pl, String msg) {
        announce(pl, msg, 0);
    }
	
    protected void announce(Collection<Player> players, String msg) {
        for (Player pl : players) {
            announce(pl, msg, 0);
        }
    }
	
    protected void announce(final Player pl, final String msg, int delay) {
        if (delay > 0) {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    PacketSendUtility.sendSys3Message(pl, "Event", msg);
                }
            }, delay);
        } else {
            PacketSendUtility.sendSys3Message(pl, "Event", msg);
        }
    }
	
    protected void announceAll(String msg) {
        announceAll(msg, 0);
    }
	
    protected void announceAll(final String msg, int delay) {
        if (delay > 0) {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    World.getInstance().doOnAllPlayers(new Visitor<Player>() {
                        @Override
                        public void visit(Player pl) {
                            if (pl.getBattleground() == null) {
                                PacketSendUtility.sendSys3Message(pl, "Event", msg);
                            }
                        }
                    });
                }
            }, delay);
        } else {
            World.getInstance().doOnAllPlayers(new Visitor<Player>() {
                @Override
                public void visit(Player pl) {
                    if (pl.getBattleground() == null) {
                        PacketSendUtility.sendSys3Message(pl, "Event", msg);
                    }
                }
            });
        }
    }
}
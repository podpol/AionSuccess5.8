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
package com.aionemu.gameserver.eventEngine.events;

import com.aionemu.gameserver.eventEngine.Event;
import com.aionemu.gameserver.services.events.LadderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanke on 12/02/2017.
 */

public class BattlegroundEvent extends Event
{
    private List<Integer> battlegrounds = new ArrayList<Integer>();
	
    @Override
    public void execute() {
        LadderService.getInstance().createNormalBgs(this);
    }
	
    public int getBgCount() {
        return battlegrounds.size();
    }
	
    public void onCreate(Integer bgId) {
        if (!battlegrounds.contains(bgId)) {
            battlegrounds.add(bgId);
        }
    }
	
    public void onEnd(Integer bgId) {
        battlegrounds.remove(bgId);
        if (battlegrounds.isEmpty()) {
            this.onEnd();
        }
    }
	
    public void onEnd() {
        super.finish();
    }
	
    @Override
    protected void onReset() {
        battlegrounds.clear();
    }
	
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }
}
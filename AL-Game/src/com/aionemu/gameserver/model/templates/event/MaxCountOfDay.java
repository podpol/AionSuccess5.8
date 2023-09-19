package com.aionemu.gameserver.model.templates.event;

/**
 * Created by wanke on 03/03/2017.
 */

public class MaxCountOfDay
{
    private int thisCount;
	
    public MaxCountOfDay(int thisCount) {
        this.thisCount = thisCount;
    }
	
    public int getThisCount() {
        return thisCount;
    }
	
    public void setThisCount(int thisCount) {
        this.thisCount = thisCount;
    }
}
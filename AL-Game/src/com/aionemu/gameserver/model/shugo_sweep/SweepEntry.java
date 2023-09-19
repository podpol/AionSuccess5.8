package com.aionemu.gameserver.model.shugo_sweep;

/**
 * Created by Wnkrz on 24/10/2017.
 */
public class SweepEntry {

    private int id;
    private boolean isReward;

    public SweepEntry(int id, boolean isReward) {
        this.id = id;
        this.isReward = isReward;
    }

    public int getId() {
        return id;
    }

    public boolean isReward() {
        return isReward;
    }
}

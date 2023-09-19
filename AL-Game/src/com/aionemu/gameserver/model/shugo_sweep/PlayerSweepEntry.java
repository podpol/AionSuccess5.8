package com.aionemu.gameserver.model.shugo_sweep;

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * Created by Wnkrz on 24/10/2017.
 */


public class PlayerSweepEntry extends SweepEntry {

    private PersistentState persistentState;

    public PlayerSweepEntry(int id, boolean isReward , PersistentState persistentState) {
        super(id, isReward);
    }

    public PersistentState getPersistentState() {
        return persistentState;
    }

    public void setPersistentState(PersistentState persistentState) {
        switch (persistentState) {
            case DELETED:
                if (this.persistentState == PersistentState.NEW) {
                    this.persistentState = PersistentState.NOACTION;
                } else {
                    this.persistentState = PersistentState.DELETED;
                }
                break;
            case UPDATE_REQUIRED:
                if (this.persistentState != PersistentState.NEW) {
                    this.persistentState = PersistentState.UPDATE_REQUIRED;
                }
                break;
            case NOACTION:
                break;
            default:
                this.persistentState = persistentState;
        }
    }
}

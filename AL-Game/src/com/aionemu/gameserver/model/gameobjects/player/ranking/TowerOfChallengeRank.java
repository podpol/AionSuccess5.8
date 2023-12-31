package com.aionemu.gameserver.model.gameobjects.player.ranking;

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * Created by Wnkrz on 24/07/2017.
 */

public class TowerOfChallengeRank
{
    private int rank;
    private int bestRank;
    private int lowRank;
    private int currentTime;
    private int lastTime;
    private int bestTime;
    private PersistentState persistentState;
	
    public TowerOfChallengeRank(int rank, int bestRank, int low_rank, int current_time, int last_time, int best_time){
        this.rank = rank;
        this.bestRank = bestRank;
        this.lowRank = low_rank;
        this.currentTime = current_time;
        this.lastTime = last_time;
        this.bestTime = best_time;
    }
	
    public int getRank() {
        return rank;
    }
    public int getBestRank() {
        return bestRank;
    }
    public int getLowRank() {
        return lowRank;
    }
    public int getCurrentTime() {
        return currentTime;
    }
    public int getLastTime() {
        return lastTime;
    }
    public int getBestTime() {
        return bestTime;
    }
    public void setRank(int r) {
        this.rank = r;
    }
    public void setBestRank(int r) {
        this.bestRank = r;
    }
    public void  setLowRank(int r) {
        this.lowRank = r;
    }
    public void setCurrentTime(int r) {
        this.currentTime = r;
    }
    public void setLastTime(int r) {
        this.lastTime = r;
    }
    public void setBestTime(int r) {
        this.bestTime = r;
    }
	
    public PersistentState getPersistentState() {
        return persistentState;
    }
	
    public void setPersistentState(PersistentState persistentState) {
        if (persistentState != PersistentState.UPDATE_REQUIRED || this.persistentState != PersistentState.NEW) {
            this.persistentState = persistentState;
		}
    }
}
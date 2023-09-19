package com.aionemu.gameserver.model.gameobjects.player.ranking;

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * Created by Wnkrz on 24/07/2017.
 */
public class Arena6V6Ranking {

    //rank
    private int rank;
    private int bestRank;

    //competiton Points
    private int points;
    private int lastPoints;
    private int highPoints;
    private int lowPoints;

    private int possitionMatch;

    private PersistentState persistentState;

    public Arena6V6Ranking(int rank, int bestRank, int points, int lastPoints, int highPoints, int lowPoints, int possitionMatch){
        this.rank = rank;
        this.bestRank = bestRank;
        this.points = points;
        this.lastPoints = lastPoints;
        this.highPoints = highPoints;
        this.lowPoints = lowPoints;
        this.possitionMatch = possitionMatch;
    }

    public int getRank(){
        return rank;
    }

    public int getBestRank(){
        return bestRank;
    }

    public int getPoints(){
        return points;
    }

    public int getLastPoints(){
        return lastPoints;
    }

    public int getHighPoints(){
        return highPoints;
    }

    public int getLowPoints() {
        return lowPoints;
    }

    public int getPossitionMatch(){
        return possitionMatch;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setBestRank(int rank){
        this.bestRank = rank;
    }

    public void setPoints(int pts){
        this.points = pts;
    }

    public void setLastPoints(int pts){
        this.lastPoints = pts;
    }

    public void setHighPoints(int pts){
        this.highPoints = pts;
    }

    public void setLowPoints(int pts) {
        this.lowPoints = pts;
    }

    public void setPossitionMatch(int pos){
        this.possitionMatch = pos;
    }

    /**
     * @return the persistentState
     */
    public PersistentState getPersistentState() {
        return persistentState;
    }

    /**
     * @param persistentState
     *          the persistentState to set
     */
    public void setPersistentState(PersistentState persistentState) {
        if (persistentState != PersistentState.UPDATE_REQUIRED || this.persistentState != PersistentState.NEW)
            this.persistentState = persistentState;
    }
}

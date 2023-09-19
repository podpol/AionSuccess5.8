package com.aionemu.gameserver.model.ranking;

import com.aionemu.gameserver.model.PlayerClass;

/**
 * Created by Wnkrz on 24/07/2017.
 */
public class SeasonRankingResult {

    private String playerName;
    private int oldRank;
    private int rank;
    private int pc;
    private PlayerClass playerClass;
    private int playerRace;
    private int playerId;

    public SeasonRankingResult(String playerName, int oldRank, int rank, int pc, PlayerClass playerClass, int playerRace, int playerId){
        this.playerName = playerName;
        this.oldRank = oldRank;
        this.rank = rank;
        this.pc = pc;
        this.playerClass = playerClass;
        this.playerRace = playerRace;
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getRank(){
        return rank;
    }

    public int getOldRank() {
        return oldRank;
    }

    public int getPlayerRace(){
        return playerRace;
    }

    public PlayerClass getPlayerClass(){
        return playerClass;
    }

    public int getPoints() {
        return pc;
    }
}

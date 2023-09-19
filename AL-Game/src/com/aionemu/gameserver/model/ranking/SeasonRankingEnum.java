package com.aionemu.gameserver.model.ranking;

/**
 * Created by Wnkrz on 24/07/2017.
 */

public enum  SeasonRankingEnum {
    HALL_OF_TENACITY(1),
    ARENA_OF_TENACITY(541),
    TOWER_OF_CHALLENGE(2),
    ARENA_6V6(3);

    private int tableId;

    private SeasonRankingEnum(int tableId){
        this.tableId = tableId;
    }

    public int getId() {
        return tableId;
    }
}


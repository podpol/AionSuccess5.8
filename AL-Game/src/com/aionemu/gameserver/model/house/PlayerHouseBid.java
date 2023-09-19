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
package com.aionemu.gameserver.model.house;

import java.sql.Timestamp;

public class PlayerHouseBid implements Comparable<PlayerHouseBid>
{
	private int playerId;
    private int houseId;
    private long offer;
    private Timestamp time;
	
    public PlayerHouseBid(int playerId, int houseId, long offer, Timestamp time) {
        this.playerId = playerId;
        this.houseId = houseId;
        this.offer = offer;
        this.time = time;
    }
	
    public int getPlayerId() {
        return playerId;
    }
	
    public int getHouseId() {
        return houseId;
    }
	
    public long getBidOffer() {
        return offer;
    }
	
    public Timestamp getTime() {
        return time;
    }
	
    @Override
    public int compareTo(PlayerHouseBid o) {
        return (int) (time.getTime() - o.getTime().getTime());
    }
}
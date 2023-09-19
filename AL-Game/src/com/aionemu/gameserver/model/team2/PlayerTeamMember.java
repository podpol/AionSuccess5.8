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
package com.aionemu.gameserver.model.team2;

import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * @author ATracer
 */
public class PlayerTeamMember implements TeamMember<Player> {

    final Player player;
    private long lastOnlineTime;

    public PlayerTeamMember(Player player) {
        this.player = player;
    }

    @Override
    public Integer getObjectId() {
        return player.getObjectId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public Player getObject() {
        return player;
    }

    public long getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void updateLastOnlineTime() {
        lastOnlineTime = System.currentTimeMillis();
    }

    public boolean isOnline() {
        return player.isOnline();
    }

    public float getX() {
        return player.getX();
    }

    public float getY() {
        return player.getY();
    }

    public float getZ() {
        return player.getZ();
    }

    public byte getHeading() {
        return player.getHeading();
    }

    public byte getLevel() {
        return player.getLevel();
    }
}

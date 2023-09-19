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
package com.aionemu.gameserver.model.team2.group;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class PlayerGroupStats implements Predicate<Player> {

    private final PlayerGroup group;
    private int minExpPlayerLevel;
    private int maxExpPlayerLevel;
    Player minLevelPlayer;
    Player maxLevelPlayer;

    PlayerGroupStats(PlayerGroup group) {
        this.group = group;
    }

    public void onAddPlayer(PlayerGroupMember member) {
        group.applyOnMembers(this);
        calculateExpLevels();
    }

    public void onRemovePlayer(PlayerGroupMember member) {
        group.applyOnMembers(this);
    }

    private void calculateExpLevels() {
        minExpPlayerLevel = minLevelPlayer.getLevel();
        maxExpPlayerLevel = maxLevelPlayer.getLevel();
        minLevelPlayer = null;
        maxLevelPlayer = null;
    }

    @Override
    public boolean apply(Player player) {
        if (minLevelPlayer == null || maxLevelPlayer == null) {
            minLevelPlayer = player;
            maxLevelPlayer = player;
        } else {
            if (player.getCommonData().getExp() < minLevelPlayer.getCommonData().getExp()) {
                minLevelPlayer = player;
            }
            if (!player.isMentor() && player.getCommonData().getExp() > maxLevelPlayer.getCommonData().getExp()) {
                maxLevelPlayer = player;
            }
        }
        return true;
    }

    public int getMinExpPlayerLevel() {
        return minExpPlayerLevel;
    }

    public int getMaxExpPlayerLevel() {
        return maxExpPlayerLevel;
    }
}

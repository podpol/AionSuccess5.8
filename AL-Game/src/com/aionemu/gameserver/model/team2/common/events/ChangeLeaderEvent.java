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
package com.aionemu.gameserver.model.team2.common.events;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.aionemu.gameserver.model.team2.group.events.ChangeGroupLeaderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ATracer
 */
public abstract class ChangeLeaderEvent<T extends TemporaryPlayerTeam<?>> extends AbstractTeamPlayerEvent<T> {

    private static final Logger log = LoggerFactory.getLogger(ChangeGroupLeaderEvent.class);

    public ChangeLeaderEvent(T team, Player eventPlayer) {
        super(team, eventPlayer);
    }

    /**
     * New leader either is null or should be online
     */
    @Override
    public boolean checkCondition() {
        return eventPlayer == null || eventPlayer.isOnline();
    }

    @Override
    public boolean apply(Player player) {
        if (!player.getObjectId().equals(team.getLeader().getObjectId()) && player.isOnline()) {
            changeLeaderTo(player);
            return false;
        }
        return true;
    }

    /**
     * @param oldLeader
     */
    protected void checkLeaderChanged(Player oldLeader) {
        if (team.isLeader(oldLeader)) {
            log.info("TEAM2: leader is not changed, total: {}, online: {}", team.size(), team.onlineMembers());
        }
    }

    protected abstract void changeLeaderTo(Player player);
}

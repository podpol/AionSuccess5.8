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
import com.aionemu.gameserver.model.team2.TeamEvent;
import com.aionemu.gameserver.model.team2.TeamMember;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.google.common.base.Predicate;
import org.apache.commons.lang.StringUtils;

public abstract class PlayerLeavedEvent<TM extends TeamMember<Player>, T extends TemporaryPlayerTeam<TM>> implements Predicate<TM>, TeamEvent
{
    public static enum LeaveReson {
        BAN,
        LEAVE,
        LEAVE_TIMEOUT,
        DISBAND;
    }
	
    protected final T team;
    protected final Player leavedPlayer;
    protected final LeaveReson reason;
    protected final TM leavedTeamMember;
    protected final String banPersonName;
	
    public PlayerLeavedEvent(T alliance, Player player) {
        this(alliance, player, LeaveReson.LEAVE);
    }
	
    public PlayerLeavedEvent(T alliance, Player player, LeaveReson reason) {
        this(alliance, player, reason, StringUtils.EMPTY);
    }
	
    public PlayerLeavedEvent(T team, Player player, LeaveReson reason, String banPersonName) {
        this.team = team;
        this.leavedPlayer = player;
        this.reason = reason;
        this.leavedTeamMember = team.getMember(player.getObjectId());
        this.banPersonName = banPersonName;
    }
	
    @Override
    public boolean checkCondition() {
        return team.hasMember(leavedPlayer.getObjectId());
    }
}
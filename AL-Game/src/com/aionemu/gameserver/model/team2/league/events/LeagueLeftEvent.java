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
package com.aionemu.gameserver.model.team2.league.events;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.common.events.AlwaysTrueTeamEvent;
import com.aionemu.gameserver.model.team2.league.League;
import com.aionemu.gameserver.model.team2.league.LeagueMember;
import com.aionemu.gameserver.model.team2.league.LeagueService;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_INFO;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Predicate;

public class LeagueLeftEvent extends AlwaysTrueTeamEvent implements Predicate<LeagueMember>
{
    private final League league;
    private final PlayerAlliance alliance;
    private final LeaveReson reason;
	
    public static enum LeaveReson {
        LEAVE,
        EXPEL,
        DISBAND;
    }
	
    public LeagueLeftEvent(League league, PlayerAlliance alliance) {
        this(league, alliance, LeaveReson.LEAVE);
    }
	
    public LeagueLeftEvent(League league, PlayerAlliance alliance, LeaveReson reason) {
        this.league = league;
        this.alliance = alliance;
        this.reason = reason;
    }
	
    @Override
    public void handleEvent() {
        league.removeMember(alliance.getTeamId());
        league.apply(this);
        switch (reason) {
            case LEAVE:
                alliance.sendPacket(new SM_ALLIANCE_INFO(alliance));
                checkDisband();
            break;
            case EXPEL:
                alliance.sendPacket(new SM_ALLIANCE_INFO(alliance, SM_ALLIANCE_INFO.UNION_BAN_ME, league.getLeaderObject().getLeader().getName()));
                checkDisband();
            break;
            case DISBAND:
                alliance.sendPacket(new SM_ALLIANCE_INFO(alliance));
            break;
        }
    }
	
    private final void checkDisband() {
        if (league.onlineMembers() <= 1) {
            LeagueService.disband(league);
        }
    }
	
    @Override
    public boolean apply(LeagueMember member) {
        PlayerAlliance leagueAlliance = member.getObject();
        leagueAlliance.applyOnMembers(new Predicate<Player>() {
            @Override
            public boolean apply(Player member) {
                switch (reason) {
                    case LEAVE:
                        PacketSendUtility.sendPacket(member, new SM_ALLIANCE_INFO(alliance, SM_ALLIANCE_INFO.UNION_LEAVE, alliance.getLeader().getName()));
                    break;
                    case EXPEL:
                        PacketSendUtility.sendPacket(member, new SM_ALLIANCE_INFO(alliance, SM_ALLIANCE_INFO.UNION_BAN_HIM, alliance.getLeader().getName()));
                    break;
                }
                return true;
            }
        });
        return true;
    }
}
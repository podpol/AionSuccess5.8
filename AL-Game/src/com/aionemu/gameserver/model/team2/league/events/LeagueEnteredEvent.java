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

import com.aionemu.gameserver.model.team2.TeamEvent;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.league.League;
import com.aionemu.gameserver.model.team2.league.LeagueMember;
import com.aionemu.gameserver.model.team2.league.LeagueService;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SHOW_BRAND;
import com.google.common.base.Predicate;

public class LeagueEnteredEvent implements Predicate<LeagueMember>, TeamEvent
{
    private final League league;
    private final PlayerAlliance invitedAlliance;
	
    public LeagueEnteredEvent(League league, PlayerAlliance alliance) {
        this.league = league;
        this.invitedAlliance = alliance;
    }
	
    @Override
    public boolean checkCondition() {
        return !league.hasMember(invitedAlliance.getObjectId());
    }
	
    @Override
    public void handleEvent() {
        LeagueService.addAllianceToLeague(league, invitedAlliance);
        league.apply(this);
    }
	
    @Override
    public boolean apply(LeagueMember member) {
        PlayerAlliance alliance = member.getObject();
        alliance.sendPacket(new SM_ALLIANCE_INFO(alliance, SM_ALLIANCE_INFO.UNION_ENTER, league.getLeaderObject().getLeader().getName()));
        alliance.sendPacket(new SM_SHOW_BRAND(0, 0));
        return true;
    }
}
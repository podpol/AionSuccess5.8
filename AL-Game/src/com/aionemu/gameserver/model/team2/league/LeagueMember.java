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
package com.aionemu.gameserver.model.team2.league;

import com.aionemu.gameserver.model.team2.TeamMember;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;

/**
 * @author ATracer
 */
public class LeagueMember implements TeamMember<PlayerAlliance>
{
    private final PlayerAlliance alliance;
    private int leaguePosition;
	
    public LeagueMember(PlayerAlliance alliance, int position) {
        this.alliance = alliance;
        this.leaguePosition = position;
    }
	
    @Override
    public Integer getObjectId() {
        return alliance.getObjectId();
    }
	
    @Override
    public String getName() {
        return alliance.getName();
    }
	
    @Override
    public PlayerAlliance getObject() {
        return alliance;
    }
	
    public final int getLeaguePosition() {
        return leaguePosition;
    }
}
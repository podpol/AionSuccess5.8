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
package com.aionemu.gameserver.model.team2.alliance;

import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;

/**
 * @author ATracer
 */
public class PlayerAllianceGroup extends TemporaryPlayerTeam<PlayerAllianceMember> {

    private final PlayerAlliance alliance;

    public PlayerAllianceGroup(PlayerAlliance alliance, Integer objId) {
        super(objId);
        this.alliance = alliance;
    }

    @Override
    public void addMember(PlayerAllianceMember member) {
        super.addMember(member);
        member.setPlayerAllianceGroup(this);
        member.setAllianceId(getTeamId());
    }

    @Override
    public void removeMember(PlayerAllianceMember member) {
        super.removeMember(member);
        member.setPlayerAllianceGroup(null);
    }

    @Override
    public boolean isFull() {
        return size() == 6;
    }

    @Override
    public int getMinExpPlayerLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMaxExpPlayerLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    public PlayerAlliance getAlliance() {
        return alliance;
    }
}

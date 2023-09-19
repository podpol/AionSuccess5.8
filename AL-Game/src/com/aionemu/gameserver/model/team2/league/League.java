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

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.GeneralTeam;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceMember;
import com.aionemu.gameserver.model.team2.common.legacy.LootGroupRules;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author ATracer
 */
public class League extends GeneralTeam<PlayerAlliance, LeagueMember>
{
    private LootGroupRules lootGroupRules = new LootGroupRules();
    private static final LeagueMemberComparator MEMBER_COMPARATOR = new LeagueMemberComparator();
	
    public League(LeagueMember leader) {
        super(IDFactory.getInstance().nextId());
        initializeTeam(leader);
    }
	
    protected final void initializeTeam(LeagueMember leader) {
        setLeader(leader);
    }
	
    @Override
    public Collection<PlayerAlliance> getOnlineMembers() {
        return getMembers();
    }
	
    @Override
    public void addMember(LeagueMember member) {
        super.addMember(member);
        member.getObject().setLeague(this);
    }
	
    @Override
    public void removeMember(LeagueMember member) {
        super.removeMember(member);
        member.getObject().setLeague(null);
    }
	
    @Override
    public void sendPacket(AionServerPacket packet) {
        for (PlayerAlliance alliance : getMembers()) {
            alliance.sendPacket(packet);
        }
    }
	
    @Override
    public void sendPacket(AionServerPacket packet, Predicate<PlayerAlliance> predicate) {
        for (PlayerAlliance alliance : getMembers()) {
            if (predicate.apply(alliance)) {
                alliance.sendPacket(packet, Predicates.<Player>alwaysTrue());
            }
        }
    }
	
    @Override
    public int onlineMembers() {
        return getMembers().size();
    }
	
    @Override
    public Race getRace() {
        return getLeaderObject().getRace();
    }
	
    @Override
    public boolean isFull() {
        return size() == 8;
    }
	
    public LootGroupRules getLootGroupRules() {
        return lootGroupRules;
    }
	
    public void setLootGroupRules(LootGroupRules lootGroupRules) {
        this.lootGroupRules = lootGroupRules;
    }
	
    public Collection<LeagueMember> getSortedMembers() {
        ArrayList<LeagueMember> newArrayList = Lists.newArrayList(members.values());
        Collections.sort(newArrayList, MEMBER_COMPARATOR);
        return newArrayList;
    }
	
    public Player getPlayerMember(Integer playerObjId) {
        for (PlayerAlliance member : getMembers()) {
            PlayerAllianceMember playerMember = member.getMember(playerObjId);
            if (playerMember != null) {
                return playerMember.getObject();
            }
        }
        return null;
    }
	
    static class LeagueMemberComparator implements Comparator<LeagueMember> {
        @Override
        public int compare(LeagueMember o1, LeagueMember o2) {
            return o1.getLeaguePosition() > o2.getLeaguePosition() ? 1 : -1;
        }
    }
}
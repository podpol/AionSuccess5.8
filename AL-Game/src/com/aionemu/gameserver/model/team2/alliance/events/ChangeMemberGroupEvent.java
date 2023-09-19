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
package com.aionemu.gameserver.model.team2.alliance.events;

import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceGroup;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceMember;
import com.aionemu.gameserver.model.team2.common.events.AlwaysTrueTeamEvent;
import com.aionemu.gameserver.model.team2.common.legacy.PlayerAllianceEvent;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_MEMBER_INFO;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class ChangeMemberGroupEvent extends AlwaysTrueTeamEvent implements Predicate<PlayerAllianceMember> {

    private final PlayerAlliance alliance;
    private final int firstMemberId;
    private final int secondMemberId;
    private final int allianceGroupId;
    private PlayerAllianceMember firstMember;
    private PlayerAllianceMember secondMember;

    public ChangeMemberGroupEvent(PlayerAlliance alliance, int firstMemberId, int secondMemberId, int allianceGroupId) {
        this.alliance = alliance;
        this.firstMemberId = firstMemberId;
        this.secondMemberId = secondMemberId;
        this.allianceGroupId = allianceGroupId;
    }

    @Override
    public void handleEvent() {
        firstMember = alliance.getMember(firstMemberId);
        secondMember = alliance.getMember(secondMemberId);
        Preconditions.checkNotNull(firstMember, "First member should not be null");
        Preconditions.checkArgument(secondMemberId == 0 || secondMember != null, "Second member should not be null");
        if (secondMember != null) {
            swapMembersInGroup(firstMember, secondMember);
        } else {
            moveMemberToGroup(firstMember, allianceGroupId);
        }
        alliance.apply(this);
    }

    @Override
    public boolean apply(PlayerAllianceMember member) {
        PacketSendUtility.sendPacket(member.getObject(), new SM_ALLIANCE_MEMBER_INFO(firstMember,
                PlayerAllianceEvent.MEMBER_GROUP_CHANGE));
        if (secondMember != null) {
            PacketSendUtility.sendPacket(member.getObject(), new SM_ALLIANCE_MEMBER_INFO(secondMember,
                    PlayerAllianceEvent.MEMBER_GROUP_CHANGE));
        }
        return true;
    }

    private void swapMembersInGroup(PlayerAllianceMember firstMember, PlayerAllianceMember secondMember) {
        PlayerAllianceGroup firstAllianceGroup = firstMember.getPlayerAllianceGroup();
        PlayerAllianceGroup secondAllianceGroup = secondMember.getPlayerAllianceGroup();
        firstAllianceGroup.removeMember(firstMember);
        secondAllianceGroup.removeMember(secondMember);
        firstAllianceGroup.addMember(secondMember);
        secondAllianceGroup.addMember(firstMember);
    }

    private void moveMemberToGroup(PlayerAllianceMember firstMember, int allianceGroupId) {
        PlayerAllianceGroup firstAllianceGroup = firstMember.getPlayerAllianceGroup();
        firstAllianceGroup.removeMember(firstMember);
        PlayerAllianceGroup newAllianceGroup = alliance.getAllianceGroup(allianceGroupId);
        newAllianceGroup.addMember(firstMember);
    }
}

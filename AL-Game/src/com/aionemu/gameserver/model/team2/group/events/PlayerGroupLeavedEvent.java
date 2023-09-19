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
package com.aionemu.gameserver.model.team2.group.events;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.common.events.PlayerLeavedEvent;
import com.aionemu.gameserver.model.team2.common.legacy.GroupEvent;
import com.aionemu.gameserver.model.team2.group.PlayerGroup;
import com.aionemu.gameserver.model.team2.group.PlayerGroupMember;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.network.aion.serverpackets.SM_GROUP_MEMBER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LEAVE_GROUP_MEMBER;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/**
 * @author ATracer
 */
public class PlayerGroupLeavedEvent extends PlayerLeavedEvent<PlayerGroupMember, PlayerGroup> {

    public PlayerGroupLeavedEvent(PlayerGroup alliance, Player player) {
        super(alliance, player);
    }

    public PlayerGroupLeavedEvent(PlayerGroup team, Player player, PlayerLeavedEvent.LeaveReson reason,
                                  String banPersonName) {
        super(team, player, reason, banPersonName);
    }

    public PlayerGroupLeavedEvent(PlayerGroup alliance, Player player, PlayerLeavedEvent.LeaveReson reason) {
        super(alliance, player, reason);
    }

    @Override
    public void handleEvent() {
        team.removeMember(leavedPlayer.getObjectId());

        if (leavedPlayer.isMentor()) {
            team.onEvent(new PlayerGroupStopMentoringEvent(team, leavedPlayer));
        }

        team.apply(this);

        PacketSendUtility.sendPacket(leavedPlayer, new SM_LEAVE_GROUP_MEMBER());
        switch (reason) {
            case BAN:
            case LEAVE:
                // PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_PARTY_SECEDE); // client side?
                if (team.onlineMembers() <= 1) {
                    PlayerGroupService.disband(team);
                } else {
                    if (leavedPlayer.equals(team.getLeader().getObject())) {
                        team.onEvent(new ChangeGroupLeaderEvent(team));
                    }
                }
                if (reason == LeaveReson.BAN) {
                    PacketSendUtility.sendPacket(leavedPlayer, SM_SYSTEM_MESSAGE.STR_PARTY_YOU_ARE_BANISHED);
                }
                break;
            case DISBAND:
                PacketSendUtility.sendPacket(leavedPlayer, SM_SYSTEM_MESSAGE.STR_PARTY_IS_DISPERSED);
                break;
        }

        if (leavedPlayer.isInInstance()) {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    if (!leavedPlayer.isInGroup2()) {
                        if (leavedPlayer.getPosition().getWorldMapInstance().getRegisteredGroup() != null) {
                            InstanceService.moveToExitPoint(leavedPlayer);
                        }
                    }
                }
            }, 10000);
        }
    }

    @Override
    public boolean apply(PlayerGroupMember member) {
        Player player = member.getObject();
        PacketSendUtility.sendPacket(player, new SM_GROUP_MEMBER_INFO(team, leavedPlayer, GroupEvent.LEAVE));

        switch (reason) {
            case LEAVE:
            case DISBAND:
                PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_PARTY_HE_LEAVE_PARTY(leavedPlayer.getName()));
                break;
            case BAN:
                // TODO find out empty strings (Retail has +2 empty strings
                PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_PARTY_HE_IS_BANISHED(leavedPlayer.getName()));
                break;
        }

        return true;
    }
}

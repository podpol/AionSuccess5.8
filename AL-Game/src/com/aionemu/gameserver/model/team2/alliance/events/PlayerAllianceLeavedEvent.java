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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceMember;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceService;
import com.aionemu.gameserver.model.team2.common.events.PlayerLeavedEvent;
import com.aionemu.gameserver.model.team2.common.legacy.PlayerAllianceEvent;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_MEMBER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LEAVE_GROUP_MEMBER;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldMapInstance;

/**
 * @author ATracer
 */
public class PlayerAllianceLeavedEvent extends PlayerLeavedEvent<PlayerAllianceMember, PlayerAlliance> {

    public PlayerAllianceLeavedEvent(PlayerAlliance alliance, Player player) {
        super(alliance, player);
    }

    public PlayerAllianceLeavedEvent(PlayerAlliance team, Player player, PlayerLeavedEvent.LeaveReson reason,
                                     String banPersonName) {
        super(team, player, reason, banPersonName);
    }

    public PlayerAllianceLeavedEvent(PlayerAlliance alliance, Player player, PlayerLeavedEvent.LeaveReson reason) {
        super(alliance, player, reason);
    }

    @Override
    public void handleEvent() {
        team.removeMember(leavedPlayer.getObjectId());
        team.getViceCaptainIds().remove(leavedPlayer.getObjectId());

        if (leavedPlayer.isOnline()) {
            PacketSendUtility.sendPacket(leavedPlayer, new SM_LEAVE_GROUP_MEMBER());
        }

        team.apply(this);

        switch (reason) {
            case BAN:
            case LEAVE:
            case LEAVE_TIMEOUT:
                if (team.onlineMembers() <= 1) {
                    PlayerAllianceService.disband(team);
                } else {
                    if (leavedPlayer.equals(team.getLeader().getObject())) {
                        team.onEvent(new ChangeAllianceLeaderEvent(team));
                    }
                }
                if (reason == LeaveReson.BAN) {
                    PacketSendUtility.sendPacket(leavedPlayer, SM_SYSTEM_MESSAGE.STR_FORCE_BAN_ME(banPersonName));
                }

                break;
            case DISBAND:
                PacketSendUtility.sendPacket(leavedPlayer, SM_SYSTEM_MESSAGE.STR_PARTY_ALLIANCE_DISPERSED);
                break;
        }

        if (leavedPlayer.isInInstance()) {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    if (!leavedPlayer.isInAlliance2()) {
                        WorldMapInstance instance = leavedPlayer.getPosition().getWorldMapInstance();
                        if (instance.getRegistredAlliance() != null || instance.getRegistredLeague() != null) {
                            InstanceService.moveToExitPoint(leavedPlayer);
                        }
                    }
                }
            }, 10000);
        }
    }

    @Override
    public boolean apply(PlayerAllianceMember member) {
        Player player = member.getObject();

        PacketSendUtility.sendPacket(player, new SM_ALLIANCE_MEMBER_INFO(leavedTeamMember, PlayerAllianceEvent.LEAVE));
        PacketSendUtility.sendPacket(player, new SM_ALLIANCE_INFO(team));

        switch (reason) {
            case LEAVE_TIMEOUT:
                PacketSendUtility.sendPacket(player,
                        SM_SYSTEM_MESSAGE.STR_PARTY_ALLIANCE_HE_LEAVED_PARTY(leavedPlayer.getName()));
                break;
            case LEAVE:
                PacketSendUtility.sendPacket(player,
                        SM_SYSTEM_MESSAGE.STR_PARTY_ALLIANCE_HE_LEAVED_PARTY(leavedPlayer.getName()));
                break;
            case DISBAND:
                PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_PARTY_ALLIANCE_DISPERSED);
                break;
            case BAN:
                PacketSendUtility
                        .sendPacket(player, SM_SYSTEM_MESSAGE.STR_FORCE_BAN_HIM(banPersonName, leavedPlayer.getName()));
                break;
        }

        return true;
    }
}

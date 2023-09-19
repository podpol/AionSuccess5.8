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
import com.aionemu.gameserver.model.team2.common.events.AlwaysTrueTeamEvent;
import com.aionemu.gameserver.model.team2.common.legacy.PlayerAllianceEvent;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_MEMBER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_INSTANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SHOW_BRAND;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class PlayerConnectedEvent extends AlwaysTrueTeamEvent implements Predicate<PlayerAllianceMember> {

    private final PlayerAlliance alliance;
    private final Player connected;
    private PlayerAllianceMember connectedMember;

    public PlayerConnectedEvent(PlayerAlliance alliance, Player player) {
        this.alliance = alliance;
        this.connected = player;
    }

    @Override
    public void handleEvent() {
        alliance.removeMember(connected.getObjectId());
        connectedMember = new PlayerAllianceMember(connected);
        alliance.addMember(connectedMember);

        PacketSendUtility.sendPacket(connected, new SM_ALLIANCE_INFO(alliance));
        PacketSendUtility
                .sendPacket(connected, new SM_ALLIANCE_MEMBER_INFO(connectedMember, PlayerAllianceEvent.RECONNECT));
        PacketSendUtility.sendPacket(connected, new SM_SHOW_BRAND(0, 0));

        alliance.apply(this);
    }

    @Override
    public boolean apply(PlayerAllianceMember member) {
        Player player = member.getObject();
        if (!connected.getObjectId().equals(player.getObjectId())) {
            PacketSendUtility.sendPacket(player, new SM_ALLIANCE_MEMBER_INFO(connectedMember, PlayerAllianceEvent.RECONNECT));
            PacketSendUtility.sendPacket(player, new SM_INSTANCE_INFO(connected, false, alliance));

            PacketSendUtility.sendPacket(connected, new SM_ALLIANCE_MEMBER_INFO(member, PlayerAllianceEvent.RECONNECT));
        }
        return true;
    }
}

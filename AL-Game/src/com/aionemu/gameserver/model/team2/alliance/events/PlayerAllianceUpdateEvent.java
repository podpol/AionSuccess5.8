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
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_MEMBER_INFO;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class PlayerAllianceUpdateEvent extends AlwaysTrueTeamEvent implements Predicate<PlayerAllianceMember> {

    private final PlayerAlliance alliance;
    private final Player player;
    private final PlayerAllianceEvent allianceEvent;
    private final PlayerAllianceMember updateMember;

    public PlayerAllianceUpdateEvent(PlayerAlliance alliance, Player player, PlayerAllianceEvent allianceEvent) {
        this.alliance = alliance;
        this.player = player;
        this.allianceEvent = allianceEvent;
        this.updateMember = alliance.getMember(player.getObjectId());
    }

    @Override
    public void handleEvent() {
        switch (allianceEvent) {
            case MOVEMENT:
            case UPDATE:
                alliance.apply(this);
                break;
            default:
                // Unsupported
                break;
        }

    }

    @Override
    public boolean apply(PlayerAllianceMember member) {
        if (!member.getObjectId().equals(player.getObjectId())) {
            PacketSendUtility.sendPacket(member.getObject(), new SM_ALLIANCE_MEMBER_INFO(updateMember, allianceEvent));
        }
        return true;
    }
}

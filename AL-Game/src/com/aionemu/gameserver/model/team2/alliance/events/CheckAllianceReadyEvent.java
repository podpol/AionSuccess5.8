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
import com.aionemu.gameserver.model.team2.common.events.AlwaysTrueTeamEvent;
import com.aionemu.gameserver.model.team2.common.events.TeamCommand;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_READY_CHECK;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class CheckAllianceReadyEvent extends AlwaysTrueTeamEvent implements Predicate<Player> {

    private final PlayerAlliance alliance;
    private final Player player;
    private final TeamCommand eventCode;

    public CheckAllianceReadyEvent(PlayerAlliance alliance, Player player, TeamCommand eventCode) {
        this.alliance = alliance;
        this.player = player;
        this.eventCode = eventCode;
    }

    @Override
    public void handleEvent() {
        int readyStatus = alliance.getAllianceReadyStatus();
        switch (eventCode) {
            case ALLIANCE_CHECKREADY_CANCEL:
                readyStatus = 0;
                break;
            case ALLIANCE_CHECKREADY_START:
                readyStatus = alliance.onlineMembers() - 1;
                break;
            case ALLIANCE_CHECKREADY_AUTOCANCEL:
                readyStatus = 0;
                break;
            case ALLIANCE_CHECKREADY_READY:
            case ALLIANCE_CHECKREADY_NOTREADY:
                readyStatus -= 1;
                break;
        }
        alliance.setAllianceReadyStatus(readyStatus);
        alliance.applyOnMembers(this);
    }

    @Override
    public boolean apply(Player member) {
        switch (eventCode) {
            case ALLIANCE_CHECKREADY_CANCEL:
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 0));
                break;
            case ALLIANCE_CHECKREADY_START:
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 5));
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 1));
                break;
            case ALLIANCE_CHECKREADY_AUTOCANCEL:
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 2));
                break;
            case ALLIANCE_CHECKREADY_READY:
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 5));
                if (alliance.getAllianceReadyStatus() == 0) {
                    PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(0, 3));
                }
                break;
            case ALLIANCE_CHECKREADY_NOTREADY:
                PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(player.getObjectId(), 4));
                if (alliance.getAllianceReadyStatus() == 0) {
                    PacketSendUtility.sendPacket(member, new SM_ALLIANCE_READY_CHECK(0, 3));
                }
                break;
        }
        return true;
    }
}

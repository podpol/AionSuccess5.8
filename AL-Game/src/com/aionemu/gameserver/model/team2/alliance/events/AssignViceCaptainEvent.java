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
import com.aionemu.gameserver.model.team2.common.events.AbstractTeamPlayerEvent;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ALLIANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class AssignViceCaptainEvent extends AbstractTeamPlayerEvent<PlayerAlliance>
{
    public static enum AssignType {
        PROMOTE,
        DEMOTE_CAPTAIN_TO_VICECAPTAIN,
        DEMOTE
    }
	
    private final AssignType assignType;
	
    public AssignViceCaptainEvent(PlayerAlliance team, Player eventPlayer, AssignType assignType) {
        super(team, eventPlayer);
        this.assignType = assignType;
    }
	
    @Override
    public boolean checkCondition() {
        return eventPlayer != null && eventPlayer.isOnline();
    }
	
    @Override
    public void handleEvent() {
        switch (assignType) {
            case DEMOTE:
                team.getViceCaptainIds().remove(eventPlayer.getObjectId());
            break;
            case PROMOTE:
                if (team.getViceCaptainIds().size() == 4) {
                    PacketSendUtility.sendPacket(team.getLeaderObject(), SM_SYSTEM_MESSAGE.STR_FORCE_CANNOT_PROMOTE_MANAGER);
                    return;
                }
                team.getViceCaptainIds().add(eventPlayer.getObjectId());
            break;
            case DEMOTE_CAPTAIN_TO_VICECAPTAIN:
                team.getViceCaptainIds().add(eventPlayer.getObjectId());
            break;
        }
        team.applyOnMembers(this);
    }
	
    @Override
    public boolean apply(Player player) {
        int messageId = 0;
        switch (assignType) {
            case PROMOTE:
                messageId = SM_ALLIANCE_INFO.FORCE_PROMOTE_MANAGER;
            break;
            case DEMOTE:
                messageId = SM_ALLIANCE_INFO.FORCE_DEMOTE_MANAGER;
            break;
        }
        PacketSendUtility.sendPacket(player, new SM_ALLIANCE_INFO(team, messageId, eventPlayer.getName()));
        return true;
    }
}
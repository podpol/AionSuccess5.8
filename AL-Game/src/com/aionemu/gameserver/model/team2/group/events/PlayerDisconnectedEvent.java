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
import com.aionemu.gameserver.model.team2.TeamEvent;
import com.aionemu.gameserver.model.team2.common.legacy.GroupEvent;
import com.aionemu.gameserver.model.team2.group.PlayerGroup;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.network.aion.serverpackets.SM_GROUP_MEMBER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class PlayerDisconnectedEvent implements Predicate<Player>, TeamEvent {

    private final PlayerGroup group;
    private final Player player;

    public PlayerDisconnectedEvent(PlayerGroup group, Player player) {
        this.group = group;
        this.player = player;
    }

    /**
     * Player should be in group before disconnection
     */
    @Override
    public boolean checkCondition() {
        return group.hasMember(player.getObjectId());
    }

    @Override
    public void handleEvent() {
        if (group.onlineMembers() <= 1) {
            PlayerGroupService.disband(group);
        } else {
            if (player.equals(group.getLeader().getObject())) {
                group.onEvent(new ChangeGroupLeaderEvent(group));
            }
            group.applyOnMembers(this);
        }
    }

    @Override
    public boolean apply(Player member) {
        if (!member.equals(player)) {
            PacketSendUtility.sendPacket(member, SM_SYSTEM_MESSAGE.STR_PARTY_HE_BECOME_OFFLINE(player.getName()));
            PacketSendUtility.sendPacket(member, new SM_GROUP_MEMBER_INFO(group, player, GroupEvent.DISCONNECTED));
            // disconnect other group members on logout? check
            PacketSendUtility.sendPacket(player, new SM_GROUP_MEMBER_INFO(group, member, GroupEvent.DISCONNECTED));
        }
        return true;
    }
}

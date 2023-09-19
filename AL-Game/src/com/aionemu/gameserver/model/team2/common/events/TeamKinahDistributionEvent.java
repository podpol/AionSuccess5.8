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
package com.aionemu.gameserver.model.team2.common.events;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.TeamMember;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author ATracer
 */
public class TeamKinahDistributionEvent<T extends TemporaryPlayerTeam<? extends TeamMember<Player>>> extends AbstractTeamPlayerEvent<T> {

    private final long amount;
    private long rewardPerPlayer;
    private long teamSize;

    public TeamKinahDistributionEvent(T team, Player distributor, long amount) {
        super(team, distributor);
        this.amount = amount;
    }

    @Override
    public boolean checkCondition() {
        return team.hasMember(eventPlayer.getObjectId());
    }

    @Override
    public void handleEvent() {
        if (eventPlayer.getInventory().getKinah() < amount) {
            // TODO retail message ?
            return;
        }

        teamSize = team.onlineMembers();
        if (teamSize <= amount) {
            rewardPerPlayer = amount / teamSize;
            team.applyOnMembers(this);
        }
    }

    @Override
    public boolean apply(Player member) {
        if (member.isOnline()) {
            if (member.equals(eventPlayer)) {
                member.getInventory().decreaseKinah(amount);
                member.getInventory().increaseKinah(rewardPerPlayer);
                PacketSendUtility.sendPacket(eventPlayer, new SM_SYSTEM_MESSAGE(1390247, amount, teamSize, rewardPerPlayer));
            } else {
                member.getInventory().increaseKinah(rewardPerPlayer);
                PacketSendUtility.sendPacket(member, new SM_SYSTEM_MESSAGE(1390248, eventPlayer.getName(), amount, teamSize,
                        rewardPerPlayer));
            }
        }
        return true;
    }
}

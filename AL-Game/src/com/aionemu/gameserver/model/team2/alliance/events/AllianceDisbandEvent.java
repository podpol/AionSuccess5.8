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
import com.aionemu.gameserver.model.team2.common.events.PlayerLeavedEvent.LeaveReson;
import com.google.common.base.Predicate;

public class AllianceDisbandEvent extends AlwaysTrueTeamEvent implements Predicate<Player>
{
    private final PlayerAlliance alliance;
	
    public AllianceDisbandEvent(PlayerAlliance alliance) {
        this.alliance = alliance;
    }
	
    @Override
    public void handleEvent() {
        alliance.applyOnMembers(this);
    }
	
    @Override
    public boolean apply(Player player) {
        alliance.onEvent(new PlayerAllianceLeavedEvent(alliance, player, LeaveReson.DISBAND));
        return true;
    }
}
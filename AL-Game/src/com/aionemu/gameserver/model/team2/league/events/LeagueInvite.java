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
package com.aionemu.gameserver.model.team2.league.events;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RequestResponseHandler;
import com.aionemu.gameserver.model.team2.league.League;
import com.aionemu.gameserver.model.team2.league.LeagueService;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class LeagueInvite extends RequestResponseHandler
{
    private final Player inviter;
    private final Player invited;
	
    public LeagueInvite(Player inviter, Player invited) {
        super(inviter);
        this.inviter = inviter;
        this.invited = invited;
    }
	
    @Override
    public void acceptRequest(Creature requester, Player responder) {
        if (LeagueService.canInvite(inviter, invited)) {
            //%0's Alliance has joined the League.
			PacketSendUtility.sendPacket(inviter, SM_SYSTEM_MESSAGE.STR_UNION_ENTER_HIM(invited.getName()));
			League league = inviter.getPlayerAlliance2().getLeague();
            if (league == null) {
                league = LeagueService.createLeague(inviter, invited);
            } else if (league.size() == 48) {
				PacketSendUtility.sendMessage(invited, "That league is already full.");
				PacketSendUtility.sendMessage(inviter, "Your league is already full.");
				return;
			} else if (invited.isInAlliance2() && invited.getPlayerAlliance2().size() + league.size() > 48) {
                PacketSendUtility.sendMessage(invited, "That league is now too full for your group to join.");
                PacketSendUtility.sendMessage(inviter, "Your league is now too full for that group to join.");
                return;
            } if (!invited.isInLeague()) {
                LeagueService.addAlliance(league, invited.getPlayerAlliance2());
            }
        }
    }
	
    @Override
    public void denyRequest(Creature requester, Player responder) {
        //%0's Alliance has declined your invitation to join the League.
		PacketSendUtility.sendPacket(inviter, SM_SYSTEM_MESSAGE.STR_UNION_REJECT_HIM(responder.getName()));
    }
}
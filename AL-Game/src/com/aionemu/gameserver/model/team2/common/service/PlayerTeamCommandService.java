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
package com.aionemu.gameserver.model.team2.common.service;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.TeamMember;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceService;
import com.aionemu.gameserver.model.team2.alliance.events.AssignViceCaptainEvent.AssignType;
import com.aionemu.gameserver.model.team2.common.events.TeamCommand;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.model.team2.league.LeagueMember;
import com.aionemu.gameserver.model.team2.league.LeagueService;
import com.google.common.base.Preconditions;

/**
 * @author ATracer
 */
public class PlayerTeamCommandService {

    public static final void executeCommand(Player player, TeamCommand command, int playerObjId) {
        Player teamSubjective = getTeamSubjective(player, playerObjId);
        Preconditions.checkArgument(playerObjId == 0 || teamSubjective.getObjectId().equals(playerObjId) || command == TeamCommand.LEAGUE_EXPEL, "Wrong command detected " + command);
        execute(player, command, teamSubjective);
    }

    private static final void execute(Player player, TeamCommand eventCode, Player teamSubjective) {
        switch (eventCode) {
            case GROUP_BAN_MEMBER:
                PlayerGroupService.banPlayer(teamSubjective, player);
            break;
            case GROUP_SET_LEADER:
                PlayerGroupService.changeLeader(teamSubjective);
            break;
            case GROUP_REMOVE_MEMBER:
                PlayerGroupService.removePlayer(teamSubjective);
            break;
            case GROUP_START_MENTORING:
                PlayerGroupService.startMentoring(player);
            break;
            case GROUP_END_MENTORING:
                PlayerGroupService.stopMentoring(player);
            break;
            case ALLIANCE_LEAVE:
                PlayerAllianceService.removePlayer(player);
            break;
            case ALLIANCE_BAN_MEMBER:
                PlayerAllianceService.banPlayer(teamSubjective, player);
            break;
            case ALLIANCE_SET_CAPTAIN:
                PlayerAllianceService.changeLeader(teamSubjective);
            break;
            case ALLIANCE_CHECKREADY_CANCEL:
            case ALLIANCE_CHECKREADY_START:
            case ALLIANCE_CHECKREADY_AUTOCANCEL:
            case ALLIANCE_CHECKREADY_NOTREADY:
            case ALLIANCE_CHECKREADY_READY:
                PlayerAllianceService.checkReady(player, eventCode);
            break;
            case ALLIANCE_SET_VICECAPTAIN:
                PlayerAllianceService.changeViceCaptain(teamSubjective, AssignType.PROMOTE);
            break;
            case ALLIANCE_UNSET_VICECAPTAIN:
                PlayerAllianceService.changeViceCaptain(teamSubjective, AssignType.DEMOTE);
            break;
            case LEAGUE_LEAVE:
                LeagueService.removeAlliance(player.getPlayerAlliance2());
            break;
            case LEAGUE_EXPEL:
                LeagueService.expelAlliance(teamSubjective, player);
            break;
        }
    }
	
    private static final Player getTeamSubjective(Player player, int playerObjId) {
        if (playerObjId == 0) {
            return player;
        } if (player.isInTeam()) {
            TeamMember<Player> member = player.getCurrentTeam().getMember(playerObjId);
            if (member != null) {
                return member.getObject();
            } if (player.isInLeague()) {
                LeagueMember subjective = player.getPlayerAlliance2().getLeague().getMember(playerObjId);
                if (subjective != null) {
                    return subjective.getObject().getLeaderObject();
                }
            }
        }
        return player;
    }
}
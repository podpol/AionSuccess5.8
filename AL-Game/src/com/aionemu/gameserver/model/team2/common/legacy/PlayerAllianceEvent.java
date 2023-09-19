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
package com.aionemu.gameserver.model.team2.common.legacy;

/**
 * @author Sarynth
 */
public enum PlayerAllianceEvent
{
    LEAVE(0),
    LEAVE_TIMEOUT(0),
    BANNED(0),
    MOVEMENT(1),
    DISCONNECTED(3),
    JOIN(5),
    ENTER_OFFLINE(7),
    UNK(9),
    RECONNECT(13),
    ENTER(13),
    UPDATE(13),
    MEMBER_GROUP_CHANGE(5),
    APPOINT_VICE_CAPTAIN(13),
    DEMOTE_VICE_CAPTAIN(13),
    APPOINT_CAPTAIN(13);
	
    private int id;
	
    private PlayerAllianceEvent(int id) {
        this.id = id;
    }
	
    public int getId() {
        return this.id;
    }
}
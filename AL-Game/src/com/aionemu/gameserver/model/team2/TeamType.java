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
package com.aionemu.gameserver.model.team2;

public enum TeamType
{
    GROUP(0x3F, 0),
    AUTO_GROUP(0x02, 1),
    ALLIANCE(0x3F, 0),
    ALLIANCE_DEFENCE(0x3F, 4),
    ALLIANCE_OFFENCE(0x02, 3);
	
    private int type;
    private int subType;
	
    private TeamType(int type, int subType) {
        this.type = type;
        this.subType = subType;
    }
	
    public int getType() {
        return type;
    }
	
    public int getSubType() {
        return subType;
    }
	
    public boolean isAutoTeam() {
        return this.getType() == 0x02;
    }
	
    public boolean isOffence() {
        return this.getSubType() == 3;
    }
	
    public boolean isDefence() {
        return this.getSubType() == 4;
    }
}
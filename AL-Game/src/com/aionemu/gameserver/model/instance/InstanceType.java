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
package com.aionemu.gameserver.model.instance;

public enum InstanceType
{
    LF1,
	SCORE,
	ARENA,
	NORMAL,
	INVASION,
	DREADGION,
	ARENA_PVP,
	TOURNAMENT,
	ARENA_TEAM,
	TIME_ATTACK,
    BATTLEFIELD;
	
    public boolean isDarkPoetaInstance() {
        return this.equals(InstanceType.LF1);
    }
	public boolean isScoreInstance() {
        return this.equals(InstanceType.SCORE);
    }
	public boolean isArenaInstance() {
        return this.equals(InstanceType.ARENA);
    }
	public boolean isNormalInstance() {
        return this.equals(InstanceType.NORMAL);
    }
	public boolean isInvasionInstance() {
        return this.equals(InstanceType.INVASION);
    }
	public boolean isDreadgionInstance() {
        return this.equals(InstanceType.DREADGION);
    }
	public boolean isArenaPvPInstance() {
        return this.equals(InstanceType.ARENA_PVP);
    }
	public boolean isTournamentInstance() {
        return this.equals(InstanceType.TOURNAMENT);
    }
	public boolean isArenaTeamInstance() {
        return this.equals(InstanceType.ARENA_TEAM);
    }
	public boolean isTimeAttackInstance() {
        return this.equals(InstanceType.TIME_ATTACK);
    }
    public boolean isBattlefieldInstance() {
        return this.equals(InstanceType.BATTLEFIELD);
    }
}
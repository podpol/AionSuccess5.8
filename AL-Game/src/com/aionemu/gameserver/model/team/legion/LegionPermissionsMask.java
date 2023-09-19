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
package com.aionemu.gameserver.model.team.legion;


/**
 * @author MrPoke
 *
 */
public enum LegionPermissionsMask {
	
	EDIT(0x200),
	INVITE(0x8),
	KICK(0x10),
	WH_WITHDRAWAL(0x4),
	WH_DEPOSIT(0x1000),
	ARTIFACT(0x400),
	GUARDIAN_STONE(0x800);
	
	private int rank;

	private LegionPermissionsMask(int rank) {
		this.rank =  rank;
	}
	
	public boolean can(int permission){
		return (rank & permission) != 0;
	}
}

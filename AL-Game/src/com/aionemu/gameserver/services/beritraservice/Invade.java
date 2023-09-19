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
package com.aionemu.gameserver.services.beritraservice;

import com.aionemu.gameserver.model.beritra.BeritraLocation;
import com.aionemu.gameserver.model.beritra.BeritraStateType;

/**
 * @author Rinzler (Encom)
 */

public class Invade extends BeritraInvasion<BeritraLocation>
{
	public Invade(BeritraLocation beritra) {
		super(beritra);
	}
	
	@Override
	public void startBeritraInvasion() {
		getBeritraLocation().setActiveBeritra(this);
		despawn();
		spawn(BeritraStateType.INVASION);
	}
	
	@Override
	public void stopBeritraInvasion() {
		getBeritraLocation().setActiveBeritra(null);
		despawn();
		spawn(BeritraStateType.PEACE);
	}
}
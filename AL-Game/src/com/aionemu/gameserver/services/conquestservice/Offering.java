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
package com.aionemu.gameserver.services.conquestservice;

import com.aionemu.gameserver.model.conquest.ConquestLocation;
import com.aionemu.gameserver.model.conquest.ConquestStateType;

/**
 * @author Rinzler (Encom)
 */

public class Offering extends ConquestOffering<ConquestLocation>
{
	public Offering(ConquestLocation conquest) {
		super(conquest);
	}
	
	@Override
	public void startConquest() {
		getConquestLocation().setActiveConquest(this);
		despawn();
		spawn(ConquestStateType.CONQUEST);
		initConquestBoss();
	}
	
	@Override
	public void stopConquest() {
		getConquestLocation().setActiveConquest(null);
		rmvConquestBossListener();
		despawn();
		spawn(ConquestStateType.PEACE);
	}
}
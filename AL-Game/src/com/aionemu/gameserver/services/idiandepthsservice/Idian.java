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
package com.aionemu.gameserver.services.idiandepthsservice;

import com.aionemu.gameserver.model.idiandepths.IdianDepthsLocation;
import com.aionemu.gameserver.model.idiandepths.IdianDepthsStateType;

/**
 * @author Rinzler (Encom)
 */

public class Idian extends IdianDepths<IdianDepthsLocation>
{
	public Idian(IdianDepthsLocation idianDepths) {
		super(idianDepths);
	}
	
	@Override
	public void startIdianDepths() {
		getIdianDepthsLocation().setActiveIdianDepths(this);
		despawn();
		spawn(IdianDepthsStateType.OPEN);
	}
	
	@Override
	public void stopIdianDepths() {
		getIdianDepthsLocation().setActiveIdianDepths(null);
		despawn();
		spawn(IdianDepthsStateType.CLOSED);
	}
}
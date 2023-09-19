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
package com.aionemu.gameserver.services.iuservice;

import com.aionemu.gameserver.model.iu.IuLocation;
import com.aionemu.gameserver.model.iu.IuStateType;

/**
 * @author Rinzler (Encom)
 */

public class CircusBound extends Iu<IuLocation>
{
	public CircusBound(IuLocation iu) {
		super(iu);
	}
	
	@Override
	public void startConcert() {
		getIuLocation().setActiveIu(this);
		despawn();
		spawn(IuStateType.OPEN);
	}
	
	@Override
	public void stopConcert() {
		getIuLocation().setActiveIu(null);
		despawn();
		spawn(IuStateType.CLOSED);
	}
}
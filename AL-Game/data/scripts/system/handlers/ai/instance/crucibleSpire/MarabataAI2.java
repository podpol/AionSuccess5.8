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
package ai.instance.crucibleSpire;

import ai.AggressiveNpcAI2;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDInfinity_Named_12")
public class MarabataAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		spawn(247361, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
		spawn(247361, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
}
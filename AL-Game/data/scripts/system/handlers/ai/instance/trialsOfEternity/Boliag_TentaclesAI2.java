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
package ai.instance.trialsOfEternity;

import ai.AggressiveNpcAI2;

import com.aionemu.gameserver.ai2.*;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Dimension_Boss_Portal")
public class Boliag_TentaclesAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 246937:
			    spawn(246724, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0, 1018);
		    break;
			case 247024:
			    spawn(246724, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0, 991);
			break;
			case 247025:
			    spawn(246724, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0, 1016);
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
}
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
package ai.worlds.beluslan;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("mine_mage")
public class Mine_MageAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		switch (Rnd.get(1, 2)) {
			case 1:
			    spawnArchmageMegran();
			break;
			case 2:
			break;
		}
		super.handleDied();
	}
	
	private void spawnArchmageMegran() {
		spawn(213716, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Archmage Megran.
	}
}
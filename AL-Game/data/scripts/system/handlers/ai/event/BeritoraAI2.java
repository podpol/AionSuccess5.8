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
package ai.event;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.world.WorldPosition;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("beritora")
public class BeritoraAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			spawn(832262, p.getX(), p.getY(), p.getZ(), (byte) 0); //Treasure Chest.
		}
		super.handleDied();
	}
}
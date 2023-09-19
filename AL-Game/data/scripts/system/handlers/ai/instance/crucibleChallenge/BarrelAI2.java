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
package ai.instance.crucibleChallenge;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("barrel")
public class BarrelAI2 extends NpcAI2
{
	@Override
	protected void handleDied() {
		super.handleDied();
		int npcId = 0;
		switch (getNpcId()) {
			case 217840: //Meat Barrel.
				npcId = 217841; //Wafer Thin Meet.
			break;
			case 218560: //Aether Barrel.
				npcId = 218561; //Aether Lump.
			break;
		}
		spawn(npcId, 1298.4448f, 1728.3262f, 316.8472f, (byte) 63);
		AI2Actions.deleteOwner(this);
	}
}
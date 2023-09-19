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
package ai.instance.padmarashkaCave;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("padmarashka_eggs")
public class Padmarashka_EggsAI2 extends NpcAI2
{
	@Override
	protected void handleDied() {
		switch (Rnd.get(1, 5)) {
			case 1:
			    spawn(282615, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Neonate Drakan.
			break;
			case 2:
			    spawn(282616, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Neonate Drakan.
			break;
			case 3:
			    spawn(282617, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Neonate Drakan.
			break;
			case 4:
			    spawn(282618, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Neonate Drakan.
			break;
			case 5:
			    spawn(282619, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Neonate Drakan.
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
}
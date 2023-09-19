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
package ai.worlds.inggison;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("huge_egg")
public class Huge_EggAI2 extends NpcAI2
{
    @Override
	protected void handleDied() {
		spawn(217097, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Lightwing Coiren.
		super.handleDied();
		AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
	
	@Override
	public int modifyOwnerDamage(int damage) {
		return 1;
	}
	
	@Override
	public int modifyDamage(int damage) {
		return 1;
	}
}
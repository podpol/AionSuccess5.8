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
package ai.instance.infernalDanuarReliquary;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("venge_full_orb")
public class Venge_Full_OrbAI2 extends NpcAI2
{
	private Future<?> task;
	
    @Override
    protected void handleSpawned() {
  	    super.handleSpawned();
		final int skill;
		switch (getNpcId()) {
			case 284443: //Sorcerer Queen Modor.
				skill = 21178;
		    break;
			default:
				skill = 0;
		}
		if (skill == 0)
			return;
		task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.useSkill(Venge_Full_OrbAI2.this, skill);
			}
		},0, 2000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Venge_Full_OrbAI2.this);
			}
		}, 1000);
	}
	
	@Override
	public void handleDespawned() {
		task.cancel(true);
		super.handleDespawned();
	}
}
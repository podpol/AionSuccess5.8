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
package ai.instance.aturamSkyFortress;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.services.NpcShoutsService;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("weaponh")
public class WeaponHAI2 extends AggressiveNpcAI2
{
	private boolean isHome = true;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		isHome = false;
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			//Abnormal object detected. Elimination beginning.
			NpcShoutsService.getInstance().sendMsg(getOwner(), 1402787, 0);
			getPosition().getWorldMapInstance().getDoors().get(85).setOpen(true);
		}
	}
	
	@Override
	protected void handleBackHome() {
	    isHome = true;
		getPosition().getWorldMapInstance().getDoors().get(85).setOpen(false);
		super.handleBackHome();
	}
	
	@Override
	protected void handleDied() {
		getPosition().getWorldMapInstance().getDoors().get(85).setOpen(true);
		super.handleDied();
	}
}
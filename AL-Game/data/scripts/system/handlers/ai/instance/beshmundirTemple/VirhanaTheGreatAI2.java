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
package ai.instance.beshmundirTemple;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("virhana")
public class VirhanaTheGreatAI2 extends AggressiveNpcAI2
{
	private int count;
	private boolean isStart;
	
	@Override
	public void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (!isStart){
			isStart = true;
			scheduleRage();
		}
	}
	
	@Override
	protected void handleBackHome() {
		super.handleBackHome();
		isStart = false;
	}
	
	private void scheduleRage() {
		if (isAlreadyDead() || !isStart) {
			return;
		}
		AI2Actions.useSkill(this, 19121); //Seal Of Reflection.
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				startRage();
			}
		}, 70000);
	}
	
	private void startRage() {
		if (isAlreadyDead() || !isStart) {
			return;
		} if (count < 12) {
			AI2Actions.useSkill(this, 18897); //Earthly Retribution.
			count++;
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					startRage();
				}
			}, 10000);
		} else {
			count = 0;
			scheduleRage();
		}
	}
}
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
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("manadar")
public class ManadarAI2 extends AggressiveNpcAI2
{
	private boolean isStart = false;
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 90 && !isStart) {
			isStart = true;
			check();
		}
	}
	
	@Override
	protected void handleBackHome() {
		isStart = false;
		super.handleBackHome();
	}
	
	private void check () {
		if (getPosition().isSpawned() && !isAlreadyDead() && isStart) {
			for (int i = 0; i < 5; i++) {
				int distance = Rnd.get(4, 11);
				int nrNpc = Rnd.get(1, 2);
				switch (nrNpc) {
					case 1:
						nrNpc = 281545;
					break;
					case 2:
						nrNpc = 281756;
					break;
				}
				rndSpawnInRange(nrNpc, distance);
			}
			doSchedule();
		}
	}
	
	private void rndSpawnInRange(int npcId, float distance) {
		float direction = Rnd.get(0, 199) / 100f;
		float x1 = (float) (Math.cos(Math.PI * direction) * distance);
		float y1 = (float) (Math.sin(Math.PI * direction) * distance);
		spawn(npcId, getPosition().getX() + x1, getPosition().getY() + y1, getPosition().getZ(), (byte) 0);
	}
	
	private void doSchedule() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				check();
			}
		}, 6000);
	}
}
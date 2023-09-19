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
package ai.instance.darkPoeta;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("telepathycontroller")
public class TelepathyControllerAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean isStart50Event = new AtomicBoolean(false);
	private AtomicBoolean isStart10Event = new AtomicBoolean(false);
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 50) {
			if (isStart50Event.compareAndSet(false, true)) {
				helper();
			}
		} else if (hpPercentage <= 10) {
			if (isStart10Event.compareAndSet(false, true)) {
				helper();
			}
		}
	}
	
	@Override
	protected void handleBackHome() {
		isStart50Event.set(false);
		isStart10Event.set(false);
		super.handleBackHome();
	}
	
	private void helper() {
		if (getPosition().isSpawned() && !isAlreadyDead()) {
			for (int i = 0; i < 1; i++) {
				int distance = Rnd.get(7, 10);
				int nrNpc = Rnd.get(1, 2);
				switch (nrNpc) {
					case 1:
						nrNpc = 281150; //Anuhart Escort.
					break;
					case 2:
						nrNpc = 281334; //Bionic Clodworm.
					break;
				}
				rndSpawnInRange(nrNpc, distance);
			}
		}
	}
	
	private void rndSpawnInRange(int npcId, float distance) {
		float direction = Rnd.get(0, 199) / 100f;
		float x1 = (float) (Math.cos(Math.PI * direction) * distance);
		float y1 = (float) (Math.sin(Math.PI * direction) * distance);
		spawn(npcId, getPosition().getX() + x1, getPosition().getY() + y1, getPosition().getZ(), (byte) 0);
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
}
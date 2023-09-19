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
package ai.instance.seizedDanuarSanctuary;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.List;
import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("suyaroka")
public class Warmage_SuyarokaAI2 extends AggressiveNpcAI2
{
	private int stage = 0;
	private boolean isStart = false;
	private Future<?> enrageTask;
	
	@Override
	protected void handleCreatureAggro(Creature creature) {
		super.handleCreatureAggro(creature);
		wakeUp();
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
		wakeUp();
	}
	
	private void wakeUp() {
		isStart = true;
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 90 && stage < 1) {
			stage1();
			stage = 1;
		}
	}
	
	private void stage1() {
		int delay = 50000;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
			SkillEngine.getInstance().getSkill(getOwner(), 20657, 1, getOwner()).useNoAnimationSkill(); //Summoning Ritual.
			ShebanMysticalTyrhund();
			scheduleDelayStage1(delay);
		}
	}
	
	private void ShebanMysticalTyrhund() {
	    if (!isAlreadyDead()) {
		    enrageTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
			    public void run() {
					if (!isAlreadyDead()) {
					    spawn(284455, 1051.3069f, 694.83075f, 282.0391f, (byte) 14); //Sheban Mystical Tyrhund.
					    spawn(284455, 1062.1957f, 694.9131f, 282.0391f, (byte) 51); //Sheban Mystical Tyrhund.
					}
				}
			}, 3000);
		}
	}
	
	private void scheduleDelayStage1(int delay) {
		if (!isStart && !isAlreadyDead()) {
			return;
		} else {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					stage1();
				}
			}, delay);
		}
	}
	
	private void despawnNpcs(int npcId) {
		List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
	
	@Override
	protected void handleBackHome() {
		super.handleBackHome();
        despawnNpcs(284455); //Sheban Mystical Tyrhund.
		isStart = false;
		stage = 0;
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
        despawnNpcs(284455); //Sheban Mystical Tyrhund.
		isStart = false;
		stage = 0;
	}
}
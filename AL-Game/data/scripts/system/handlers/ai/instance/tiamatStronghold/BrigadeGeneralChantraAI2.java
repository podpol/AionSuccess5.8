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
package ai.instance.tiamatStronghold;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("brigadegeneralchantra")
public class BrigadeGeneralChantraAI2 extends AggressiveNpcAI2
{
	private Future<?> trapTask;
	private boolean isFinalBuff;
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	protected void handleAttack(Creature creature){
		super.handleAttack(creature);
		if (isHome.compareAndSet(true, false)) {
			startSkillTask();
		} if (!isFinalBuff && getOwner().getLifeStats().getHpPercentage() <= 25) {
			isFinalBuff = true;
			AI2Actions.useSkill(this, 20942);
		}
	}
	
	private void startSkillTask()	{
		trapTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run()	{
				if (isAlreadyDead()) {
					cancelTask();
				} else {
					startTrapEvent();
				}
			}
		}, 5000, 40000);
	}
	
	private void cancelTask() {
		if (trapTask != null && !trapTask.isCancelled()) {
			trapTask.cancel(true);
		}
	}
	
	private void startTrapEvent() {
		int [] trapNpc = {283092, 283094};
		final int trap = trapNpc[Rnd.get(0, trapNpc.length -1)]; 
		if (getPosition().getWorldMapInstance().getNpc(trap) == null) {
			spawn(trap, 1031.1f, 466.38f, 445.45f, (byte) 0);
			ThreadPoolManager.getInstance().schedule(new Runnable() {
	  		    @Override
	  		    public void run() {
	  			    Npc ring = getPosition().getWorldMapInstance().getNpc(trap);
	  			    if (trap == 283092) {
	  				    spawn(283171, 1031.1f, 466.38f, 445.45f, (byte) 0);
					} else {
	  				    spawn(283172, 1031.1f, 466.38f, 445.45f, (byte) 0);
					}
	  			    ring.getController().onDelete();
	  		    }
	  	    }, 5000);
	    }
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		cancelTask();
	}
	
	@Override
	protected void handleDespawned() {
		super.handleDespawned();
		cancelTask();
	}
	
	@Override
	protected void handleBackHome() {
		super.handleBackHome();
		cancelTask();
		isFinalBuff = false;
		isHome.set(true);
	}
}
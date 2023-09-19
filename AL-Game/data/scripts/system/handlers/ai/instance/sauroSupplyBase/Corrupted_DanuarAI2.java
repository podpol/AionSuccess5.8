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
package ai.instance.sauroSupplyBase;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("corrupted_danuar")
public class Corrupted_DanuarAI2 extends AggressiveNpcAI2
{
    private Future<?> skillTask;
	
    @Override
    protected void handleSpawned() {
        super.handleSpawned();
        startpower();
    }
	
    private void startpower() {
        skillTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
		        AI2Actions.targetSelf(Corrupted_DanuarAI2.this);
                AI2Actions.useSkill(Corrupted_DanuarAI2.this, 21185); //Curse Of The Rune.
            }
        }, 3000, 5000);
    }
	
    private void cancelskillTask() {
        if (skillTask != null && !skillTask.isCancelled()) {
            skillTask.cancel(true);
        }
    }
	
    @Override
    protected void handleDied() {
        cancelskillTask();
        super.handleDied();
    }
	
    @Override
    protected void handleDespawned() {
        cancelskillTask();
        super.handleDespawned();
    }
}
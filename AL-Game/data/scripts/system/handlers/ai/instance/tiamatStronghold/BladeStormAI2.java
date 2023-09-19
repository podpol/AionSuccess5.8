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
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("bladestorm")
public class BladeStormAI2 extends AggressiveNpcAI2
{
	private Future<?> stormBladeTask;
	
	@Override
	public void think() {
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		stormBlade();
		startLifeTask();
	}
	
	private void stormBlade() {
		stormBladeTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.targetCreature(BladeStormAI2.this, getPosition().getWorldMapInstance().getNpc(219357)); //Adjudant Anuhart.
				AI2Actions.targetCreature(BladeStormAI2.this, getPosition().getWorldMapInstance().getNpc(247717)); //F4_Raid_Drakan_Boss_55_Ah.
				AI2Actions.useSkill(BladeStormAI2.this, 20748); //Storm Blade.
			}
		}, 3000, 8000);
	}
	
    private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(BladeStormAI2.this);
			}
		}, 10000);
	}
	
    @Override
	public boolean isMoveSupported() {
		return false;
	}
}
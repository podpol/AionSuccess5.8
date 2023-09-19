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

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("electrocute")
public class ElectrocuteAI2 extends NpcAI2
{
	private Future<?> task;
	
	@Override
	public void think() {
	}
	
    @Override
    protected void handleSpawned() {
  	    super.handleSpawned();
		task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.useSkill(ElectrocuteAI2.this, 20757);
			}
		},0, 2000);
  	    despawn();
    }
	
    private void despawn() {
  	    ThreadPoolManager.getInstance().schedule(new Runnable() {
  		    @Override
  		    public void run() {
  			    getOwner().getController().onDelete();
  		    }
  	    }, 10000);
    }
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
	
	@Override
	public void handleDespawned() {
		task.cancel(true);
		super.handleDespawned();
	}
}
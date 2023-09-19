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
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.MathUtil;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("chantrarings")
public class ChantraRingsAI2 extends NpcAI2
{
    @Override
	public void think() {
	}
	
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        checkDistance(this, creature);
    }
	
    private void checkDistance(NpcAI2 ai, Creature creature) {
  	    int debuff = getOwner().getNpcId() == 283172 ? 20735 : 20734;
        if (creature instanceof Player) {
    	    if (getOwner().getNpcId() == 283172 && MathUtil.isIn3dRangeLimited(getOwner(), creature, 10, 18) ||
			    getOwner().getNpcId() == 283171 && MathUtil.isIn3dRangeLimited(getOwner(), creature, 18, 25) ||
				getOwner().getNpcId() == 283171 && MathUtil.isIn3dRangeLimited(getOwner(), creature, 0, 10)) {
    		    if (!creature.getEffectController().hasAbnormalEffect(debuff)) {
    		        AI2Actions.useSkill(this, debuff);
				}
    	    }
        }
	}
	
    @Override
    protected void handleSpawned() {
  	    super.handleSpawned();
  	    despawn();
    }
	
    private void despawn() {
  	    ThreadPoolManager.getInstance().schedule(new Runnable() {
  		    @Override
  		    public void run() {
  			    getOwner().getController().onDelete();
  		    }
  	    }, 20000);
    }
	
    @Override
	public boolean isMoveSupported() {
		return false;
	}
}
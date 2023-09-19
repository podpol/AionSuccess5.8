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
package ai.walkers;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.handler.MoveEventHandler;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.utils.MathUtil;
import org.apache.commons.lang.ArrayUtils;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("polorserin")
public class PolorSerinAI2 extends WalkGeneralRunnerAI2
{
	static final int[] stopAdults = { 203129, 203132 };
	
	@Override
	protected void handleMoveArrived() {
		boolean adultsNear = false;
		for (VisibleObject object : getOwner().getKnownList().getKnownObjects().values()) {
			if (object instanceof Npc) {
				Npc npc = (Npc)object;
				if (!ArrayUtils.contains(stopAdults, npc.getNpcId()))
					continue;
				if (MathUtil.isIn3dRange(npc, getOwner(), getOwner().getAggroRange())) {
					adultsNear = true;
					break;
				}
			}
		} if (adultsNear) {
			MoveEventHandler.onMoveArrived(this);
			getOwner().unsetState(CreatureState.WEAPON_EQUIPPED);
		} else {
			super.handleMoveArrived();
			getOwner().setState(CreatureState.WEAPON_EQUIPPED);
		}
	}
}
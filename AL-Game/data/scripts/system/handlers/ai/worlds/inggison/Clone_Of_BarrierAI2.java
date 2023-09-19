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
package ai.worlds.inggison;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("omega_clone")
public class Clone_Of_BarrierAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		for (VisibleObject object: getKnownList().getKnownObjects().values()) {
			if (object instanceof Npc) {
				Npc npc = (Npc) object;
				if (npc.getNpcId() == 216516) { //Omega.
					npc.getEffectController().removeEffect(18671); //Magic Ward.
					break;
				}
			}
		}
		super.handleDied();
	}
}
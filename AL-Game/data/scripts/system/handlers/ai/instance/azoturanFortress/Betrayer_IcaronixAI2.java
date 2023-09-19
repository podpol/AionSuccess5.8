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
package ai.instance.azoturanFortress;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("betrayer_icaronix")
public class Betrayer_IcaronixAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkForSupport(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkForSupport(Creature creature) {
		for (VisibleObject object: getKnownList().getKnownObjects().values()) {
			if (object instanceof Npc && isInRange(object, 40)) {
				((Npc) object).getAi2().onCreatureEvent(AIEventType.CREATURE_AGGRO, creature);
			}
		}
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 75) {
			spawn(214599, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Icaronix The Betrayer.
			AI2Actions.deleteOwner(this);
		}
	}
}
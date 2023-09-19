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
package ai.worlds.enshar;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.handler.CreatureEventHandler;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("shademender")
public class ShademenderAI2 extends AggressiveNpcAI2
{
	@Override
    protected void handleCreatureMoved(Creature creature) {
        CreatureEventHandler.onCreatureSee(this, creature);
    	if (creature instanceof Player) {
			final Player player = (Player) creature;
    		if (!creature.getEffectController().hasAbnormalEffect(20664)) { //Conqueror's Passion.
				if (player.getCommonData().getRace() == Race.ASMODIANS) {
    		        SkillEngine.getInstance().getSkill(getOwner(), 20664, 1, (Player) creature).useNoAnimationSkill(); //Conqueror's Passion.
				}
			}
    	}
    }
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 219880:
			case 219886:
			case 219893:
			case 219899:
			case 219905:
			case 219912:
			case 219919:
			case 219925:
				conquerorPassion();
			break;
		}
	}
	
	private void conquerorPassion() {
		SkillEngine.getInstance().getSkill(getOwner(), 20665, 1, getOwner()).useNoAnimationSkill(); //Conqueror's Passion.
	}
}
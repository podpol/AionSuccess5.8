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
package ai.housing;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.controllers.observer.GaleCycloneObserver;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import javolution.util.FastMap;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("galecyclone")
public class GaleCycloneAI2 extends NpcAI2
{
    private boolean blocked;
	private FastMap<Integer, GaleCycloneObserver> observed = new FastMap<Integer, GaleCycloneObserver>().shared();
    
	@Override
	protected void handleCreatureSee(Creature creature) {
		if (blocked) {
			return;
		} if (creature instanceof Player) {
			final Player player = (Player) creature;
			final GaleCycloneObserver observer = new GaleCycloneObserver(player, getOwner()) {
				@Override
				public void onMove() {
					if (!blocked) {
						SkillEngine.getInstance().getSkill(getOwner(), 20528, 50, player).useNoAnimationSkill();
					}
				}
			};
			player.getObserveController().addObserver(observer);
			observed.put(player.getObjectId(), observer);
		}
	}
	
	@Override
	protected void handleCreatureNotSee(Creature creature) {
		if (blocked) {
			return;
		} if (creature instanceof Player) {
			Player player = (Player) creature;
			Integer obj = player.getObjectId();
			GaleCycloneObserver observer = observed.remove(obj);
			if (observer != null) {
				player.getObserveController().removeObserver(observer);
			}
		}
	}
	
	@Override
	protected void handleDied() {
		clear();
		super.handleDied();
	}
	
	@Override
	protected void handleDespawned() {
		clear();
		super.handleDespawned();
	}
	
	private void clear() {
		blocked = true;
		for (Integer obj: observed.keySet()) {
			Player player = getKnownList().getKnownPlayers().get(obj);
			GaleCycloneObserver observer = observed.remove(obj);
			if (player != null) {
				player.getObserveController().removeObserver(observer);
			}
		}
	}
}
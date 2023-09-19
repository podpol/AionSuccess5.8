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
package ai;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.StateEvents;
import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.ai2.handler.FollowEventHandler;
import com.aionemu.gameserver.model.gameobjects.Creature;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("following")
public class FollowingNpcAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleFollowMe(Creature creature) {
		FollowEventHandler.follow(this, creature);
	}
	
	@Override
	protected boolean canHandleEvent(AIEventType eventType) {
		switch (getState()) {
			case DESPAWNED:
				return StateEvents.DESPAWN_EVENTS.hasEvent(eventType);
			case DIED:
				return StateEvents.DEAD_EVENTS.hasEvent(eventType);
			case CREATED:
				return StateEvents.CREATED_EVENTS.hasEvent(eventType);
		} if (eventType == AIEventType.CREATURE_MOVED) {
				return getState() == AIState.FOLLOWING;
		}
		return true;
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature == getOwner().getTarget())
			FollowEventHandler.creatureMoved(this, creature);
	}
	
	@Override
	protected void handleStopFollowMe(Creature creature) {
		FollowEventHandler.stopFollow(this, creature);
	}
}
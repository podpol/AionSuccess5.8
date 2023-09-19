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
package ai.instance.rentusBase;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.model.actions.NpcActions;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.skillengine.SkillEngine;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("kuhara_bomb")
public class KuharaBombAI2 extends AggressiveNpcAI2
{
	private Npc kuharaTheVolatile1;
	private Npc kuharaTheVolatile2;
	private AtomicBoolean isDestroyed = new AtomicBoolean(false);
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		this.setStateIfNot(AIState.FOLLOWING);
		kuharaTheVolatile1 = getPosition().getWorldMapInstance().getNpc(217311); //Kuhara The Volatile.
		kuharaTheVolatile2 = getPosition().getWorldMapInstance().getNpc(236298); //Kuhara The Volatile.
	}
	
	@Override
	protected void handleMoveArrived() {
		if (isDestroyed.compareAndSet(false, true)) {
			if (kuharaTheVolatile1 != null && !NpcActions.isAlreadyDead(kuharaTheVolatile1)) {
				SkillEngine.getInstance().getSkill(getOwner(), 19659, 60, kuharaTheVolatile1).useNoAnimationSkill();  //Bomb Explosion.
			} else if (kuharaTheVolatile2 != null && !NpcActions.isAlreadyDead(kuharaTheVolatile2)) {
				SkillEngine.getInstance().getSkill(getOwner(), 19659, 60, kuharaTheVolatile2).useNoAnimationSkill();  //Bomb Explosion.
			}
		}
	}
}
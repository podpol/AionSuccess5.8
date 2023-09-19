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
package ai.instance.aturamSkyFortress;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("explosion_shadows")
public class ExplosionShadowsAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean isHome = new AtomicBoolean(true);
	
	@Override
	protected void handleCreatureAggro(Creature creature) {
		super.handleCreatureAggro(creature);
		if (isHome.compareAndSet(true, false)) {
			SkillEngine.getInstance().getSkill(getOwner(), 19428, 1, getOwner()).useNoAnimationSkill();
			getPosition().getWorldMapInstance().getDoors().get(2).setOpen(true);
			getPosition().getWorldMapInstance().getDoors().get(17).setOpen(true);
			doSchedule();
		}
	}
	
	@Override
	protected void handleBackHome() {
		isHome.set(true);
		super.handleBackHome();
	}
	
	private void doSchedule() {
		if (!isAlreadyDead()) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						SkillEngine.getInstance().getSkill(getOwner(), 19425, 49, getOwner()).useNoAnimationSkill();
						ThreadPoolManager.getInstance().schedule(new Runnable() {
							@Override
							public void run() {
								if (!isAlreadyDead()) {
									check();
								}
							}
						}, 1500);
					}
				}
			}, 3000);
		}
	}
	
	private void check() {
		getPosition().getWorldMapInstance().getDoors().get(17).setOpen(false);
		getPosition().getWorldMapInstance().getDoors().get(2).setOpen(false);
		getKnownList().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.getEffectController().hasAbnormalEffect(19502) ||
				    player.getEffectController().hasAbnormalEffect(21807) ||
				    player.getEffectController().hasAbnormalEffect(21808)) {
					final Npc npc = (Npc) spawn(799657, player.getX(), player.getY(), player.getZ(), player.getHeading());
					player.getEffectController().removeEffect(19502);
					player.getEffectController().removeEffect(21807);
					player.getEffectController().removeEffect(21808);
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							if (npc != null && !npc.getLifeStats().isAlreadyDead()) {
								npc.getController().onDelete();
							}
						}
					}, 4000);
				}
			}
		});
		AI2Actions.deleteOwner(this);
	}
}
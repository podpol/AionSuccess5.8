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
package ai.worlds.iluma;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("jump_trigger")
public class Jump_TriggerAI2 extends AggressiveNpcAI2
{
	@Override
	public void think() {
	}
	
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			PlayerEffectController effectController = player.getEffectController();
			if (MathUtil.getDistance(getOwner(), player) <= 5) {
				if (startedEvent.compareAndSet(false, true)) {
					switch (Rnd.get(1, 5)) {
					    case 1:
						    effectController.removeEffect(22883);
				            effectController.removeEffect(22884);
				            effectController.removeEffect(22885);
							effectController.removeEffect(22886);
                            SkillEngine.getInstance().getSkill(player, 22882, 1, player).useNoAnimationSkill(); //Boost Attack Power.
						break;
						case 2:
						    effectController.removeEffect(22884);
				            effectController.removeEffect(22885);
				            effectController.removeEffect(22886);
							effectController.removeEffect(22882);
                            SkillEngine.getInstance().getSkill(player, 22883, 1, player).useNoAnimationSkill(); //Movement Speed Increase.
						break;
						case 3:
						    effectController.removeEffect(22885);
				            effectController.removeEffect(22886);
				            effectController.removeEffect(22882);
							effectController.removeEffect(22883);
                            SkillEngine.getInstance().getSkill(player, 22884, 1, player).useNoAnimationSkill(); //Attack Speed Increased.
						break;
						case 4:
						    effectController.removeEffect(22886);
				            effectController.removeEffect(22882);
				            effectController.removeEffect(22883);
							effectController.removeEffect(22884);
                            SkillEngine.getInstance().getSkill(player, 22885, 1, player).useNoAnimationSkill(); //Boost Defense.
						break;
						case 5:
						    effectController.removeEffect(22882);
				            effectController.removeEffect(22883);
				            effectController.removeEffect(22884);
							effectController.removeEffect(22885);
                            SkillEngine.getInstance().getSkill(player, 22886, 1, player).useNoAnimationSkill(); //Casting Time Reduced.
						break;
					}
					AI2Actions.deleteOwner(Jump_TriggerAI2.this);
					AI2Actions.scheduleRespawn(this);
				}
			}
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
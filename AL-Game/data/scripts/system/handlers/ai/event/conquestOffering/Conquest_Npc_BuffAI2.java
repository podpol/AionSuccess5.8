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
package ai.event.conquestOffering;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.controllers.effect.PlayerEffectController;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("conquest_npc_buff")
public class Conquest_Npc_BuffAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		super.handleDialogStart(player);
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		PlayerEffectController effectController = player.getEffectController();
		switch (getNpcId()) {
		    case 856175: //Pawrunerk.
			    effectController.removeEffect(21925);
				effectController.removeEffect(21926);
				effectController.removeEffect(21927);
		        SkillEngine.getInstance().getSkill(player, 21924, 1, player).useNoAnimationSkill(); //Boost Attack Power.
		    break;
			case 856176: //Chitrunerk.
			    effectController.removeEffect(21924);
				effectController.removeEffect(21926);
				effectController.removeEffect(21927);
			    SkillEngine.getInstance().getSkill(player, 21925, 1, player).useNoAnimationSkill(); //Movement Speed Increase.
			break;
			case 856177: //Rapirunerk.
			    effectController.removeEffect(21924);
				effectController.removeEffect(21925);
				effectController.removeEffect(21927);
			    SkillEngine.getInstance().getSkill(player, 21926, 1, player).useNoAnimationSkill(); //Attack Speed/Casting Speed Increase.
			break;
			case 856178: //Dandrunerk.
			    effectController.removeEffect(21924);
				effectController.removeEffect(21925);
				effectController.removeEffect(21926);
			    SkillEngine.getInstance().getSkill(player, 21927, 1, player).useNoAnimationSkill(); //Boost Defense.
			break;
		}
		AI2Actions.deleteOwner(this);
	}
}
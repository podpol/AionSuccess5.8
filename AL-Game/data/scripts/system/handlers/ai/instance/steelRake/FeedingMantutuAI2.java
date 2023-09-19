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
package ai.instance.steelRake;

import ai.ShifterAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.NpcActions;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("feeding_mantutu")
public class FeedingMantutuAI2 extends ShifterAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		WorldMapInstance instance = getPosition().getWorldMapInstance();
		if (instance.getNpc(281128) == null && instance.getNpc(281129) == null) {
			super.handleDialogStart(player);
		}
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		super.handleUseItemFinish(player);
		Npc boss = getPosition().getWorldMapInstance().getNpc(215079);
		if (boss != null && boss.isSpawned() && !NpcActions.isAlreadyDead(boss)) {
			Npc npc = null;
			switch (getNpcId()) {
				case 701387: //Water Supply.
					npc = (Npc) spawn(281129, 712.042f, 490.5559f, 939.7027f, (byte) 0);
				break;
				case 701386: //Feed Supply.
					npc = (Npc) spawn(281128, 714.62634f, 504.4552f, 939.60675f, (byte) 0);
				break;
			}
			boss.getAi2().onCustomEvent(1, npc);
			AI2Actions.deleteOwner(this);
		}
	}
}
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
package ai.walkers;

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.handler.MoveEventHandler;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npcshout.NpcShout;
import com.aionemu.gameserver.model.templates.npcshout.ShoutEventType;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("naia")
public class NaiaAI2 extends GeneralNpcAI2
{
	boolean saidCannon = false;
	boolean saidQydro = false;
	
	@Override
	protected void handleMoveArrived() {
		MoveEventHandler.onMoveArrived(this);
		Npc npc2 = null;
		Npc cannon = getPosition().getWorldMapInstance().getNpc(203145);
		Npc qydro = getPosition().getWorldMapInstance().getNpc(203125);
		boolean isCannonNear = MathUtil.isIn3dRange(getOwner(), cannon, getOwner().getAggroRange());
		boolean isQydroNear = MathUtil.isIn3dRange(getOwner(), qydro, getOwner().getAggroRange());
		int delay = 0;
		List<NpcShout> shouts = null;
		if (!saidCannon && isCannonNear) {
			saidCannon = true;
			npc2 = cannon;
			delay = 10;
			shouts = DataManager.NPC_SHOUT_DATA.getNpcShouts(getPosition().getMapId(), getNpcId(), ShoutEventType.WALK_WAYPOINT, "2", 0);
		} else if (saidCannon && !isCannonNear) {
			saidCannon = false;
		} if (!saidQydro && isQydroNear) {
			saidQydro = true;
			npc2 = qydro;
			shouts = DataManager.NPC_SHOUT_DATA.getNpcShouts(getPosition().getMapId(), getNpcId(), ShoutEventType.WALK_WAYPOINT, "1", 0);
		} else if (saidQydro && !isQydroNear) {
			saidQydro = false;
		} if (shouts != null) {
			NpcShoutsService.getInstance().shout(getOwner(), npc2, shouts, delay, false);
			shouts.clear();
		}
	}
}
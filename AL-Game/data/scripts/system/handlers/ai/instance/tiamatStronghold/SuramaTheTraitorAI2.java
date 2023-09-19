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
package ai.instance.tiamatStronghold;

import ai.GeneralNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.NpcType;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("suramathetraitor")
public class SuramaTheTraitorAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		moveToRaksha();
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		NpcShoutsService.getInstance().sendMsg(getOwner(), 390845, getOwner().getObjectId(), 0, 2000);
	}
	
	private void moveToRaksha() {
		setStateIfNot(AIState.WALKING);
		getOwner().setState(1);
		getMoveController().moveToPoint(651, 1319, 487);
		PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getOwner().getObjectId()));
		ThreadPoolManager.getInstance().schedule(new Runnable() {
		    @Override
		    public void run() {
		  	    startDialog();
		    }
	    }, 10000);
	}
	
	private void startDialog() {
		final Npc laksyaka = getPosition().getWorldMapInstance().getNpc(219356); //Brigade General Laksyaka.
		NpcShoutsService.getInstance().sendMsg(getOwner(), 390841, getOwner().getObjectId(), 0, 0);
		NpcShoutsService.getInstance().sendMsg(getOwner(), 390842, getOwner().getObjectId(), 0, 3000);
		NpcShoutsService.getInstance().sendMsg(laksyaka, 390843, laksyaka.getObjectId(), 0, 6000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
		    @Override
		    public void run() {
		  	    WorldMapInstance instance = getPosition().getWorldMapInstance();
		  	    laksyaka.setTarget(getOwner());
		  	    SkillEngine.getInstance().getSkill(laksyaka, 20952, 60, getOwner()).useNoAnimationSkill();
		  	    laksyaka.setNpcType(NpcType.ATTACKABLE);
		  	    for (Player player: instance.getPlayersInside()) {
					if (MathUtil.isIn3dRange(player, laksyaka, 100)) {
						player.clearKnownlist();
						player.updateKnownlist();
					}
				}
		    }
	    }, 8000);
	}
}
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
package ai.instance.kromedesTrial;

import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("blood_wing")
public class Blood_WingAI2 extends NpcAI2
{
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        checkDistance(this, creature);
    }
	
	private void checkDistance(NpcAI2 ai, Creature creature) {
        if (creature instanceof Player && !creature.getLifeStats().isAlreadyDead()) {
			final Player player = (Player) creature;
        	if (MathUtil.isIn3dRange(getOwner(), creature, 15)) {
				startBloodWing();
        	}
        }
    }
	
	private void startBloodWing() {
		AI2Actions.deleteOwner(Blood_WingAI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackBloodWing((Npc)spawn(217109, 641.71704f, 573.7239f, 209.03032f, (byte) 96), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 640.8816f, 572.90814f, 209.03218f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 639.92664f, 572.13995f, 209.03427f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 639.0914f, 571.4476f, 209.0361f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 640.8328f, 573.7359f, 209.03212f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 639.96747f, 573.10034f, 209.034f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
				attackBloodWing((Npc)spawn(217109, 638.9543f, 572.216f, 209.03624f, (byte) 103), 651.39923f, 564.0526f, 206.85417f, false);
			}
		}, 2500);
	}
	
	private void attackBloodWing(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
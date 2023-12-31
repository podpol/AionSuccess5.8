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
package ai.instance.admaStronghold;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("suspicious_coffin")
public class Suspicious_CoffinAI2 extends AggressiveNpcAI2
{
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		startLifeTask();
		startZombiesEvent();
    }
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Suspicious_CoffinAI2.this);
			}
		}, 5000);
	}
	
	private void rushZombies(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	private void startZombiesEvent() {
		rushZombies((Npc)spawn(286079, 600.93933f, 768.17395f, 198.62938f, (byte) 84), 597.2995f, 738.27295f, 197.7209f, false);
		rushZombies((Npc)spawn(286079, 621.0434f, 722.87054f, 198.61337f, (byte) 75), 597.2995f, 738.27295f, 197.7209f, false);
		rushZombies((Npc)spawn(286079, 574.20996f, 722.4946f, 198.64093f, (byte) 6), 597.2995f, 738.27295f, 197.7209f, false);
		rushZombies((Npc)spawn(286079, 595.97644f, 721.2781f, 198.62476f, (byte) 90), 597.2995f, 738.27295f, 197.7209f, false);
		rushZombies((Npc)spawn(286079, 582.4945f, 750.6145f, 198.60979f, (byte) 40), 597.2995f, 738.27295f, 197.7209f, false);
		rushZombies((Npc)spawn(286079, 622.8963f, 760.1835f, 198.61732f, (byte) 6), 597.2995f, 738.27295f, 197.7209f, false);
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}
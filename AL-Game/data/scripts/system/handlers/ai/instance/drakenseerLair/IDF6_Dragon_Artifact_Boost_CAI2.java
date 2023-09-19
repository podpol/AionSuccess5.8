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
package ai.instance.drakenseerLair;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDF6_Dragon_Artifact_Boost_C")
public class IDF6_Dragon_Artifact_Boost_CAI2 extends NpcAI2
{
	private Npc IDF6DragonGate;
	private boolean canThink = true;
	private int artifactBoostPhase = 0;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
		} if (IDF6DragonGate == null) {
		    IDF6DragonGate = (Npc)spawn(703159, 335.19684f, 323.7957f, 319.2191f, (byte) 82);
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage == 99 && artifactBoostPhase < 1) {
			artifactBoostPhase = 1;
			startIDF6DragonRaidC1();
		} if (hpPercentage == 80 && artifactBoostPhase < 2) {
			artifactBoostPhase = 2;
			startIDF6DragonRaidC1();
		} if (hpPercentage == 60 && artifactBoostPhase < 3) {
			artifactBoostPhase = 3;
			startIDF6DragonRaidC1();
		} if (hpPercentage == 40 && artifactBoostPhase < 4) {
			artifactBoostPhase = 4;
			startIDF6DragonRaidC1();
		} if (hpPercentage == 8 && artifactBoostPhase < 5) {
			artifactBoostPhase = 5;
			startIDF6DragonRaidC1();
		}
	}
	
   /**
	* Dragon Raid C
	*/
	private void startIDF6DragonRaidC1() {
		//Reinforcements have arrived to defend the Shielding Conduits.
		PacketSendUtility.npcSendPacketTime(getOwner(), SM_SYSTEM_MESSAGE.STR_MSG_IDF6_Dragon_Attack_Tower, 0);
		//Prepare for combat! Enemies approaching!
		NpcShoutsService.getInstance().sendMsg(getOwner(), 1402785, 10000);
		//Hold a little longer and you will survive.
		NpcShoutsService.getInstance().sendMsg(getOwner(), 1402833, 20000);
		//Only a few enemies left!
		NpcShoutsService.getInstance().sendMsg(getOwner(), 1402834, 30000);
		dragonRaid((Npc)spawn(220432, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
		dragonRaid((Npc)spawn(220433, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
		dragonRaid((Npc)spawn(220432, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
		dragonRaid((Npc)spawn(220435, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
		dragonRaid((Npc)spawn(220436, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
		dragonRaid((Npc)spawn(220440, 333.3891f, 319.6643f, 318.8283f, (byte) 82), 312.16144f, 285.48962f, 318.85556f, false);
	}
	
	private void dragonRaid(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	@Override
	public boolean canThink() {
		return canThink;
	}
	
	@Override
	protected void handleBackHome() {
		canThink = true;
		isAggred.set(false);
		super.handleBackHome();
	}
}
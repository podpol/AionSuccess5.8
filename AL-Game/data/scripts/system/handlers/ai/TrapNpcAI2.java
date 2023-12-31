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
package ai;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AIState;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.state.CreatureVisualState;
import com.aionemu.gameserver.model.skill.NpcSkillEntry;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PLAYER_STATE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("trap")
public class TrapNpcAI2 extends NpcAI2
{
	private int sensoryRange = 0;
	private Future<?> despawnTask;
	public static int EVENT_SET_TRAP_RANGE = 1;
	
	@Override
	protected void handleCreatureSee(Creature creature) {
		super.handleCreatureSee(creature);
		tryActivateTrap(creature);
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		super.handleCreatureMoved(creature);
		tryActivateTrap(creature);
	}
	
	private void tryActivateTrap(Creature creature) {
		if (despawnTask != null) {
			return;
		} if (!creature.getLifeStats().isAlreadyDead() &&
		      !creature.isInVisualState(CreatureVisualState.BLINKING) && isInRange(creature, getOwner().getAggroRange())) {
			Creature creator = (Creature) getCreator();
			if (!creator.isEnemy(creature)) {
				return;
			}
			explode(creature);
		}
	}
	
	@Override
	protected void handleCustomEvent(int eventId, Object... args) {
		if (eventId == EVENT_SET_TRAP_RANGE) {
			String ownerName = getObjectTemplate().getName().toLowerCase();
			if (ownerName.equals("snare trap")
			    || ownerName.equals("caltrop")
				|| ownerName.equals("spike trap")
				|| ownerName.equals("shock trap")
				|| ownerName.equals("sleep trap")
				|| ownerName.equals("blazing trap")
				|| ownerName.equals("explosion trap")
				|| ownerName.equals("specter's trap")
		        || ownerName.equals("explosive trap")
				|| ownerName.equals("poisoning trap")
				|| ownerName.equals("sandstorm trap")
				|| ownerName.equals("trap of slowing")
				|| ownerName.equals("trap of silence")
				|| ownerName.equals("propelling trap")
				|| ownerName.equals("spike bite trap")
				|| ownerName.equals("EV_RA_N_Light_SleepingTrap_G1_NPC")
				|| ownerName.equals("EV_RA_N_Dark_SleepingTrap_G1_NPC")) {
				sensoryRange = 4;
			} else if (ownerName.equals("trap")
				|| ownerName.equals("web trap")
				|| ownerName.equals("ice trap")
				|| ownerName.equals("storm mine")
				|| ownerName.equals("swamp trap")
				|| ownerName.equals("flame trap")
				|| ownerName.equals("sticky trap")
				|| ownerName.equals("skybound trap")
				|| ownerName.equals("paralyze trap")
				|| ownerName.equals("protect symbol")
				|| ownerName.equals("drakan net trap")
				|| ownerName.equals("thornburst trap")
				|| ownerName.equals("thorntwist trap")
				|| ownerName.equals("drakan mine trap")
				|| ownerName.equals("symbol of spirit")
				|| ownerName.equals("destruction trap")
				|| ownerName.equals("unidentified trap")
				|| ownerName.equals("symbol of solidity")
				|| ownerName.equals("symbol of recovery")
				|| ownerName.equals("symbol of protection")
				|| ownerName.equals("trap of clairvoyance")
				|| ownerName.equals("symbol of castle wall")
				|| ownerName.equals("scrapped mechanisms")
				|| ownerName.equals("trap of infernal blaze")
				|| ownerName.equals("Highdeva_Fire_NPC_L_G1")
				|| ownerName.equals("Highdeva_Fire_NPC_D_G1")
				|| ownerName.equals("IDEvent_Solo_Paralyze_NPC")) {
				sensoryRange = 10;
			}
		}
	}
	
	private void explode(Creature creature) {
		if (setStateIfNot(AIState.FIGHT)) {
			getOwner().unsetVisualState(CreatureVisualState.HIDE1);
			PacketSendUtility.broadcastPacket(getOwner(), new SM_PLAYER_STATE(getOwner()));
			AI2Actions.targetCreature(this, creature);
			NpcSkillEntry npcSkill = getSkillList().getRandomSkill();
			if (npcSkill != null) {
				AI2Actions.useSkill(this, npcSkill.getSkillId());
			}
			despawnTask = ThreadPoolManager.getInstance().schedule(new TrapDelete(this), 5000);
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
	
	private static final class TrapDelete implements Runnable {
		private TrapNpcAI2 ai;
		
		TrapDelete(TrapNpcAI2 ai) {
			this.ai = ai;
		}
		
		@Override
		public void run() {
			AI2Actions.deleteOwner(ai);
			ai = null;
		}
	}
}
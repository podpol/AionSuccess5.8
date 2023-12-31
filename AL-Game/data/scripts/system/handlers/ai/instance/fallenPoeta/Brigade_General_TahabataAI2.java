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
package ai.instance.fallenPoeta;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Brigade_General_Tahabata")
public class Brigade_General_TahabataAI2 extends AggressiveNpcAI2
{
	private int phase = 0;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage == 90 && phase < 1) {
			phase = 1;
			fireTornado();
			startPhase();
		} if (hpPercentage == 50 && phase < 2) {
			phase = 2;
			fireTornado();
			startPhase();
		} if (hpPercentage == 20 && phase < 3) {
			phase = 3;
			fireTornado();
			startPhase();
		}
	}
	
	private void fireTornado() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(243961, 679.88f, 1068.88f, 497.88f, (byte) 0); //IDF6_LF1_Thor_SumStatue_PhyAtk.
			}
		}, 5000);
	}
	
	private void startPhase() {
		AI2Actions.useSkill(this, 20060); //Lava Eruption.
	}
	
	private void startParalyze() {
		AI2Actions.useSkill(this, 20761); //Flame Terror.
	}
	
	private void schedule() {
		if (isAlreadyDead()) {
			return;
		}
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				startParalyze();
			}
		}, 10000);
	}
	
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(243961)); //IDF6_LF1_Thor_SumStatue_PhyAtk.
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
}
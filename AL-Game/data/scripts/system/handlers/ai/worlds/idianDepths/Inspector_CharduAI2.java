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
package ai.worlds.idianDepths;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("inspector_chardu")
public class Inspector_CharduAI2 extends AggressiveNpcAI2
{
	private int stage = 0;
	private boolean isStart = false;
	private Future<?> enrageTask;
	
	@Override
	protected void handleCreatureAggro(Creature creature) {
		super.handleCreatureAggro(creature);
		wakeUp();
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
		wakeUp();
	}
	
	private void wakeUp() {
		isStart = true;
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 90 && stage < 1) {
			stage1();
			stage = 1;
		} if (hpPercentage <= 50 && stage < 2) {
			stage2();
			stage = 2;
		} if (hpPercentage <= 20 && stage < 3) {
			stage3();
			stage = 3;
		}
	}
	
	private void stage1() {
		int delay = 0;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
			SkillEngine.getInstance().getSkill(getOwner(), 21135, 60, getOwner()).useNoAnimationSkill(); //Beritra's Favor.
		}
	}
	
	private void stage2() {
		int delay = 35000;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
		    skill();
			scheduleDelayStage2(delay);
		}
	}	
	
	private void skill() {
		SkillEngine.getInstance().getSkill(getOwner(), 18158, 100, getOwner()).useNoAnimationSkill(); //Wrathful Venom Burst.
		   ThreadPoolManager.getInstance().schedule(new Runnable() {
			public void run() {
                SkillEngine.getInstance().getSkill(getOwner(), 18160, 100, getOwner()).useNoAnimationSkill(); //Virulence.
			}
		}, 4000);
	}
	
	private void scheduleDelayStage2(int delay) {
		if (!isStart && !isAlreadyDead()) {
			return;
		} else {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					stage2();
				}
			}, delay);
		}
	}
	
	private void stage3() {
		int delay = 15000;
		if (isAlreadyDead() || !isStart) {
			return;
		} else {
			scheduleDelayStage3(delay);
		}
	}
	
	private void scheduleDelayStage3(int delay) {
		if (!isStart && !isAlreadyDead()) {
			return;
		} else {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					getRandomTarget();
					stage3();
				}
			}, delay);
		}
	}
	
    private void getRandomTarget()  {
        List<Player> players = new ArrayList<Player>();
        for (Player player : getKnownList().getKnownPlayers().values()) {
            if (!PlayerActions.isAlreadyDead(player) && MathUtil.isIn3dRange(player, getOwner(), 16)) {
                players.add(player);
			}
        } if (players.isEmpty()) {
            return;
		}
        getAggroList().clear();
        getAggroList().startHate(players.get(Rnd.get(0, players.size() - 1)));
    }
	
	@Override
	protected void handleBackHome() {
        super.handleBackHome();
		isStart = false;
		stage = 0;
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		isStart = false;
		stage = 0;
	}
}
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
package ai.instance.empyreanCrucible;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.WorldPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("mage_preceptor")
public class MagePreceptorAI2 extends AggressiveNpcAI2
{
	private List<Integer> percents = new ArrayList<Integer>();
	
	@Override
	public void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void startEvent(int percent) {
		if (percent == 50 || percent == 25) {
			SkillEngine.getInstance().getSkill(getOwner(), 19606, 46, getTarget()).useNoAnimationSkill();
		} switch (percent) {
			case 75:
				SkillEngine.getInstance().getSkill(getOwner(), 19605, 46, getTargetPlayer()).useNoAnimationSkill();
			break;
			case 50:
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						if (!isAlreadyDead()) {
							SkillEngine.getInstance().getSkill(getOwner(), 19609, 46, getOwner()).useNoAnimationSkill();	
							ThreadPoolManager.getInstance().schedule(new Runnable() {
								@Override
								public void run() {
									WorldPosition p = getPosition();
									switch (Rnd.get(1, 2)) {
										case 1:
										    spawn(282363, p.getX(), p.getY(), p.getZ(), p.getHeading()); //Summoned Tran Of Fire.
										break;
										case 2:
										    spawn(282364, p.getX(), p.getY(), p.getZ(), p.getHeading()); //Summoned Tran Of Wind.
										break;
									}
									scheduleSkill(2000);
								}
							}, 4500);
						}
					}
				}, 3000);
			break;
			case 25:
				scheduleSkill(3000);
				scheduleSkill(9000);
				scheduleSkill(15000);
			break;
		}
	}
	
	private void scheduleSkill(int delay) {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				if (!isAlreadyDead()) {
					SkillEngine.getInstance().getSkill(getOwner(), 19605, 46, getTargetPlayer()).useNoAnimationSkill();
				}
			}
		}, delay);
	}
	
	private Player getTargetPlayer() {
		List<Player> players = new ArrayList<Player>();
		for (Player player : getKnownList().getKnownPlayers().values()) {
			if (!PlayerActions.isAlreadyDead(player) && MathUtil.isIn3dRange(player, getOwner(), 37)) {
				players.add(player);
			}
		}
		return players.get(Rnd.get(players.size()));
	}
	
	private void checkPercentage(int percentage) {
		for (Integer percent : percents) {
			if (percentage <= percent) {
				percents.remove(percent);
				startEvent(percent);
				break;
			}
		}
	}
	private void addPercents() {
		percents.clear();
		Collections.addAll(percents, new Integer[] {75, 50, 25});
	}
	
	@Override
	public void handleSpawned() {
		super.handleSpawned();
		addPercents();
	}
	
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(282363)); //Summoned Tran Of Fire.
			deleteNpcs(p.getWorldMapInstance().getNpcs(282364)); //Summoned Tran Of Wind.
		}
		super.handleDied();
	}
	
	@Override
	protected void handleBackHome() {
		final WorldPosition p = getPosition();
		if (p != null) {
			deleteNpcs(p.getWorldMapInstance().getNpcs(282363)); //Summoned Tran Of Fire.
			deleteNpcs(p.getWorldMapInstance().getNpcs(282364)); //Summoned Tran Of Wind.
		}
		addPercents();
		super.handleDied();
	}
	
	private void deleteNpcs(List<Npc> npcs) {
		for (Npc npc: npcs) {
			if (npc != null) {
				npc.getController().onDelete();
			}
		}
	}
}
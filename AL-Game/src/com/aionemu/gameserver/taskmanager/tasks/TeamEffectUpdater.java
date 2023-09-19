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
package com.aionemu.gameserver.taskmanager.tasks;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.alliance.PlayerAllianceService;
import com.aionemu.gameserver.model.team2.common.legacy.GroupEvent;
import com.aionemu.gameserver.model.team2.common.legacy.PlayerAllianceEvent;
import com.aionemu.gameserver.model.team2.group.PlayerGroupService;
import com.aionemu.gameserver.taskmanager.AbstractIterativePeriodicTaskManager;

public final class TeamEffectUpdater extends AbstractIterativePeriodicTaskManager<Player>
{
	private static final class SingletonHolder {
		private static final TeamEffectUpdater INSTANCE = new TeamEffectUpdater();
	}
	
	public static TeamEffectUpdater getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public TeamEffectUpdater() {
		super(500);
	}
	
	@Override
	protected void callTask(Player player) {
		if (player.isOnline()) {
			if (player.isInGroup2()) {
				PlayerGroupService.updateGroup(player, GroupEvent.UPDATE);
				PlayerGroupService.updateGroup(player, GroupEvent.UNK_53);
			} if (player.isInAlliance2()) {
				PlayerAllianceService.updateAlliance(player, PlayerAllianceEvent.UPDATE);
			}
		}
		this.stopTask(player);
	}
	
	@Override
	protected String getCalledMethodName() {
		return "teamEffectUpdate()";
	}
}
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
package com.aionemu.gameserver.services.agentservice;

import com.aionemu.gameserver.model.agent.AgentLocation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.AgentService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class AgentStartRunnable implements Runnable
{
	private final int id;
	
	public AgentStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//The Agent battle will start in 10 minutes.
		AgentService.getInstance().agentBattleMsg1(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //The Agent battle will start in 5 minutes.
				AgentService.getInstance().agentBattleMsg2(id);
			}
		}, 300000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, AgentLocation> locations = AgentService.getInstance().getAgentLocations();
				for (final AgentLocation loc: locations.values()) {
					if (loc.getId() == id) {
						//Governor Sunayaka 5.8
						AgentService.getInstance().governorSunayakaMsg(id);
						//Berserker Sunayaka 5.8
						AgentService.getInstance().berserkerSunayakaMsg(id);
						//Agent Fight 4.7
						AgentService.getInstance().startAgentFight(loc.getId());
					}
				}
				World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						//An Agent has spawned.
						PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF4_Advance_GodElite);
					}
				});
			}
		}, 600000);
	}
}
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
import com.aionemu.gameserver.model.agent.AgentStateType;

/**
 * @author Rinzler (Encom)
 */

public class Fight extends AgentFight<AgentLocation>
{
	public Fight(AgentLocation agent) {
		super(agent);
	}
	
	@Override
	public void startAgentFight() {
		getAgentLocation().setActiveAgent(this);
		despawn();
		spawn(AgentStateType.FIGHT);
	}
	
	@Override
	public void stopAgentFight() {
		getAgentLocation().setActiveAgent(null);
		despawn();
		spawn(AgentStateType.PEACE);
	}
}
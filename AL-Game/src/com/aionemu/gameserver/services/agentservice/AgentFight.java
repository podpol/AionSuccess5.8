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
import com.aionemu.gameserver.services.AgentService;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rinzler (Encom)
 */

public abstract class AgentFight<AL extends AgentLocation>
{
	private boolean started;
	private final AL agentLocation;
	protected abstract void stopAgentFight();
	protected abstract void startAgentFight();
	private final AtomicBoolean finished = new AtomicBoolean();
	
	public AgentFight(AL agentLocation) {
		this.agentLocation = agentLocation;
	}
	
	public final void start() {
		boolean doubleStart = false;
		synchronized (this) {
			if (started) {
				doubleStart = true;
			} else {
				started = true;
			}
		} if (doubleStart) {
			return;
		}
		startAgentFight();
	}
	
	public final void stop() {
		if (finished.compareAndSet(false, true)) {
			stopAgentFight();
		}
	}
	
	protected void spawn(AgentStateType type) {
		AgentService.getInstance().spawn(getAgentLocation(), type);
	}
	
	protected void despawn() {
		AgentService.getInstance().despawn(getAgentLocation());
	}
	
	public boolean isFinished() {
		return finished.get();
	}
	
	public AL getAgentLocation() {
		return agentLocation;
	}
	
	public int getAgentLocationId() {
		return agentLocation.getId();
	}
}
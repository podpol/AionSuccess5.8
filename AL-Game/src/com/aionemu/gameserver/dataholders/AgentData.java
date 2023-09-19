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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.agent.AgentLocation;
import com.aionemu.gameserver.model.templates.agent.AgentTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "agent_fight")
public class AgentData
{
	@XmlElement(name = "agent_location")
	private List<AgentTemplate> agentTemplates;
	
	@XmlTransient
	private FastMap<Integer, AgentLocation> agent = new FastMap<Integer, AgentLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (AgentTemplate template : agentTemplates) {
			agent.put(template.getId(), new AgentLocation(template));
		}
	}
	
	public int size() {
		return agent.size();
	}
	
	public FastMap<Integer, AgentLocation> getAgentLocations() {
		return agent;
	}
}
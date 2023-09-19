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
package com.aionemu.gameserver.model.templates.spawns.basespawns;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.spawns.Spawn;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseSpawn")
public class BaseSpawn
{
	@XmlAttribute(name = "id")
	private int id;
	
	@XmlAttribute(name = "world")
	private int world;
	
	@XmlElement(name = "simple_race")
	private List<SimpleRaceTemplate> simpleRaceTemplates;
	
	public int getId() {
		return id;
	}
	
	public int getWorldId() {
		return world;
	}
	
	public List<SimpleRaceTemplate> getBaseRaceTemplates() {
		return simpleRaceTemplates;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "BaseRaceTemplate")
	public static class SimpleRaceTemplate {
		@XmlAttribute(name = "race")
		private Race race;
		
		public Race getBaseRace() {
			return race;
		}
		
		@XmlElement(name = "spawn")
		private List<Spawn> spawns;
		
		public List<Spawn> getSpawns() {
			return spawns;
		}
	}
}
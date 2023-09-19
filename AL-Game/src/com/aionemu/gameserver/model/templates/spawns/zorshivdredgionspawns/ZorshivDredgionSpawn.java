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
package com.aionemu.gameserver.model.templates.spawns.zorshivdredgionspawns;

import com.aionemu.gameserver.model.templates.spawns.Spawn;
import com.aionemu.gameserver.model.zorshivdredgion.ZorshivDredgionStateType;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZorshivDredgionSpawn")
public class ZorshivDredgionSpawn
{
	@XmlAttribute(name = "id")
	private int id;
	
	public int getId() {
		return id;
	}
	
	@XmlElement(name = "zorshiv_dredgion_type")
	private List<ZorshivDredgionSpawn.ZorshivDredgionStateTemplate> ZorshivDredgionStateTemplate;
	
	public List<ZorshivDredgionStateTemplate> getSiegeModTemplates() {
		return ZorshivDredgionStateTemplate;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "ZorshivDredgionStateTemplate")
	public static class ZorshivDredgionStateTemplate {
	
		@XmlElement(name = "spawn")
		private List<Spawn> spawns;
		
		@XmlAttribute(name = "zstate")
		private ZorshivDredgionStateType zorshivDredgionType;
		
		public List<Spawn> getSpawns() {
			return spawns;
		}
		
		public ZorshivDredgionStateType getZorshivDredgionType() {
			return zorshivDredgionType;
		}
	}
}
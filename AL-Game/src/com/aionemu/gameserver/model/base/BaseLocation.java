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
package com.aionemu.gameserver.model.base;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.base.BaseTemplate;

/**
 * @author Rinzler
 */

public class BaseLocation
{
	protected BaseTemplate template;
	protected Race race = Race.NPC;
	
	public BaseLocation() {
	}
	
	public BaseLocation(BaseTemplate template) {
		this.template = template;
	}
	
	public int getId() {
		return template.getId();
	}
	
	public int getWorldId() {
		return template.getWorldId();
	}
	
	public String getName() {
		return template.getName();
	}
	
	public Race getRace() {
		return race;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
}
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
package com.aionemu.gameserver.model.dynamicrift;

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.dynamicrift.DynamicRiftTemplate;
import com.aionemu.gameserver.services.dynamicriftservice.DynamicRift;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

public class DynamicRiftLocation
{
	protected int id;
	protected boolean isActive;
	protected DynamicRiftTemplate template;
	protected DynamicRift<DynamicRiftLocation> activeDynamicRift;
	protected FastMap<Integer, Player> players = new FastMap<Integer, Player>();
	private final List<VisibleObject> spawned = new ArrayList<VisibleObject>();
	
	public DynamicRiftLocation() {
	}
	
	public DynamicRiftLocation(DynamicRiftTemplate template) {
		this.template = template;
		this.id = template.getId();
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActiveDynamicRift(DynamicRift<DynamicRiftLocation> dynamicRift) {
		isActive = dynamicRift != null;
		this.activeDynamicRift = dynamicRift;
	}
	
	public DynamicRift<DynamicRiftLocation> getActiveDynamicRift() {
		return activeDynamicRift;
	}
	
	public final DynamicRiftTemplate getTemplate() {
		return template;
	}
	
	public int getId() {
		return id;
	}
	
	public List<VisibleObject> getSpawned() {
		return spawned;
	}
	
	public FastMap<Integer, Player> getPlayers() {
		return players;
	}
}
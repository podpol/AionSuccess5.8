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
package com.aionemu.gameserver.model.nightmarecircus;

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.nightmarecircus.NightmareCircusTemplate;
import com.aionemu.gameserver.services.nightmarecircusservice.CircusInstance;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

public class NightmareCircusLocation
{
	protected int id;
	protected boolean isActive;
	protected NightmareCircusTemplate template;
	protected CircusInstance<NightmareCircusLocation> activeNightmareCircus;
	protected FastMap<Integer, Player> players = new FastMap<Integer, Player>();
	private final List<VisibleObject> spawned = new ArrayList<VisibleObject>();
	
	public NightmareCircusLocation() {
	}
	
	public NightmareCircusLocation(NightmareCircusTemplate template) {
		this.template = template;
		this.id = template.getId();
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActiveNightmareCircus(CircusInstance<NightmareCircusLocation> nightmareCircus) {
		isActive = nightmareCircus != null;
		this.activeNightmareCircus = nightmareCircus;
	}
	
	public CircusInstance<NightmareCircusLocation> getActiveNightmareCircus() {
		return activeNightmareCircus;
	}
	
	public final NightmareCircusTemplate getTemplate() {
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
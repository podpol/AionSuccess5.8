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
package com.aionemu.gameserver.model.zorshivdredgion;

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.zorshivdredgion.ZorshivDredgionTemplate;
import com.aionemu.gameserver.services.zorshivdredgionservice.ZorshivDredgion;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

public class ZorshivDredgionLocation
{
	protected int id;
	protected boolean isActive;
	protected ZorshivDredgionTemplate template;
	protected ZorshivDredgion<ZorshivDredgionLocation> activeZorshivDredgion;
	protected FastMap<Integer, Player> players = new FastMap<Integer, Player>();
	private final List<VisibleObject> spawned = new ArrayList<VisibleObject>();
	
	public ZorshivDredgionLocation() {
	}
	
	public ZorshivDredgionLocation(ZorshivDredgionTemplate template) {
		this.template = template;
		this.id = template.getId();
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActiveZorshivDredgion(ZorshivDredgion<ZorshivDredgionLocation> zorshivDredgion) {
		isActive = zorshivDredgion != null;
		this.activeZorshivDredgion = zorshivDredgion;
	}
	
	public ZorshivDredgion<ZorshivDredgionLocation> getActiveZorshivDredgion() {
		return activeZorshivDredgion;
	}
	
	public final ZorshivDredgionTemplate getTemplate() {
		return template;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return template.getName();
	}
	
	public List<VisibleObject> getSpawned() {
		return spawned;
	}
	
	public FastMap<Integer, Player> getPlayers() {
		return players;
	}
}
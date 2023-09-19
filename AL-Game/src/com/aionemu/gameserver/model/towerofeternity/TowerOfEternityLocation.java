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
package com.aionemu.gameserver.model.towerofeternity;

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.towerofeternity.TowerOfEternityTemplate;
import com.aionemu.gameserver.services.towerofeternityservice.TowerOfEternity;
import javolution.util.FastMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wnkrz on 22/08/2017.
 */

public class TowerOfEternityLocation
{
    protected int id;
    protected boolean isActive;
    protected TowerOfEternityTemplate template;
    protected TowerOfEternity<TowerOfEternityLocation> activeTowerOfEternity;
    protected FastMap<Integer, Player> players = new FastMap<Integer, Player>();
    private final List<VisibleObject> spawned = new ArrayList<VisibleObject>();
	
    public TowerOfEternityLocation() {
    }
	
    public TowerOfEternityLocation(TowerOfEternityTemplate template) {
        this.template = template;
        this.id = template.getId();
    }
	
    public boolean isActive() {
        return isActive;
    }
	
    public void setActiveTowerOfEternity(TowerOfEternity<TowerOfEternityLocation> towerOfEternity) {
        isActive = towerOfEternity != null;
        this.activeTowerOfEternity = towerOfEternity;
    }
	
    public int getWorldId() {
        return template.getWorldId();
    }
	
    public TowerOfEternity<TowerOfEternityLocation> getActiveTowerOfEternity() {
        return activeTowerOfEternity;
    }
	
    public final TowerOfEternityTemplate getTemplate() {
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
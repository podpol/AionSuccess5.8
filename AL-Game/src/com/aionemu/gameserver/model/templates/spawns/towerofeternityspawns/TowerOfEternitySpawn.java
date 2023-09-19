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
package com.aionemu.gameserver.model.templates.spawns.towerofeternityspawns;

import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityStateType;
import com.aionemu.gameserver.model.templates.spawns.Spawn;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Wnkrz on 23/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TowerOfEternitySpawn")
public class TowerOfEternitySpawn
{
    @XmlAttribute(name = "id")
    private int id;
	
    public int getId() {
        return id;
    }
	
    @XmlElement(name = "tower_of_eternity_type")
    private List<TowerOfEternitySpawn.TowerOfEternityStateTemplate> TowerOfEternityStateTemplate;
	
    public List<TowerOfEternityStateTemplate> getSiegeModTemplates() {
        return TowerOfEternityStateTemplate;
    }
	
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TowerOfEternityStateTemplate")
    public static class TowerOfEternityStateTemplate {
		
        @XmlElement(name = "spawn")
        private List<Spawn> spawns;
		
        @XmlAttribute(name = "tstate")
        private TowerOfEternityStateType towerOfEternityType;
		
        public List<Spawn> getSpawns() {
            return spawns;
        }
		
        public TowerOfEternityStateType getTowerOfEternityType() {
            return towerOfEternityType;
        }
    }
}
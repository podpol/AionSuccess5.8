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

import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityLocation;
import com.aionemu.gameserver.model.templates.towerofeternity.TowerOfEternityTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Wnkrz on 22/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tower_of_eternity")
public class TowerOfEternityData
{
    @XmlElement(name = "tower_location")
    private List<TowerOfEternityTemplate> towerOfEternityTemplates;
	
    @XmlTransient
    private FastMap<Integer, TowerOfEternityLocation> towerOfEternity = new FastMap<Integer, TowerOfEternityLocation>();
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (TowerOfEternityTemplate template : towerOfEternityTemplates) {
            towerOfEternity.put(template.getId(), new TowerOfEternityLocation(template));
        }
    }
	
    public int size() {
        return towerOfEternity.size();
    }
	
    public FastMap<Integer, TowerOfEternityLocation> getTowerOfEternityLocations() {
        return towerOfEternity;
    }
}
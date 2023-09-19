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

import com.aionemu.gameserver.model.templates.item.ItemCustomSetTeamplate;
import com.aionemu.gameserver.model.templates.revive_start_points.InstanceReviveStartPoints;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wnkrz on 27/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"InstanceStartPoints"})
@XmlRootElement(name = "instance_revive_start_points")
public class ReviveInstanceStartPointsData
{
    @XmlElement(name = "instance_revive_start_point")
    protected List<InstanceReviveStartPoints> InstanceStartPoints;
	
    @XmlTransient
    private TIntObjectHashMap<InstanceReviveStartPoints> custom = new TIntObjectHashMap<InstanceReviveStartPoints>();
	
    public InstanceReviveStartPoints getReviveStartPoint(int worldId) {
        return custom.get(worldId);
    }
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (InstanceReviveStartPoints it : InstanceStartPoints) {
            getCustomMap().put(it.getReviveWorld(), it);
        }
    }
	
    private TIntObjectHashMap<InstanceReviveStartPoints> getCustomMap() {
        return custom;
    }
	
    public int size() {
        return custom.size();
    }
}
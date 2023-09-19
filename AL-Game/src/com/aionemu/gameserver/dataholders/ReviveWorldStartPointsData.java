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

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.revive_start_points.WorldReviveStartPoints;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wnkrz on 22/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"WorldStartPoints"})
@XmlRootElement(name = "revive_world_start_points")
public class ReviveWorldStartPointsData
{
    @XmlElement(name = "revive_world_start_point")
    protected List<WorldReviveStartPoints> WorldStartPoints;
	
    @XmlTransient
    protected List<WorldReviveStartPoints> StartPointsList = new ArrayList<WorldReviveStartPoints>();
	
    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        for (WorldReviveStartPoints exit : WorldStartPoints) {
            StartPointsList.add(exit);
        }
        WorldStartPoints.clear();
        WorldStartPoints = null;
    }
	
    public WorldReviveStartPoints getReviveStartPoint(int worldId, Race race, int playerLevel) {
        for (WorldReviveStartPoints revive : StartPointsList) {
            if (revive.getReviveWorld() == worldId && (race.equals(revive.getRace()) ||
			    revive.getRace().equals(Race.PC_ALL)) && playerLevel >=
				revive.getMinlevel() && playerLevel <= revive.getMaxlevel()) {
                return revive;
            }
        }
        return null;
    }
	
    public int size() {
        return StartPointsList.size();
    }
}
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

import com.aionemu.gameserver.model.outpost.OutpostLocation;
import com.aionemu.gameserver.model.templates.outpost.OutpostTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Wnkrz on 27/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "outpost_locations")
public class OutpostData
{
    @XmlElement(name = "outpost_location")
    private List<OutpostTemplate> outpostTemplates;
    @XmlTransient
    private FastMap<Integer, OutpostLocation> out = new FastMap<Integer, OutpostLocation>();
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (OutpostTemplate template : outpostTemplates) {
            out.put(template.getId(), new OutpostLocation(template));
        }
    }
	
    public int size() {
        return out.size();
    }
	
    public FastMap<Integer, OutpostLocation> getOutpostLocations() {
        return out;
    }
}
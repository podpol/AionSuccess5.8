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

import com.aionemu.gameserver.model.legiondominion.LegionDominionLocation;
import com.aionemu.gameserver.model.templates.legiondominion.LegionDominionTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dominion_locations")
public class LegionDominionData
{
	@XmlElement(name = "dominion_location")
	private List<LegionDominionTemplate> legionDominionTemplates;
	
	@XmlTransient
	private FastMap<Integer, LegionDominionLocation> legionDominion = new FastMap<Integer, LegionDominionLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (LegionDominionTemplate template : legionDominionTemplates) {
			legionDominion.put(template.getLegionDominionId(), new LegionDominionLocation(template));
		}
	}
	
	public int size() {
		return legionDominion.size();
	}
	
	public FastMap<Integer, LegionDominionLocation> getLegionDominionLocations() {
		return legionDominion;
	}
}
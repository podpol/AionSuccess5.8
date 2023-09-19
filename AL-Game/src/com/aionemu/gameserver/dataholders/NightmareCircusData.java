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

import com.aionemu.gameserver.model.nightmarecircus.NightmareCircusLocation;
import com.aionemu.gameserver.model.templates.nightmarecircus.NightmareCircusTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "nightmare_circus")
public class NightmareCircusData
{
	@XmlElement(name = "nightmare_location")
	private List<NightmareCircusTemplate> nightmareCircusTemplates;
	
	@XmlTransient
	private FastMap<Integer, NightmareCircusLocation> nightmareCircus = new FastMap<Integer, NightmareCircusLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (NightmareCircusTemplate template : nightmareCircusTemplates) {
			nightmareCircus.put(template.getId(), new NightmareCircusLocation(template));
		}
	}
	
	public int size() {
		return nightmareCircus.size();
	}
	
	public FastMap<Integer, NightmareCircusLocation> getNightmareCircusLocations() {
		return nightmareCircus;
	}
}
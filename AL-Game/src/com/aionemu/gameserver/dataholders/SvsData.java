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

import com.aionemu.gameserver.model.svs.SvsLocation;
import com.aionemu.gameserver.model.templates.svs.SvsTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "svs")
public class SvsData
{
	@XmlElement(name = "svs_location")
	private List<SvsTemplate> svsTemplates;
	
	@XmlTransient
	private FastMap<Integer, SvsLocation> svs = new FastMap<Integer, SvsLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (SvsTemplate template : svsTemplates) {
			svs.put(template.getId(), new SvsLocation(template));
		}
	}
	
	public int size() {
		return svs.size();
	}
	
	public FastMap<Integer, SvsLocation> getSvsLocations() {
		return svs;
	}
}
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

import com.aionemu.gameserver.model.conquest.ConquestLocation;
import com.aionemu.gameserver.model.templates.conquest.ConquestTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "conquest")
public class ConquestData
{
	@XmlElement(name = "conquest_location")
	private List<ConquestTemplate> conquestTemplates;
	
	@XmlTransient
	private FastMap<Integer, ConquestLocation> conquest = new FastMap<Integer, ConquestLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (ConquestTemplate template : conquestTemplates) {
			conquest.put(template.getId(), new ConquestLocation(template));
		}
	}
	
	public int size() {
		return conquest.size();
	}
	
	public FastMap<Integer, ConquestLocation> getConquestLocations() {
		return conquest;
	}
}
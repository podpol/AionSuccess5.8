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

import com.aionemu.gameserver.model.dynamicrift.DynamicRiftLocation;
import com.aionemu.gameserver.model.templates.dynamicrift.DynamicRiftTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dynamic_rift")
public class DynamicRiftData
{
	@XmlElement(name = "dynamic_location")
	private List<DynamicRiftTemplate> dynamicRiftTemplates;
	
	@XmlTransient
	private FastMap<Integer, DynamicRiftLocation> dynamicRift = new FastMap<Integer, DynamicRiftLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (DynamicRiftTemplate template : dynamicRiftTemplates) {
			dynamicRift.put(template.getId(), new DynamicRiftLocation(template));
		}
	}
	
	public int size() {
		return dynamicRift.size();
	}
	
	public FastMap<Integer, DynamicRiftLocation> getDynamicRiftLocations() {
		return dynamicRift;
	}
}
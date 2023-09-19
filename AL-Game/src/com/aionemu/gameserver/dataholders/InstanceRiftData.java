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

import com.aionemu.gameserver.model.instancerift.InstanceRiftLocation;
import com.aionemu.gameserver.model.templates.instancerift.InstanceRiftTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "instance_rift")
public class InstanceRiftData
{
	@XmlElement(name = "instance_location")
	private List<InstanceRiftTemplate> instanceRiftTemplates;
	
	@XmlTransient
	private FastMap<Integer, InstanceRiftLocation> instanceRift = new FastMap<Integer, InstanceRiftLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (InstanceRiftTemplate template : instanceRiftTemplates) {
			instanceRift.put(template.getId(), new InstanceRiftLocation(template));
		}
	}
	
	public int size() {
		return instanceRift.size();
	}
	
	public FastMap<Integer, InstanceRiftLocation> getInstanceRiftLocations() {
		return instanceRift;
	}
}
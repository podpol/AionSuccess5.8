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

import com.aionemu.gameserver.model.templates.teleport.HotspotlocationTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "hotspot_location")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotspotLocationData
{
	@XmlElement(name = "hotspot_template")
	private List<HotspotlocationTemplate> hslist;
	
	private TIntObjectHashMap<HotspotlocationTemplate> lochslistData = new TIntObjectHashMap<HotspotlocationTemplate>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (HotspotlocationTemplate loc: hslist) {
			lochslistData.put(loc.getLocId(), loc);
		}
	}
	
	public int size() {
		return lochslistData.size();
	}
	
	public HotspotlocationTemplate getHotspotlocationTemplate(int id) {
		return lochslistData.get(id);
	}
}
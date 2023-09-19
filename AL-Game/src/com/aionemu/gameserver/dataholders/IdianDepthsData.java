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


import com.aionemu.gameserver.model.idiandepths.IdianDepthsLocation;
import com.aionemu.gameserver.model.templates.idiandepths.IdianDepthsTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "idian_depths")
public class IdianDepthsData
{
	@XmlElement(name = "idian_location")
	private List<IdianDepthsTemplate> idianDepthsTemplates;
	
	@XmlTransient
	private FastMap<Integer, IdianDepthsLocation> idianDepths = new FastMap<Integer, IdianDepthsLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (IdianDepthsTemplate template : idianDepthsTemplates) {
			idianDepths.put(template.getId(), new IdianDepthsLocation(template));
		}
	}
	
	public int size() {
		return idianDepths.size();
	}
	
	public FastMap<Integer, IdianDepthsLocation> getIdianDepthsLocations() {
		return idianDepths;
	}
}
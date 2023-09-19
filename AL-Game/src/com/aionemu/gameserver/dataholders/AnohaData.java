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

import com.aionemu.gameserver.model.anoha.AnohaLocation;
import com.aionemu.gameserver.model.templates.anoha.AnohaTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anoha")
public class AnohaData
{
	@XmlElement(name = "anoha_location")
	private List<AnohaTemplate> anohaTemplates;
	
	@XmlTransient
	private FastMap<Integer, AnohaLocation> anoha = new FastMap<Integer, AnohaLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (AnohaTemplate template : anohaTemplates) {
			anoha.put(template.getId(), new AnohaLocation(template));
		}
	}
	
	public int size() {
		return anoha.size();
	}
	
	public FastMap<Integer, AnohaLocation> getAnohaLocations() {
		return anoha;
	}
}
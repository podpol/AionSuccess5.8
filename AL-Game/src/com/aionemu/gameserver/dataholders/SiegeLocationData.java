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

import com.aionemu.gameserver.model.siege.ArtifactLocation;
import com.aionemu.gameserver.model.siege.FortressLocation;
import com.aionemu.gameserver.model.siege.SiegeLocation;
import com.aionemu.gameserver.model.templates.siegelocation.SiegeLocationTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "siege_locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiegeLocationData
{
	@XmlElement(name = "siege_location")
	private List<SiegeLocationTemplate> siegeLocationTemplates;
	
	@XmlTransient
	private FastMap<Integer, ArtifactLocation> artifactLocations = new FastMap<Integer, ArtifactLocation>();
	@XmlTransient
	private FastMap<Integer, FortressLocation> fortressLocations = new FastMap<Integer, FortressLocation>();
	@XmlTransient
	private FastMap<Integer, SiegeLocation> siegeLocations = new FastMap<Integer, SiegeLocation>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		artifactLocations.clear();
		fortressLocations.clear();
		siegeLocations.clear();
		for (SiegeLocationTemplate template : siegeLocationTemplates) {
			switch (template.getType()) {
				case FORTRESS:
					FortressLocation fortress = new FortressLocation(template);
					fortressLocations.put(template.getId(), fortress);
					siegeLocations.put(template.getId(), fortress);
					artifactLocations.put(template.getId(), new ArtifactLocation(template));
				break;
				case ARTIFACT:
					ArtifactLocation artifact = new ArtifactLocation(template);
					artifactLocations.put(template.getId(), artifact);
					siegeLocations.put(template.getId(), artifact);
				break;
				default:
				break;
			}
		}
	}
	
	public int size() {
		return siegeLocations.size();
	}
	
	public FastMap<Integer, ArtifactLocation> getArtifacts() {
		return artifactLocations;
	}
	
	public FastMap<Integer, FortressLocation> getFortress() {
		return fortressLocations;
	}
	
	public FastMap<Integer, SiegeLocation> getSiegeLocations() {
		return siegeLocations;
	}
}
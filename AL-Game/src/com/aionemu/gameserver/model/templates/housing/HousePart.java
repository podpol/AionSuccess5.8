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
package com.aionemu.gameserver.model.templates.housing;

import com.aionemu.gameserver.model.templates.item.ItemQuality;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "house_part")
public class HousePart {

	@XmlAttribute(name = "building_tags", required = true)
	private List<String> buildingTags;

	@XmlAttribute(required = true)
	protected PartType type;
	
	@XmlAttribute(required = true)
	protected ItemQuality quality;

	@XmlAttribute
	protected String name;

	@XmlAttribute(required = true)
	protected int id;

	@XmlTransient
	protected Set<String> tagsSet = new HashSet<String>(1);

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (buildingTags == null)
			return;

		for (String tag : buildingTags)
			tagsSet.add(tag);

		buildingTags.clear();
		buildingTags = null;
	}
	
	public PartType getType() {
		return type;
	}

	public ItemQuality getQuality() {
		return quality;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public Set<String> getTags() {
		return tagsSet;
	}

	public boolean isForBuilding(Building building) {
		return tagsSet.contains(building.getPartsMatchTag());
	}

}

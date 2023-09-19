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

import com.aionemu.gameserver.model.templates.housing.Building;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "buildings" })
public class HouseBuildingData {

	@XmlElement(name = "building")
	protected List<Building> buildings;

	@XmlTransient
	Map<Integer, Building> buildingById = new HashMap<Integer, Building>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (buildings == null)
			return;

		for (Building building : buildings)
			buildingById.put(building.getId(), building);

		buildings.clear();
		buildings = null;
	}

	public Building getBuilding(int buildingId) {
		return buildingById.get(buildingId);
	}
	
	public int size() {
		return buildingById.size();
	}
}

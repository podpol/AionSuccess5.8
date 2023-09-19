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
import com.aionemu.gameserver.model.templates.housing.HousePart;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "houseParts" })
@XmlRootElement(name = "house_parts")
public class HousePartsData {

	@XmlElement(name = "house_part")
	protected List<HousePart> houseParts;

	@XmlTransient
	Map<String, List<HousePart>> partsByTags = new HashMap<String, List<HousePart>>(5);

	@XmlTransient
	Map<Integer, HousePart> partsById = new HashMap<Integer, HousePart>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (houseParts == null)
			return;

		for (HousePart part : houseParts) {
			partsById.put(part.getId(), part);
			Iterator<String> iterator = part.getTags().iterator();
			while (iterator.hasNext()) {
				String tag = iterator.next();
				List<HousePart> parts = partsByTags.get(tag);
				if (parts == null) {
					parts = new ArrayList<HousePart>();
					partsByTags.put(tag, parts);
				}
				parts.add(part);
			}
		}

		houseParts.clear();
		houseParts = null;
	}

	public HousePart getPartById(int partId) {
		return partsById.get(partId);
	}

	public List<HousePart> getPartsForBuilding(Building building) {
		return partsByTags.get(building.getPartsMatchTag());
	}

	public int size() {
		return partsById.size();
	}

}

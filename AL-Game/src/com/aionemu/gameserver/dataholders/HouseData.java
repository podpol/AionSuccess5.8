package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.housing.Building;
import com.aionemu.gameserver.model.templates.housing.HouseAddress;
import com.aionemu.gameserver.model.templates.housing.HouseType;
import com.aionemu.gameserver.model.templates.housing.HousingLand;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "lands" })
@XmlRootElement(name = "house_lands")
public class HouseData {

	@XmlElement(name = "land")
	protected List<HousingLand> lands;

	@XmlTransient
	Map<Integer, HousingLand> landsById = new HashMap<Integer, HousingLand>();

	@XmlTransient
	Map<Integer, Set<HousingLand>> landsByEntryWorldId = new HashMap<Integer, Set<HousingLand>>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (lands == null) {
			return;
		}
		for (HousingLand land : lands) {
			landsById.put(land.getId(), land);
			for (HouseAddress address : land.getAddresses()) {
				Integer exitMapId = address.getExitMapId();
				if (exitMapId == null)
					exitMapId = Integer.valueOf(address.getMapId());
				Set<HousingLand> landList = landsByEntryWorldId.get(exitMapId);
				if (landList == null) {
					landList = new HashSet<HousingLand>();
					landsByEntryWorldId.put(exitMapId, landList);
				}
				landList.add(land);
			}
		}
		lands.clear();
		lands = null;
	}

	public Set<HousingLand> getLandsForWorldId(int worldId) {
		return landsByEntryWorldId.get(worldId);
	}

	public HousingLand getLandForHouse(int worldId, HouseType houseSize) {
		Set<HousingLand> worldHouseAreas = landsByEntryWorldId.get(worldId);
		if (worldHouseAreas == null)
			return null;
		for (HousingLand land : worldHouseAreas) {
			for (Building building : land.getBuildings())
				if (houseSize.value().equals(building.getSize()))
					return land;
		}
		return null;
	}

	public HousingLand getLand(int landId) {
		return landsById.get(landId);
	}

	public Collection<HousingLand> getLands() {
		return landsById.values();
	}

	public int size() {
		return landsById.size();
	}
}

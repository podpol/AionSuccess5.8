package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.spawns.HouseSpawn;
import com.aionemu.gameserver.model.templates.spawns.HouseSpawns;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "houseSpawnsData" })
@XmlRootElement(name = "house_npcs")
public class HouseNpcsData {

	@XmlElement(name = "house")
	protected List<HouseSpawns> houseSpawnsData;

	@XmlTransient
	private TIntObjectHashMap<List<HouseSpawn>> houseSpawnsByAddressId = new TIntObjectHashMap<List<HouseSpawn>>();

	public List<HouseSpawns> getHouseSpawns() {
		if (houseSpawnsData == null) {
			houseSpawnsData = new ArrayList<HouseSpawns>();
		}
		return houseSpawnsData;
	}

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (HouseSpawns houseSpawns : getHouseSpawns())
			houseSpawnsByAddressId.put(houseSpawns.getAddress(), houseSpawns.getSpawns());
	}

	public List<HouseSpawn> getSpawnsByAddress(int address) {
		return houseSpawnsByAddressId.get(address);
	}

	public int size() {
		return houseSpawnsByAddressId.size() * 3;
	}
}

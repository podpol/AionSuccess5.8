package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.spawns.Spawn;
import com.aionemu.gameserver.model.templates.towns.TownLevel;
import com.aionemu.gameserver.model.templates.towns.TownSpawn;
import com.aionemu.gameserver.model.templates.towns.TownSpawnMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "town_spawns_data")
public class TownSpawnsData
{
	@XmlElement(name = "spawn_map")
	private List<TownSpawnMap> spawnMap;
	
	private TIntObjectHashMap<TownSpawnMap> spawnMapsData = new TIntObjectHashMap<TownSpawnMap>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		spawnMapsData.clear();
		for (TownSpawnMap map : spawnMap) {
			spawnMapsData.put(map.getMapId(), map);
		}
		spawnMap.clear();
		spawnMap = null;
	}
	
	public int getSpawnsCount() {
		int counter = 0;
		for (TownSpawnMap spawnMap : spawnMapsData.valueCollection())
			for (TownSpawn townSpawn : spawnMap.getTownSpawns())
				for (TownLevel townLevel : townSpawn.getTownLevels())
					counter+= townLevel.getSpawns().size();
		return counter;
	}
	
	public List<Spawn> getSpawns(int townId, int townLevel) {
		for(TownSpawnMap spawnMap : spawnMapsData.valueCollection()) {
			if(spawnMap.getTownSpawn(townId) != null) {
				TownSpawn townSpawn = spawnMap.getTownSpawn(townId);
				return townSpawn.getSpawnsForLevel(townLevel).getSpawns();
			}
		}
		return null;
	}
	
	public int getWorldIdForTown(int townId) {
		for(TownSpawnMap spawnMap : spawnMapsData.valueCollection())
			if(spawnMap.getTownSpawn(townId) != null)
				return spawnMap.getMapId();
		return 0;
	}
}
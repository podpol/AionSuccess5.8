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
package com.aionemu.gameserver.model.templates.towns;

import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;
import java.util.List;


/**
 * @author ViAl
 *
 */
@XmlType(name = "town_spawn_map")
public class TownSpawnMap {
	@XmlAttribute(name = "map_id")
	private int mapId;
	@XmlElement(name ="town_spawn")
	private List<TownSpawn> townSpawns;
	
	private TIntObjectHashMap<TownSpawn> townSpawnsData = new TIntObjectHashMap<TownSpawn>();
	
	/**
	 * @param u
	 * @param parent
	 */
	void afterUnmarshal(Unmarshaller u, Object parent) {
		townSpawnsData.clear();

		for (TownSpawn town : townSpawns) {
			townSpawnsData.put(town.getTownId(), town);
		}
		townSpawns.clear();
		townSpawns = null;
	}
	
	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}
	
	public TownSpawn getTownSpawn(int townId) {
		return townSpawnsData.get(townId);
	}
	
	public Collection<TownSpawn> getTownSpawns() {
		return townSpawnsData.valueCollection();
	}

}

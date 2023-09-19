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

import com.aionemu.gameserver.model.templates.world.WorldMapTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

/**
 * Object of this class is containing <tt>WorldMapTemplate</tt> objects for all world maps. World maps are defined in
 * data/static_data/world_maps.xml file.
 * 
 * @author Luno
 */
@XmlRootElement(name = "world_maps")
@XmlAccessorType(XmlAccessType.NONE)
public class WorldMapsData implements Iterable<WorldMapTemplate> {

	@XmlElement(name = "map")
	protected List<WorldMapTemplate> worldMaps;

	protected TIntObjectHashMap<WorldMapTemplate> worldIdMap = new TIntObjectHashMap<WorldMapTemplate>();

	protected void afterUnmarshal(Unmarshaller u, Object parent) {
		for (WorldMapTemplate map : worldMaps) {
			worldIdMap.put(map.getMapId(), map);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<WorldMapTemplate> iterator() {
		return worldMaps.iterator();
	}

	/**
	 * Returns the count of maps.
	 * 
	 * @return worldMaps.size()
	 */
	public int size() {
		return worldMaps == null ? 0 : worldMaps.size();
	}

	/**
	 * @param worldId
	 * @return
	 */
	public WorldMapTemplate getTemplate(int worldId) {
		return worldIdMap.get(worldId);
	}
}

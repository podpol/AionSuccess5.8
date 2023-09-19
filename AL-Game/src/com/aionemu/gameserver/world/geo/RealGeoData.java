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
package com.aionemu.gameserver.world.geo;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.geoEngine.GeoWorldLoader;
import com.aionemu.gameserver.geoEngine.models.GeoMap;
import com.aionemu.gameserver.geoEngine.scene.Spatial;
import com.aionemu.gameserver.model.templates.world.WorldMapTemplate;
import com.aionemu.gameserver.utils.Util;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ATracer
 */
public class RealGeoData implements GeoData {

	private static final Logger log = LoggerFactory.getLogger(RealGeoData.class);

	private TIntObjectHashMap<GeoMap> geoMaps = new TIntObjectHashMap<GeoMap>();

	@Override
	public void loadGeoMaps() {
		Map<String, Spatial> models = loadMeshes();
		loadWorldMaps(models);
		models.clear();
		models = null;
	}

	/**
	 * @param models
	 */
	protected void loadWorldMaps(Map<String, Spatial> models) {
		log.info("Loading geo maps..");
		Util.printProgressBarHeader(DataManager.WORLD_MAPS_DATA.size());
		List<Integer> mapsWithErrors = new ArrayList<Integer>();

		for (WorldMapTemplate map : DataManager.WORLD_MAPS_DATA) {
			GeoMap geoMap = new GeoMap(Integer.toString(map.getMapId()), map.getWorldSize());
			try {
				if (GeoWorldLoader.loadWorld(map.getMapId(), models, geoMap)) {
					geoMaps.put(map.getMapId(), geoMap);
				}
			}
			catch (Throwable t) {
				mapsWithErrors.add(map.getMapId());
				geoMaps.put(map.getMapId(), DummyGeoData.DUMMY_MAP);
			}
			Util.printCurrentProgress();
		}
		Util.printEndProgress();

		if (mapsWithErrors.size() > 0) {
		}
	}

	/**
	 * @return
	 */
	protected Map<String, Spatial> loadMeshes() {
		log.info("Loading meshes..");
		Map<String, Spatial> models = null;
		try {
			models = GeoWorldLoader.loadMeshs("data/geo/meshs.geo");
		}
		catch (IOException e) {
			throw new IllegalStateException("Problem loading meshes", e);
		}
		return models;
	}

	@Override
	public GeoMap getMap(int worldId) {
		GeoMap geoMap = geoMaps.get(worldId);
		return geoMap != null ? geoMap : DummyGeoData.DUMMY_MAP;
	}
}

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

import com.aionemu.gameserver.model.templates.world.WeatherTable;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "weatherData" })
@XmlRootElement(name = "weather")
public class MapWeatherData {

	@XmlElement(name = "map", required = true)
	private List<WeatherTable> weatherData;

	@XmlTransient
	private TIntObjectHashMap<WeatherTable> mapWeather;

	void afterUnmarshal(Unmarshaller u, Object parent) {
		mapWeather = new TIntObjectHashMap<WeatherTable>();

		for (WeatherTable table : weatherData) {
			mapWeather.put(table.getMapId(), table);
		}

		weatherData.clear();
		weatherData = null;
	}

	public WeatherTable getWeather(int mapId) {
		return mapWeather.get(mapId);
	}

	public int size() {
		return mapWeather.size();
	}

}

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
package com.aionemu.gameserver.model.templates.world;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherTable", propOrder = {"zoneData"})
public class WeatherTable
{
	@XmlElement(name = "table", required = true)
	protected List<WeatherEntry> zoneData;
	
	@XmlAttribute(name = "weather_count", required = true)
	protected int weatherCount;
	
	@XmlAttribute(name = "zone_count", required = true)
	protected int zoneCount;
	
	@XmlAttribute(name = "id", required = true)
	protected int mapId;
	
	public List<WeatherEntry> getZoneData() {
		return zoneData;
	}
	
	public int getMapId() {
		return mapId;
	}
	
	public int getZoneCount() {
		return zoneCount;
	}
	
	public int getWeatherCount() {
		return weatherCount;
	}
	
	public WeatherEntry getWeatherAfter(WeatherEntry entry) {
		if (entry.getWeatherName() == null || entry.isAfter())
			return null;
		for (WeatherEntry we: getZoneData()) {
			if (we.getZoneId() != entry.getZoneId())
				continue;
			if (entry.getWeatherName().equals(we.getWeatherName())) {
				if (entry.isBefore() && !we.isBefore() && !we.isAfter())
					return we;
				else if (!entry.isBefore() && !entry.isAfter() && we.isAfter())
					return we;
			}
		}
		return null;
	}
	
	public List<WeatherEntry> getWeathersForZone(int zoneId) {
		List<WeatherEntry> result = new ArrayList<WeatherEntry>();
		for (WeatherEntry entry : getZoneData()) {
			if (entry.getZoneId() == zoneId)
				result.add(entry);
		}
		return result;
	}
}
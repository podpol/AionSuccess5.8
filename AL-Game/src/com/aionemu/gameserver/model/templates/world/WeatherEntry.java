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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherEntry")
public class WeatherEntry
{
	public WeatherEntry() {
	}
	
	public WeatherEntry(int zoneId, int weatherCode) {
		this.weatherCode = weatherCode;
		this.zoneId = zoneId;
	}
	
	@XmlAttribute(name = "zone_id", required = true)
	private int zoneId;
	
	@XmlAttribute(name = "code", required = true)
	private int weatherCode;
	
	@XmlAttribute(name = "att_ranking", required = true)
	private int attRanking;
	
	@XmlAttribute(name = "name")
	private String weatherName;
	
	@XmlAttribute(name = "before")
	private Boolean isBefore;
	
	@XmlAttribute(name = "after")
	private Boolean isAfter;
	
	public int getZoneId() {
		return zoneId;
	}
	
	public int getCode() {
		return weatherCode;
	}
	
	public int getAttRanking() {
		return attRanking;
	}
	
	public Boolean isBefore() {
		if (isBefore == null)
			return false;
		return isBefore;
	}
	
	public Boolean isAfter() {
		if (isAfter == null)
			return false;
		return isAfter;
	}
	
	public String getWeatherName() {
		return weatherName;
	}
}
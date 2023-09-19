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
package com.aionemu.gameserver.configs.schedule;

import com.aionemu.commons.utils.xml.JAXBUtil;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "conquest_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConquestSchedule
{
	@XmlElement(name = "conquest", required = true)
	private List<Conquest> conquestsList;
	
	public List<Conquest> getConquestsList() {
		return conquestsList;
	}
	
	public void setOfferingList(List<Conquest> conquestList) {
		this.conquestsList = conquestList;
	}
	
	public static ConquestSchedule load() {
		ConquestSchedule cs;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/conquest_schedule.xml"));
			cs = (ConquestSchedule) JAXBUtil.deserialize(xml, ConquestSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize conquest", e);
		}
		return cs;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "conquest")
	public static class Conquest {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "offeringTime", required = true)
		private List<String> offeringTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getOfferingTimes() {
			return offeringTimes;
		}
		
		public void setOfferingTimes(List<String> offeringTimes) {
			this.offeringTimes = offeringTimes;
		}
	}
}
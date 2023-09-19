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

@XmlRootElement(name = "circus_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class CircusSchedule
{
	@XmlElement(name = "circus", required = true)
	private List<Circus> circussList;
	
	public List<Circus> getCircussList() {
		return circussList;
	}
	
	public void setCircussList(List<Circus> circusList) {
		this.circussList = circusList;
	}
	
	public static CircusSchedule load() {
		CircusSchedule cs;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/circus_schedule.xml"));
			cs = (CircusSchedule) JAXBUtil.deserialize(xml, CircusSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize circus", e);
		}
		return cs;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "circus")
	public static class Circus {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "circusTime", required = true)
		private List<String> circusTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getCircusTimes() {
			return circusTimes;
		}
		
		public void setCircusTimes(List<String> circusTimes) {
			this.circusTimes = circusTimes;
		}
	}
}
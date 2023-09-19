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

@XmlRootElement(name = "svs_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class SvsSchedule
{
	@XmlElement(name = "svs", required = true)
	private List<Svs> svssList;
	
	public List<Svs> getSvssList() {
		return svssList;
	}
	
	public void setSvssList(List<Svs> svsList) {
		this.svssList = svsList;
	}
	
	public static SvsSchedule load() {
		SvsSchedule ss;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/svs_schedule.xml"));
			ss = (SvsSchedule) JAXBUtil.deserialize(xml, SvsSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize svs", e);
		}
		return ss;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "svs")
	public static class Svs {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "svsTime", required = true)
		private List<String> svsTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getSvsTimes() {
			return svsTimes;
		}
		
		public void setSvsTimes(List<String> svsTimes) {
			this.svsTimes = svsTimes;
		}
	}
}
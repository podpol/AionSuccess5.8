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

@XmlRootElement(name = "rvr_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class RvrSchedule
{
	@XmlElement(name = "rvr", required = true)
	private List<Rvr> rvrsList;
	
	public List<Rvr> getRvrsList() {
		return rvrsList;
	}
	
	public void setRvrsList(List<Rvr> rvrList) {
		this.rvrsList = rvrList;
	}
	
	public static RvrSchedule load() {
		RvrSchedule rs;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/rvr_schedule.xml"));
			rs = (RvrSchedule) JAXBUtil.deserialize(xml, RvrSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize rvr", e);
		}
		return rs;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "rvr")
	public static class Rvr {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "rvrTime", required = true)
		private List<String> rvrTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getRvrTimes() {
			return rvrTimes;
		}
		
		public void setRvrTimes(List<String> rvrTimes) {
			this.rvrTimes = rvrTimes;
		}
	}
}
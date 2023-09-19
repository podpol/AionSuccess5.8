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

@XmlRootElement(name = "beritra_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeritraSchedule
{
	@XmlElement(name = "beritra", required = true)
	private List<Beritra> beritrasList;
	
	public List<Beritra> getBeritrasList() {
		return beritrasList;
	}
	
	public void setInvasionsList(List<Beritra> beritraList) {
		this.beritrasList = beritraList;
	}
	
	public static BeritraSchedule load() {
		BeritraSchedule bs;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/beritra_schedule.xml"));
			bs = (BeritraSchedule) JAXBUtil.deserialize(xml, BeritraSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize beritra", e);
		}
		return bs;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "beritra")
	public static class Beritra {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "invasionTime", required = true)
		private List<String> invasionTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getInvasionTimes() {
			return invasionTimes;
		}
		
		public void setInvasionTimes(List<String> invasionTimes) {
			this.invasionTimes = invasionTimes;
		}
	}
}
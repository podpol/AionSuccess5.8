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

@XmlRootElement(name = "vortex_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class VortexSchedule
{
	@XmlElement(name = "vortex", required = true)
	private List<Vortex> vortexsList;
	
	public List<Vortex> getVortexsList() {
		return vortexsList;
	}
	
	public void setInvasionsList(List<Vortex> vortexList) {
		this.vortexsList = vortexList;
	}
	
	public static VortexSchedule load() {
		VortexSchedule vs;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/vortex_schedule.xml"));
			vs = (VortexSchedule) JAXBUtil.deserialize(xml, VortexSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize vortex", e);
		}
		return vs;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "vortex")
	public static class Vortex {
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
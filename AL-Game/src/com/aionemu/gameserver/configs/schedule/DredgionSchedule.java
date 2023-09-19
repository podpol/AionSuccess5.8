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

@XmlRootElement(name = "dredgion_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class DredgionSchedule
{
	@XmlElement(name = "dredgion", required = true)
	private List<Dredgion> dredgionsList;
	
	public List<Dredgion> getDredgionsList() {
		return dredgionsList;
	}
	
	public void setZorshivsList(List<Dredgion> dredgionList) {
		this.dredgionsList = dredgionList;
	}
	
	public static DredgionSchedule load() {
		DredgionSchedule ds;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/dredgion_schedule.xml"));
			ds = (DredgionSchedule) JAXBUtil.deserialize(xml, DredgionSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize dredgion", e);
		}
		return ds;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "dredgion")
	public static class Dredgion {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "zorshivTime", required = true)
		private List<String> zorshivTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getZorshivTimes() {
			return zorshivTimes;
		}
		
		public void setZorshivTimes(List<String> zorshivTimes) {
			this.zorshivTimes = zorshivTimes;
		}
	}
}
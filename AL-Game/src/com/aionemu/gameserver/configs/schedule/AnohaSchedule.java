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

@XmlRootElement(name = "anoha_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnohaSchedule
{
	@XmlElement(name = "anoha", required = true)
	private List<Anoha> anohasList;
	
	public List<Anoha> getAnohasList() {
		return anohasList;
	}
	
	public void setBerserksList(List<Anoha> anohaList) {
		this.anohasList = anohaList;
	}
	
	public static AnohaSchedule load() {
		AnohaSchedule as;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/anoha_schedule.xml"));
			as = (AnohaSchedule) JAXBUtil.deserialize(xml, AnohaSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize anoha", e);
		}
		return as;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "anoha")
	public static class Anoha {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "berserkTime", required = true)
		private List<String> berserkTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getBerserkTimes() {
			return berserkTimes;
		}
		
		public void setBerserkTimes(List<String> berserkTimes) {
			this.berserkTimes = berserkTimes;
		}
	}
}
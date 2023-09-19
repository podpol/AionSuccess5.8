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

@XmlRootElement(name = "moltenus_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoltenusSchedule
{
	@XmlElement(name = "moltenus", required = true)
	private List<Moltenus> moltenussList;
	
	public List<Moltenus> getMoltenussList() {
		return moltenussList;
	}
	
	public void setFightsList(List<Moltenus> moltenusList) {
		this.moltenussList = moltenusList;
	}
	
	public static MoltenusSchedule load() {
		MoltenusSchedule ms;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/moltenus_schedule.xml"));
			ms = (MoltenusSchedule) JAXBUtil.deserialize(xml, MoltenusSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize moltenus", e);
		}
		return ms;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "moltenus")
	public static class Moltenus {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "fightTime", required = true)
		private List<String> fightTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getFightTimes() {
			return fightTimes;
		}
		
		public void setFightTimes(List<String> fightTimes) {
			this.fightTimes = fightTimes;
		}
	}
}
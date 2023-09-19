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

@XmlRootElement(name = "instance_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstanceSchedule
{
	@XmlElement(name = "instance", required = true)
	private List<Instance> instancesList;
	
	public List<Instance> getInstancesList() {
		return instancesList;
	}
	
	public void setInstancesList(List<Instance> instanceList) {
		this.instancesList = instanceList;
	}
	
	public static InstanceSchedule load() {
		InstanceSchedule is;
		try {
			String xml = FileUtils.readFileToString(new File("./config/schedule/instance_schedule.xml"));
			is = (InstanceSchedule) JAXBUtil.deserialize(xml, InstanceSchedule.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize instance", e);
		}
		return is;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "instance")
	public static class Instance {
		@XmlAttribute(required = true)
		private int id;
		
		@XmlElement(name = "instanceTime", required = true)
		private List<String> instanceTimes;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public List<String> getInstanceTimes() {
			return instanceTimes;
		}
		
		public void setInstanceTimes(List<String> instanceTimes) {
			this.instanceTimes = instanceTimes;
		}
	}
}
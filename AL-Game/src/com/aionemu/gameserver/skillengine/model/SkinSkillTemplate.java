package com.aionemu.gameserver.skillengine.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Ranastic
 *
 */
@XmlRootElement(name = "skin_skill")
@XmlAccessorType(XmlAccessType.NONE)
public class SkinSkillTemplate {

	@XmlAttribute(name = "id", required = true)
	private int id;
	
	@XmlAttribute(name = "name")
	private String name = "";
	
	@XmlAttribute(name = "desc")
	private String desc = "";
	
	@XmlAttribute(name = "skill_group")
	private String skill_group;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getGroup() {
		return skill_group;
	}
	
	
}

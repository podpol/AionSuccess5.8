package com.aionemu.gameserver.model.templates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="skill_skin")
public class SkillSkinTemplate {
	@XmlAttribute(name="id", required=true)
	private int id;
	@XmlAttribute(name="name", required=true)
	private String name;
	@XmlAttribute(name="skill_group", required=true)
	private String skillgroup;
	@XmlAttribute(name="motion_name", required=true)
	private String motionName;
	@XmlAttribute(name="ammo_speed", required=true)
	private int ammoSpeed;
	  
	public int getId() {
		return id;
	}
	  
	public String getName() {
		return name;
	}
	  
	public String getSkillGroup() {
		return skillgroup;
	}
	  
	public String getMotionName() {
		return motionName;
	}
	  
	public int getAmmoSpeed() {
		return ammoSpeed;
	}
}
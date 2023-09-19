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
package com.aionemu.gameserver.model.templates.materials;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rolandas
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaterialTemplate", propOrder = { "skills" })
public class MaterialTemplate {

	@XmlElement(name = "skill", required = true)
	protected List<MaterialSkill> skills;

	@XmlAttribute(name = "skill_obstacle")
	protected Integer skillObstacle;

	@XmlAttribute(required = true)
	protected int id;

	public List<MaterialSkill> getSkills() {
		return skills;
	}

	public Integer getSkillObstacle() {
		return skillObstacle;
	}

	public int getId() {
		return id;
	}

}
